---
layout: default
title: Entwicklungsumgebung
---

## System-Voraussetzungen

* Java
* Gradle
* Eclipse mit den folgenden Plugins
    * [SonarLint](https://marketplace.eclipse.org/content/sonarlint)
    * [Eclipse-Environment-Variables](https://github.com/JorisAerts/Eclipse-Environment-Variables/)
    * [CheckStyle](http://eclipse-cs.sourceforge.net/)
* [Docker](https://docs.docker.com/)
* [Docker-Compose](https://docs.docker.com/compose/)

## Entwicklungsumgebung einrichten

1. Eclipse Projektdateien generieren:
    ```bash
    cd /path/to/repository
    cd examibur/
    ./gradlew eclipse
    ```
2. Eclipse starten
3. Projekt importieren
    * `File` - `Import` - `Existing Projects into Workspace`
    * Pfad vom Repository im Feld `Select root directory` einfügen
    * Abschliessen mit `Finish`
4. Gradle Nature hinzufügen:
    * Installiere Buildship Gradle Integration
    * `Help` - `Eclipse Marketplace` - `Find Buildship Gradle Integration` - `Install Now`
    * Rechtsklick auf Projekt
    * `Configure` - `Add Gradle Nature`

### Editor konfigurieren
Der Google Checkstyle verlangt für die Einrückung Spaces, in Eclipse werden aber standardmässig Tabs eingefügt. Zudem sind in Eclipse die Imports noch nach gewissen Packages sortiert (Java, Javax, etc.), im Checkstyle hingegen nur in lexikographischer Reihenfolge.

1. Rechtsklick auf das Examibur-Projekt im Package Explorer - `Checkstyle` - `Create Formatter Profile`
2. Einstellungen öffnen mit `Window` - `Preferences`
3. Auf `Java` - `Code Style` - `Formatter` navigieren
	1. Das neue Profil `eclipse-cs examibur` wählen
4. Auf `Java` - `Code Style` - `Organize Imports` navigieren
	1. Alle vordefinierten Packages löschen (`Java`, `Javax`, `org`, `com`)

Wenn nun ein File noch komplett mit Tabs formatiert ist, alles markieren und mit `Ctrl` + `Shift` + `F` formatieren. Die Imports können auch automatisch mit `Ctrl` + `Shift` + `O` sortiert werden. *Tipp: Unter `Java` - `Editor` - `Save Actions` lassen sich diese Aktionen automatisch bei jedem Speichern ausführen.*

Für andere Filetypen, z.B. XML, müssen die Einstellungen separat gemacht werden. Für XML ist die Einstellung unter `Preferences` - `XML` - `XML Files` - `Editor` zu finden.

## Entwicklungsumgebung starten

Die Application- und Datenbankserver (und die Projekt-Website) werden als Docker-Images bereitgestellt. Diese können für die Entwicklung einfach über Docker-Compose gestartet werden:

```bash
# Startet alle Services (Ctrl + C um zu stoppen)
docker-compose up
```

Nun kann ganz klassisch in Eclipse entwickelt werden. Um die Applikation auf dem Applikation-Server zu deployen muss der Gradle Task `assemble` ausgeführt werden.

Tipp: Falls du möchtest, dass nach jedem mal Speichern die Applikation neu deployed wird, kannst du den Gradle task "continuous" laufenlassen.

```bash
cd examibur
./gradlew assemble -t
```

## Tomcat Debugging

* Tomcat normal starten und aktuelle version darauf deployen
* Im Menu `Run` - `Debug Configurations...` eine neue Java Remote Application hinzufügen (falls nich nicht vorhanden)
    * Examibur als Projekt auswählen
    * Verbindungsdetails: Host `localhost`, Port `8000`
* Debuggung starten über den Debug-Button 

[Siehe auch Schritt-für-Schritt-Anleitung auf Stackoverflow](http://stackoverflow.com/questions/3835612/remote-debugging-tomcat-with-eclipse)
 

## PSQL

Falls PSQL/PgAdmin lokal installiert sind können diese so genutzt werden, als wäre postgres lokal installiert, solange die Entwicklungsumgebung läuft (siehe oben). Die Zugangsdaten sind der Datei `docker-compose.yml` zu entnehmen.

Alternativ kann PSQL auch über docker-compose genutzt werden: `docker-compose run postgres psql -h postgres -U examibur`. Das Kennwort findet sich in der Datei `docker-compose.yml`.

## Integration-Tests laufen lassen
Hierfür ist es wichtig, dass alle Umgebungsvariablen korrekt gesetzt sind. Mittels docker können die Tests direkt ausgeführt werden - es ist aber bequemer dies über Eclipse selektiv zu tun. Da die Tests für db-konfigurationen etc. Umgebungsvariabeln benötigt, ist das Plugin [Eclipse-Environment-Variables](https://github.com/JorisAerts/Eclipse-Environment-Variables) empfohlen. Folgende Variablen müssen gesetzt werden:

* `DB_HOST` = `localhost `
* `DB_USER` (Siehe `docker-compose.yml`)
* `DB_PASSWORD` (Siehe `docker-compose.yml`)
* `LOG_LEVEL` = `debug`
* `LOG_FILE` = `examibur.log`


## UI-Tests laufen lassen
Um reproduzierbare Screenshots zu bekommen müssen diese Tests in einem Docker-Container laufen. Die UI-Tests können einfach mit folgendem Kommando im Projekt-Root ausgeführt werden:

```bash
./run-integration.sh
```
