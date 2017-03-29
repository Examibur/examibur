# Installation

* Vorbereitung: DNS IP eintragen (sonar.raphael.li / examibur-demo.raphael.li

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

Git-SSh-Key auf den Server kopieren

```bash
scp git root@$SERVER_IP:/root/.ssh/id_rsa
scp git.pub root@$SERVER_IP:/root/.ssh/id_rsa.pub
```

Auf dem Server anmelden:

```bash
ssh root@$SERVER_IP
```

Korrekte berechtigung der erstellten keys Setzen 

```bash
chmod -R 700 /root/.ssh/
```

Das repository klonen

```
git clone git@gitlab.com:engineering-projekt/examibur.git /opt/examibur
```

Automatisches Re-Deployment des Stating-Environment einrichten:

```
useradd deploy -d /home/deploy
mkdir -p -m 700 /home/deploy/.ssh/

# NUR das folgende kommando auf dem Client mit den generierten schlüssen ausführen;
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

anschliessend den ssh daemon neu starten:

```
systemctl restart sshd.service
```

Testen auf dem Client, ob Zugriff via SSH Funktioniert:

```
ssh -i ssh deploy@$SERVER_IP
```

Login auf der eigenen registry (credentials von `examibur-bot`)

```
docker login registry.gitlab.com
```

Spezifische Umgebungsvariablen kopieren

```
cp -R /opt/examibur/projekteserver/env_templates/ /opt/examibur/projekteserver/env/
```

**und anschliessend anpassen und sichern (nicht im backup eingschlossen!)**

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


