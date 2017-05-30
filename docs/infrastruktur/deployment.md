---
layout: default
title: Deployment Projektserver
---
## Kennwörter & Keys

Alle verwendeten Kennwörter & Keys sind bei Raphael Zimmermann und Robin Suter abgelegt.

## Installation Projekeserver

Als erstes die IP im DNS eintragen (hier `sonar.raphael.li` und `examibur-demo.raphael.li`)

Als Ausgangslage dient ein minimaler Ubuntu-Server mit einem OpenSSH-Daemon.

[Docker CE](https://docs.docker.com/engine/installation/linux/ubuntu/) und [Docker Compose](https://docs.docker.com/compose/install/) installieren. Anschliessend Docker-Autostart aktivieren

```bash
systemctl enable docker.service
systemctl start docker.service
```

Notwendige Software installieren

```bash
apt-get install inotify-tools
```

Zwei frische SSH-Keys generieren. Dies muss auf einem vertrauenswürdigen System passieren

```bash
# Key for git access
ssh-keygen -t rsa -b 4096 -C 'examibur-projekteserver-git' -f git
# Key for SSH-Deployment
ssh-keygen -t rsa -b 4096 -C 'examibur-projekteserver-ssh' -f ssh
```

In Gitlab dem Deployment-Benutzer (`examibur-bot`) den generierten Schlüssel hinzufügen (`Profile` - `Edit` - `SSH Keys` - `Add an SSH key `).

Git-SSH-Key auf den Server kopieren

```bash
scp git root@$SERVER_IP:/root/.ssh/id_rsa
scp git.pub root@$SERVER_IP:/root/.ssh/id_rsa.pub
```

Auf dem Server anmelden:

```bash
ssh root@$SERVER_IP
```

Korrekte Berechtigung der erstellten Keys setzen 

```bash
chmod -R 700 /root/.ssh/
```

Das Repository klonen

```
git clone git@gitlab.com:engineering-projekt/examibur.git /opt/examibur
```

Automatisches Re-Deployment des Stating-Environment einrichten:

```
useradd deploy -d /home/deploy
mkdir -p -m 700 /home/deploy/.ssh/

# NUR das folgende Kommando auf dem Client mit den generierten Schlüsseln ausführen;
scp ssh.pub root@$SERVER_IP:/home/deploy/.ssh/authorized_keys

cp /opt/examibur/projekteserver/deploy/trigger_deploy.sh /home/deploy/trigger_deploy.sh

chown -R deploy /home/deploy/
chmod +x /home/deploy/trigger_deploy.sh
```

In der Datei `/etc/ssh/sshd_config` folgende Zeilen am Ende der Datei ergänzen

```
Match User deploy
        ForceCommand /home/deploy/trigger_deploy.sh
```

anschliessend den SSH daemon neu starten:

```
systemctl restart sshd.service
```

Testen auf dem Client, ob Zugriff via SSH Funktioniert:

```
ssh -i ssh deploy@$SERVER_IP
```

Login auf der eigenen Registry (credentials von `examibur-bot`)

```
docker login registry.gitlab.com
```

Spezifische Umgebungsvariablen kopieren

```
cp -R /opt/examibur/projekteserver/env_templates/ /opt/examibur/projekteserver/env/
```

**und anschliessend anpassen und sichern (nicht im Backup eingschlossen!)**

Service, welcher die docker-compose Dienste startet registrieren.

```
systemctl link /opt/examibur/projekteserver/systemd/examibur-dev.service 
systemctl start examibur-dev.service
```

Die Zertifikate werden nun das erste mal generiert. Das log kann mit `journalctl -xe -f --unit examibur-dev.service` eingesehen werden.
Ist alles erfolgreich gestratet (vorallem sonarqube dauert seine gute Zeit...), dann kann der dienst neu gestartet werden.

```
systemctl start examibur-dev.service
```

Nun sollte die Webseite über die definierten URLs erreichbar sein.

Redeploy service registrieren

```
systemctl link /opt/examibur/projekteserver/systemd/examibur-redeploy.service 
systemctl start examibur-redeploy.service
```

Backup task einrichten

```
systemctl link /opt/examibur/projekteserver/systemd/examibur-backup.service
systemctl link /opt/examibur/projekteserver/systemd/examibur-backup.timer
systemctl start examibur-backup.timer
systemctl start examibur-backup.service # First Backup!
```

## Installation Gitlab Worker 

* Installation gemäss [Dokumentation](https://gitlab.com/gitlab-org/gitlab-ci-multi-runner/blob/master/docs/release_process/README.md)
* Dediziertes Docker-Netzwerk erstellen
    * `docker network create examibur_examibur`
* Eigene Konfiguration überschreiben (`/etc/gitlab-runner/config.toml`):

```
concurrent = 1
check_interval = 0

[[runners]]
  name = "sinv-56077"
  url = "https://gitlab.com/ci/"
  token = "REPLACE_ME"
  executor = "docker"
  [runners.docker]
    tls_verify = false
    image = "docker:1.13-dind"
    network_mode = "examibur_examibur"
    privileged = true
    disable_cache = false
    volumes = ["/cache", "/root/.gradle/", "/var/run/docker.sock:/var/run/docker.sock"]
  [runners.cache]

```
