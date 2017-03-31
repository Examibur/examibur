---
layout: default
title: Entwicklungsumgebung
---

## System-Voraussetzungen

* Java
* Gradle
* Eclipse
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
Der Google Checkstyle verlangt für die Einrückung Spaces, in Eclipse werden aber standardmässig Tabs eingefügt.

1. Rechtsklick auf das Examibur-Projekt im Package Explorer - `Checkstyle` - `Create Formatter Profile`
2. Einstellungen öffnen mit `Window` - `Preferences`
3. Auf `Java` - `Code Style` - `Formatter` navigieren
4. Das neue Profile `eclipse-cs examibur` wählen

Wenn nun ein File noch komplett mit Tabs formatiert ist, alles markieren und mit `Ctrl` + `Shift` + `F` formatieren.

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

### Troubleshooting
Wenn SELinux aktiviert ist (z.B. unter Fedora) kann es sein, dass die Zugriffe auf die Docker-Volumes vom System blockiert werden. Dann muss für die beiden Verzeichnisse `docs/` und `examibur/webapps` der Kontext entsprechend gesetzt werden:
```bash
sudo chcon -Rt svirt_sandbox_file_t docs/ examibur/webapps/
```

## PSQL

Falls PSQL/PgAdmin lokal installiert sind können diese so genutzt werden, als wäre postgres lokal installiert, solange die Entwicklungsumgebung läuft (siehe oben). Die Zugangsdaten sind der Datei `docker-compose.yml` zu entnehmen.

Alternativ kann PSQL auch über docker-compose genutzt werden: `docker-compose run postgres psql -h postgres -U examibur`. Das Kennwort findet sich in der Datei `docker-compose.yml`.