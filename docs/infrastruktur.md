# EPJ Infrastruktur Dokumentation

## System-Voraussetzungen

* Java
* Gradle
* [Docker](https://docs.docker.com/)
* [Docker-Compose](https://docs.docker.com/compose/)

## Entwicklungsumgebung einrichten



## PSQL

* Lokale Installation von PSQL/PgAdmin kann genutzt werden - Verbinden auf Port `localhost:5432` mit Benutzer und Passwort aus der Datei `docker-compose.yml`
* PSQL via docker-compose: `docker-compose run postgres psql -h postgres -U examibur`
  * Das Kennwort für die Entwicklungsumgebung findet sich in der Datei `docker-compose.yml`

## Website generieren

```bash
docker-compose website up	
```

