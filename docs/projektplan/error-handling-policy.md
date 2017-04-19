---
layout: default
title: Error-Handling Policy
---
# Error-Handling Policy

* Alle Daten auf der Datenbank sind sicher und müssen beim Laden nicht mehr mit Barrikaden überprüft werden.
    * Sicherheit wird vor dem Schreiben in die Datenbank sichergestellt.
* Alle Daten, welche in das System von aussen (via Web oder Prüfungsimport) eingelesen werden, müssen über Barrikaden verifiziert werden.
    * Dem Benutzer müssen beim Import verständliche Fehlermeldungen angezeigt werden.
    * Eingabe-Validierung wird im Browser und auf dem Server durchgeführt und im Fehlerfall eine Fehlermeldung angezeigt.
    * Die Serverseitige-Validierung muss keine detailiertes Feedback zurückliefern.
    * Bei unerwarteten Exceptions: Benutzerinformationen mit unerwartetem Fehler protokollieren.

## Exception-Policy
* Ein globaler Exception Handler protokolliert unerwartete Exceptions. Die Applikation läuft weiter.
* Exceptions so lokal wie möglich behandeln.
* Daten (insbesondere Prüfung) müssen immer konsistent bleiben.
* Es gelten die Best-Practices nach Joshua Bloch's Effective Java (Addison-Wesley, 2001) (via [IBM DeveloperWorks - The exceptions debate](https://www.ibm.com/developerworks/library/j-jtp05254/#artrelatedtopics)
    * Use exceptions only for exceptional conditions.
    * Use checked exceptions for recoverable conditions and runtime exceptions for programming errors.
    * Avoid unnecessary use of checked exceptions.
    * Throw exceptions appropriate to the abstraction.

### Implementierung
* Alle Exceptions, die der Integration-Layer werfen kann, werden vom Business-Layer gefangen, geloggt und weiter geworfen
* Der Business-Layer wirft nur vordefinierte Exceptions:
    * `ValidationException` für ungültige Werte, die vom User in den Service kommen und die Integrität der Daten verletzen würde
    * `IllegalArgumentException` für ungültige Funktions-Parameter, z.B. eine negative ID
    * `NotFoundException`, wenn eine angeforderte Ressource auf der Datenbank nicht gefunden wurde
    * `AuthorizationException`, falls der User keine Berechtigung hat, diesen Service mit den spezifischen Argumenten aufzurufen
    * `IOException`, wenn zwischen dem UI und dem Service ein Fehler in der Kommunikation auftritt
* Der UI-Layer (Spark) verwaltet die Exceptions vom Business-Layer und zeigt dem User entsprechende Fehlermeldungen an

## Assertion Policy
* Es werden keine Asserts verwendet, damit keine Diskrepanz zwischen Entwicklung und Produktion entstehen kann.
    * "It is absurd to make elaborate security checks on debugging runs, when no trust is put in the results, and then remove them in production runs, when an erroneous result could be expensive or disastrous. What would we think of a sailing enthusiast who wears his lifejacket when training on dry land, but takes it off as soon as he goes to sea?" - C.A.R. Hoar
* Barrikaden werden mit [Guava Preconditions](https://github.com/google/guava/wiki/PreconditionsExplained) implementiert.

## Logging Policy

* Ein Logfile wird auf dem Server geführt, welches mit externen Tools beobachtet werden kann.
* Es wird das Framework [Log4J](https://logging.apache.org/log4j/2.0/) mit den folgenden Levels verwendet:
    * Debug (Default in Entwicklung)
    * Info (Default in Produktion)
    * Warning
    * Error
* Exception müssen protokolliert werden, falls diese nicht korrekt behandelt werden konnten (inkl. Stack-Trace).
* Folgende Aktionen müssen auditierbar sein, sprich User- und relevante Daten protokolliert werden:
    * Administrative Aktionen
    * Alles, was eine Prüfung verändert
    * Ungültige Login-Versuche
    * Passwortwechsel
