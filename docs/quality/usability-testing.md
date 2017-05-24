---
layout: default
title: Usability Tests 
---
# Usability Tests

## Auswahl Testpersonen
Da im Rahmen des Engineering Projekts die Korrekturkomponente im Vordergrund steht wird auch nur diese für die mittels Usability-Tests untersucht. Dafür ist nur die Rolle des [Dozent, respektive Korrektor und Reviewer,](../anforderungen/personas.html#dozenten) Sinn. Aus zeitlichen Gründen haben wir uns dazu entschieden, die Usability Tests nur mit einer Person durchzuführen.

## Test Geräte

Die Tests werden mit den persönlichen Laptops der Testpersonen durchgeführt, damit sich diese auf die ausschliesslich auf die zu testende Anwendung fokussieren können.

## Szenarien / Szenarien

### Prüfung Korrigieren

Die Hochschule, an der Sie dozieren, hat neulich ein System zur Prüfungskorrektur eingeführt. Ihr Kollege, mit welchem Sie ein Modul unterrichten, hat eine Prüfung erfasst und die Studenten haben diese bereits gelöst. Nun hat er Sie angesprochen, ob sie die Prüfung nicht noch zu ende Korrigieren können, da er aus persönlichen gründen Verhindert ist. Da Sie sich gut vertragen sagen Sie zu. Ihre Aufgabe ist nun, die offene Prüfung zu korrigieren.

| URL:         | https://examibur-demo.raphael.li |
|--------------|----------------------------------|
| Benutzername | `stefan.boehm`                   |
| Passwort     | `***`                            |


### Auswertung
Sie haben nun die Prüfung InfSi1 komplett korrigiert. Finden Sie heraus, wie die Prüfung resp. die einzelnen Aufgaben gelaufen ist.


### Prüfung Reviewen

Ihr Kollege hat eine Prüfung korrigiert und bittet Sie ein Review davon zu machen.

| URL:         | https://examibur-demo.raphael.li |
|--------------|----------------------------------|
| Benutzername | `juergen.koenig`                 |
| Passwort     | `***`                            |


### Prüfung Approven

Ihr Kollege hat eine Prüfung, welche Sie korrigiert haben, überprüft. Sie müssen nun seine Änderungsvorschläge genehmigen oder ablehnen.

| URL:         | https://examibur-demo.raphael.li |
|--------------|----------------------------------|
| Benutzername | `uwe.baer`                       |
| Passwort     | `***`                            |


## Durchführung

### Einführung
Folgende Text-Passage wird als Einführung den Testpersonen persönlich präsentiert:

"Besten Dank, dass Sie Sich Zeit nehmen! Sie helfen uns dabei, ein intuitives Produkt zu entwickeln, welches den Benutzern Arbeit abnimmt und nicht im Weg steht.

Der Usability-Test wird wie folgt ablaufen. Ich gebe Ihnen ein einfaches Szenario vor. Ihre Aufgabe ist es dann, dieses Szenario so gut wie möglich abzuarbeiten. Denken Sie dabei laut mit! Teilen Sie uns mit, was Sie gerade machen und sprechen sie Unklarheiten laut aus. Dabei können Sie keine Fehler machen. Sollten sie nicht ans Ziel kommen, so ist dies nicht Ihr Fehler, sondern einer der Software!

Gibt es bis hier Fragen?"

Anschliessend wird der Testperson ein Szenario nach dem anderen vorgegeben.

### Raphael Das Guptas

* **Moderator** Raphael Zimmermann
* **Datum** 22. Mai 2017, 21:00

#### Fragen zur Person

* Was sind Ihre aufgaben im Lehrbetrieb?
    * Mitbetreuung Übungsstunden der Fächer, Datenbanksysteme 1, Datenbanksysteme 2, Informationssysteme
        * Teilweise semesterwochenweise/themenweise zwischen mir und anderen Assistenten und/oder dem Dozent der jeweiligen Vorlesung aufgeteilt, teilweise nach Übungsdurchführungen (Gruppen) aufgeteilt (von Fach zu Fach und manchmal von Semester zu Semester unterschiedlich).
    * inkl. Erstellung/Überarbeitung von Übungsaufgaben/-anleitungen, wo noch nicht vorhanden oder veraltet
    * inkl. Bewertung Testate
* Welche Prüfungen korrigieren Sie?
    *  Bisher glaub' nur Mit-Korrektur (Aufteilung nach Aufgaben) von Datenbanksysteme 1. Grundsätzlich aber für alle Fächer möglich, an denen ich beteiligt bin.
* Wie gehen sie bei der Korrektur vor?
    *  Mischung aus Querkorrigieren (einzelne Aufgaben für alle Teilnehmer nacheinander) und Teilnehmer-weise (mehrere Aufgaben eines Prüflings nacheinander), je nach Aufgaben-/Lösungslänge und -komplexität und wie die Aufgaben zusammenhängen / aufeinander aufbauen.
* Haben Sie Erfahrungen mit digitalen Prüfungssystemen?
    *  Bisher nicht. (Ich habe Erfahrung mit Moodle, das auch als digitales Prüfungssystemen genutzt werden kann, habe dieses aber bisher nur für andere Zwecke (LMS, Testatabgaben) genutzt.)

#### Protokoll

* Einführung
* Szenario 1
    * Testperson tippt in die Addressleiste die URL ein und wird auf die Login-Seite weitergeleitet.
    * Der Testperson ist nicht den ersten Blick ersichtlich, dass es sich um mehrere Felder halten.
        * Mehr Abstand zwischen den Feldern oder diese weniger breit zu machen würde helfen.
    * Nach dem Login wird die Testperson auf das Dashboard umgeleitet.
    * Da im Szenario nicht klar ist, um welche Prüfung es sich handelt ist die Testperson unsicher, was die Kürzel bedeuten.
        * Obwohl dies wohl ehr an einem unrealistischen Szenario liegt wäre die Angabe der kompletten Modulbezeichnung hilfreich
    * Der Testperson sind die Bezeichnungen `aufgabenweise` und `prüfungsweise` nicht auf Anhieb klar. Er kann sich deren Bedeutung aber korrekt ableiten.
        * Die Testperson ist irritiert, dass die Bezeichnungen gross geschrieben sind. Er interpretierte diese so zu beginn als Nomen.
        * Die Testperson schlägt als alternative Bezeichnung "Nach Aufgaben/Teilnehmer sortiert korrigieren" vor.
    * Die Testperson ist irritiert, dass es die gleichen Prüfungen sowohl unter "Korrektur" als auch inter "Meine Prüfungen" erscheint.
    * "Sind das 2 Ansichten auf das gleiche?"
        * Bessere Labels wären beispielsweise (Meine) Ausstehende Korrekturen / Reviews
        * Wenn es nicht die "eigene" Prüfung ist, dann sollte es "meine beteiligten Prüfungen" heissen (analog zu "Repositories you contributed to")
        * In "Meine Prüfungen" ist nicht klar, welche Rolle man darin gehabt hat.
    * Die Testperson navigiert auf die Übersicht der zu korrigierenden Prüfung.
    * Die Testperson lobt den Breadcrumb als sehr übersichtlich
    * In der Übersicht sind die Rahmenbedinungen sichtbar, was die Testperson als Hilfe für das aktuelle Szenario lobt.
    * Die Testperson bemängelt, dass die Durchführung (welches Jahr) nicht erkennbar ist.
    * Die Testperson möchte sich einen Überblick über die Prüfung verschaffen, also wie weit diese bereits korrigiert ist und navigiert auf den "Teilnahmen"-Tab
    * "Komische Studentennamen"
    * Die Spalte "Fortschritt" wird als mehrdeutig bemängelt:
        * "Korrekturfotrschritt" oder "wie weit ist der Student gekommen?"
    * Die Testperson klickt aus Neugier auf einen Teilnahme.
    * Die Testperson navigiert über den Breadcrumb auf die zu korrigierende Prüfung und beginnt mit der Korrektur über den Button auf der Prüfungsübersicht.
    * Während der Korrektur zeigt sich die Testperson irritiert über die Testdaten. Diese sind meist leer oder haben nichts mit der Aufgabe zu tun.
    * Eine Aufgabe ist bereits korrigiert, muss aber erneut korrigiert werden.
        * Inkonsistente Testdaten. Wurde als Bug in [Issue #129](https://gitlab.com/engineering-projekt/examibur/issues/129) raportiert.
    * Der Testperson ist der Unterschied zwischen `Kommentar` und `Begründung` nicht klar (Was ist öffentlich, was ist nur Intern?)
        * Vorschlag: "Korrekturnotiz" für interne Anmerkungen
    * Die Testperson bemängelt, dass kein Fortschritt ersichtlich ist. Ein Fortschrittsbalken wäre hilfreich.
* Szenario 2
    * Die Testperson findet die Reports ohne Probleme.
    * Aus Neugier speichert die Testperson den Report als PDF, was problemlos funktioniert.
    * Die problematischen Aufgaben werden von der Testperson einfach identifiziert.
    * Die Testperson wünscht sich weitere Graphen und Informationen wie Minimum, Maximum und Standardabweichung bei den einzelnen Aufgaben.
    * Eine Funktion wie in Moodle, wo Aufgaben mit tiefer Korrelation mit der Gesammtprüfung aufgelistet werden wäre sehr wünschenswert, da dies ein gutes Verdachsmoment auf schlecht formulierte Aufgaben ist.
    * Die Testperson ist verwirrt ab der gutherzigen Korrektur der bereits korrigierten Aufgaben und würde diese gerne anpassen, was aber nicht geht.
        * Korrekturen sollte unbedingt editierbar sein.
    * Korrektur-Button kann auch nach abgeschlossener Korrektur verwendet werden, die Testperson wird aber ohne Rückmeldung direkt auf die Prüfungsübersicht weitergeleitet.

* Szenario 3:
    * Die Test-Person bemängelt, dass ihr nicht klar ist, ob das Review bereits begonnen hat oder nicht. Der Status "Review" wird als unklar empfunden
        * Unterscheidung wie "In Review" und "To Review" wäre evtl. hilfreich.
    * Beim Review ist unklar ob mit der Punktezahl die Lösung des Studenten oder diejenige der Korrektur bewertet wird.
    * Die Tetstdaten sind irritierend ("Rechenfehler" als Korrektur-Kommentar bei einer leeren Aufgabenlösung) 
    * Die Testperson hat einen Fehler gemacht bei dem Review und klickt auf den Zurück-Button des Browsers.
    * Die Testperson verändert das Review und klickt erneut auf Speicher. Ein Error 500 wird angezeigt.
    * Die Testperson bemängelt erneut die Abwesenheit eines Fortschrittsbalken.
    * Die Spalte Fortschritt im Tab `Teilnehmer` wurde von der Testperson so interpretiert, dass diese den Korrekturfortschritt anzeigt.
        * Es würde wohl Sinn machen, die Spalte je nach Status anders zu benennen.
    * Die Einrückung der Korrekturen und Reviews wird als irritierend empfunden.
    * Die Testperson bemängelt, dass ihr nicht klar ist, wo sie bei der Korrektur hinschauen muss.
        * Die UI-Elemente nehemn sehr viel Platz ein.
        * Wichtiges hervorheben, also primär die Dinge, die sich beim Blättern verändern (Bsp. die Antwort des Studenten)
        * Für Leere Felder einen platzhalter verwenden. "Der Teilnehmer hat diese Aufgabe leer abgegeben"
    * Gleiche Punkte zum Button "Reviewen" wie bereits bei der Korrektur bemängelt. 

* Szenario 4:
    * Die Bezeichnung `approval` ist nicht eindeutig
    * Was wird approved? Die Korrektur? Das Review? Die Prüfung?
    * "Ein review ist review und muss nicht approved werden"
    * Auf der Prüfungsübersicht Wäre es noch nützlich zu sehen, wie viele Teinehmer und Aufgaben die Prüfung hat.
    * Bei Auswertung müsste auch ein Vergleich zwischen Erstkorrektur und dem Review (=2. Korrektur)
    * "Median ist cool, wäre aber noch cool je Aufgabe"
    * Wenn das Review etwas korrekt bemängelt, aber keine Teilpunkte gibt muss das Review ablehnen.
    * Blättert geht nicht automatisch weiter wie bei Review und Korrektur. Das ist irritierend.
    * Nach dem Approval muss wieder nach unten gescrollt werden, was mühsam ist (Reisezeit der Maus).
        * Ist aber von der Bildschirmgrösse abhängig.

## Auswertung / Erkenntnisse

Die wichtigsten Beobachtungen wurden gemeinsam vom Team in Issues übertragen. Nach deren Abschluss müssen erneut UX-Tests durchgeführt werden.

* [Bedienbarkeit des Login-Screens verbessern](https://gitlab.com/engineering-projekt/examibur/issues/130)
* [Beschriftungen / Naming überarbeiten](https://gitlab.com/engineering-projekt/examibur/issues/131)
* [Korrektur-Button nach nach abgeschlossener Korrektur deaktivieren](https://gitlab.com/engineering-projekt/examibur/issues/132)
* [Reviews und Korrekturen müssen editierbar sein](https://gitlab.com/engineering-projekt/examibur/issues/133)
* [Felder Kommentar und Begründung zusammenfassen](https://gitlab.com/engineering-projekt/examibur/issues/134)
* [Fortschrittsbalken während der Korrektur / Review anzeigen](https://gitlab.com/engineering-projekt/examibur/issues/135)
* [https://gitlab.com/engineering-projekt/examibur/issues/136](https://gitlab.com/engineering-projekt/examibur/issues/136)

