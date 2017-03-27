# Installation

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
scp git root@46.101.212.144:/root/.ssh/id_rsa
scp git.pub root@46.101.212.144:/root/.ssh/id_rsa.pub
```

Auf dem Server anmelden:

```bash
ssh root@46.101.212.144
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
scp ssh.pub root@46.101.212.144:/home/deploy/.ssh/authorized_keys

cp /opt/examibur/projekteserver/deploy/trigger_deploy.sh /home/deploy/trigger_deploy.sh

chown -R deploy /home/deploy/
chmod +x /home/deploy/trigger_deploy.sh
```

In der Datei `/etc/ssh/sshd_config` folgende Zeilen am Ende der Datei ergänzen

```
Match User deploy
        ForceCommand /home/deploy/trigger_deploy.sh
```

Testen, ob Zugriff via SSH Funktioniert:

```
ssh -i ssh deploy@46.101.212.144
```

Service, welcher die docker-compose Dienste startet registrieren.

```
systemctl link /opt/examibur/projekteserver/systemd/examibur-dev.service 
systemctl enable examibur-dev.service
systemctl start examibur-dev.service
```

Redeploy service registrieren

```
systemctl link /opt/examibur/projekteserver/systemd/examibur-redeploy.service 
systemctl enable examibur-redeploy.service
systemctl start examibur-redeploy.service
```
TODO: env kopieren
TODO: Backup Task einrichten:

```
# SYSTEMD TIMER
# docker-compose down
# tar /var/volumes/KNOWN_VOLUMES ...or everything?
# put these backup tars to /opt/backups
# docker-compose down
```
