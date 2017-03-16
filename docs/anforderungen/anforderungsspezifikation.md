---
layout: default
title: Anforderungsspezifikation
---

# Einführung

## Zweck
Dieses Dokument beschreibt die Anforderungsspezifikation. Es wird der Fokus auf die funktionalen Anforderungen gelegt. Es ist ersichtlich, welche Use Cases Scope des Engineering-Projekts sind und welche Use Cases für Folgeprojekte vorgesehen sind. Desweiteren werden die Stakeholders analysiert und ihre Ansprüche mit User Stories identifiziert.

## Gültigkeitsbereich
Der Gültigkeitsbereich beschränkt sich auf die Projektdauer vom 20.02.17 bis 02.06.17. Während dieser Zeit wird das Dokument laufend aktualisiert und stellt zu jedem Zeitpunkt einen genauen Überblick über die Anforderungsspezifikation zur Verfügung.

## Referenzen
In der nachfolgenden Tabelle sind alle Dokumente und Links aufgelistet, welche für die Anforderungsspezifikation von Relevanz sind. Diese Liste wird laufend auf dem aktuellen Stand gehalten.

| **Name**                          | **Referenz**                                                                                                                                                                                                                                         |
| --------------------------------- | ----------------------------- |
|                      |

## Übersicht
`TODO`

# Allgemeine Beschreibung
`TODO`

## Produkt & Vision
`TODO Verweis auf Projektplan`

## Einschränkungen
`TODO`

## Zielgruppe
`TODO`

## Abhängigkeiten
Die Nutzung von Examibur setzt einen aktuellen Webbrowser mit einer funktionierenden Internetverbindung voraus.

Examibur verwendet diverse Bibliotheken, Frameworks und Produkte, welche sich in der Praxis bewährt haben. `TODO Verweis auf Architektur`

# Funktionale Anforderungen
Im folgenden sind die funktionalen Anforderungen anhand von Use Cases beschrieben. Es wird besonderen Wert auf die Aktoren und Stakeholder gelegt, um die Bedürfnisse der verschiedenen Anspruchsgrupppen an das Produkt zu identifizieren. Die Use Cases, welche im Scope des Projekts sind, sind im Fully-Dressed-Format aufgelistet. Use Cases für allfällige Folgeprojekte werden im Brief-Format spezifiziert.

## Einschränkungen
Die Beschreibung der folgenden Kapitel wurde basierend auf der Vision von Examibur erfasst. Im Unterkapitel Use Cases ist klar ersichtlich, welche Funktionalität Scope des Projekts Examibur ist. Um die Applikation offen für Folgeprojekte zu halten, wurden die anderen Anforderungen zusätzlich spezifiziert (Kapitel Erweiterungen), um sich keine Hindernisse einzubauen, welche eine allfällige Erweiterung erschweren könnten.

## Aktoren

| Aktor                         | Beschreibung  |
|-------------------------------|---------------|
| **Korrektor**                 | Ein Korrektor erstellt und verwaltet Prüfungen. Nach Durchführung einer Prüfung hat er die Möglichkeit, die Prüfungen mit einem Scanner in das System einzulesen. Sobald die Prüfungen im System erfasst bzw. eingelesen wurden, kann er gleiche Prüfungsaufgaben einzeln durchgehen und korrigieren. Der Korrektor kann auch eine Prüfung eines einzelnen Studenten am Stück korrigieren. Sind alle Aufgaben korrigiert, kann die Prüfung in den Reviewprozess übergeben werden. Aufgabenbewertungen, welche vom Reviewer zurückgewiesen werden, müssen überarbeitet werden, bevor die Prüfung in den Status korrigiert überführt werden kann. |
| **Reviewer**                  | Sobald der Korrektor eine Prüfung einem Reviewer zuordnet, kann der Reviewer Aufgabe für Aufgabe durchgehen und die Aufgabenwertungen des Korrektor überprüfen, welche er entweder akzeptieren kann oder per Kommentar an den Korrektor zurückweist.	|
| **Verwaltung**                | Die Verwaltung der Lehranstalt erstellt initiale Prüfungen mit allen Terminen und weist diese den Korrektoren zur Erstellung der Prüfung zu. Die Verwaltung kann den Status der einzelnen Prüfung einsehen und hat die Möglichkeit die korrigierten Prüfung nach Abschluss der Reviewphase einzusehen und bei Bedarf zu exportieren. Falls ein Student den Rekursprozess startet, übernimmt die Verwaltung die Verarbeitung dieses Rekurses. 	|
| **Student**                   | Der Student hat die Möglichkeit die korrigierte Prüfung nach Freischaltung der Noten einzusehen und bei Ungereimtheiten den Rekursprozess anzustossen.  	|

## Stakeholder

Die Ansprüche der Stakeholder werden als User Stories erfasst, um so den Sinn und Zweck von Examibur und den Mehrwert für alle Stakeholder aufzuzeigen.

### Lehranstalt
#### _US101: Qualitätssicherung_

Als Lehranstalt möchte ich die Qualität der Prüfungskorrekturen hochhalten, um Rekurskosten zu sparen.

#### _US102: Rekurszprozess_

Als Lehranstalt möchte ich den Rekursprozess abgebildet haben, um so die rechtlichen Vorgaben einhalten zu können.

### Dozent
#### _US201: Prüfung erstellen_

Als Dozent möchte ich Prüfungen online erstellen können, um so eine einfache, zentrale und für Nachfolge transparente Ablage der Prüfung zu erreichen.

#### _US202: Qualität aufrechterhalten_

Als Dozent möchte ich Prüfungskorrekturen reviewen lassen, um so die Qualität und Fairness der Bewertungen aufrecht zu halten und allfällige Flüchtigkeitsfehler zu vermeiden.

#### _US203: Prüfung online korrigieren_

Als Dozent möchte ich Prüfungen online korrigieren, um so den Papierkrieg einzudämmen, Verluste vorzubeugen und Transparenz zu gewährleisten.

### Student
#### _US301: anonyme Korrektur_

Als Student möchte ich einen anonyme Korrektur der Prüfung erhalten, um so eine faire Beurteilung aller Studenten sicherzustellen.

#### _US302: Prüfungsreview_

Als Student möchte ich eine zweite Beurteilung der Korrektur nach dem 4-Augen-Prinzip, um so die Qualität der Korrekturen hoch und fair zu halten.

#### _US303: Prüfungseinsicht_

Als Student möchte ich die Prüfung inklusive Korrektur nach Freischaltung der Noten einsehen, um so das Zustandekommen der Note zu verstehen und um Wissenslücken am Ende des Moduls zu identifizieren.

#### _US304: Rekursprozess_

Als Student möchte ich den Rekursprozess bei Ungereimtheiten anstossen, um so alle Fristen und Bedingungen für einen gültigen Rekurs einhalten zu können.


## Use Cases

### Diagramm
![](resources/UseCaseDiagram.svg)

### Beschreibung

Im folgenden sind alle Use Cases im Fully-Dressed-Format aufgelistet, welche Teil des Scopes des Projekts sind.

#### _UC001: Prüfungen anzeigen_
**Primary Actor:** Korrektor, Reviewer, Verwaltung

**Stakeholders and Interests:**

- _Korrektor_ will alle Prüfungen sehen, welche ihm zugewiesen sind, um Korrekturen durchführen zu können.
- _Reviewer_ will alle Prüfungen sehen, welche ihm zum Review zugewiesen sind, um den Review durchführen zu können.
- _Verwaltung_ will alle Prüfungen sehen, um Metadaten (Durchführungsdatum, etc.) bearbeiten und um Notenexporte durchführen zu können.

**Preconditions:**

- Der User ist im System angemeldet.

**Postconditions:**

- Dem _Korrektor_ werden seine zugewiesene Prüfungen angezeigt.
- Dem _Reviewer_ werden zum Review zugewiesene Prüfungen angezeigt.
- Der _Verwaltung_ werden alle Prüfungen angezeigt.

**Main Success Scenario:**

1. Der User öffnet die Übersicht/Dashboard.
2. Das System lädt alle relevanten Prüfungen.

#### _UC002: Prüfung öffnen_

**Primary Actor:** Korrektor, Reviewer, Verwaltung

**Stakeholders and Interests:**

- _Korrektor_ will eine Prüfung öffnen, um Prüfungsdurchführungen korrigieren zu können.
- _Reviewer_ will eine Prüfung öffnen, um den Review der Korrektur durchführen zu können.
- _Verwaltung_ will eine Prüfung öffnen, um Prüfungsteilnahmen an _Studenten_ freigeben zu können.

**Preconditions:**

- Der User ist im System angemeldet und befindet sich auf der Übersicht/Dashboard.
- Die Prüfung ist dem User zugewiesen.

**Postconditions:**

- Dem User wird die Prüfung zur weiteren Verarbeitung (Korrektur, Review, Export, Auswertung) angezeigt.

**Main Success Scenario:**

1. Der User wählt aus der Prüfungsliste eine Prüfung aus.
2. Das System lädt die Prüfung.

**Level:**

include:

 - UC003
 - UC004
 - UC018

#### _UC003: Prüfungsteilnahmen anzeigen_
**Primary Actor:** Korrektor, Reviewer, Verwaltung

**Stakeholders and Interests:**

- _Korrektor_ will alle Prüfungsteilnahmen sehen, um Prüfungsteilnahmen einzeln korrigieren zu können.
- _Reviewer_ will alle Prüfungsteilnahmen sehen, um Prüfungsteilnahmen einzeln reviewen zu können.
- _Verwaltung_ will alle Prüfungsteilnahmen sehen, um Prüfungsteilnahmen an Studenten freigeben und um Notenexporte über alle Prüfungsteilnahmen durchführen zu können.

**Preconditions:**

- Der User ist im System angemeldet und hat eine Prüfung geöffnet.

**Postconditions:**

- Dem User werden alle Prüfungsteilnahmen zur Prüfung angezeigt.

**Main Success Scenario:**

1. Das System lädt alle Prüfungsteilnahmen.

#### _UC004: Prüfungsaufgaben anzeigen_
**Primary Actor:** Korrektor, Reviewer

**Stakeholders and Interests:**

- _Korrektor_ will alle Prüfungsaufgaben sehen, um alle Lösungen zu einer Prüfungsaufgaben korrigieren zu können.
- _Reviewer_ will alle Prüfungsaufgaben sehen, um alle Bewertungen zu einer Prüfungsaufgabe reviewen zu können.

**Preconditions:**

- Der User ist im System angemeldet und hat eine Prüfung geöffnet.

**Postconditions:**

- Dem User werden alle Prüfungsaufgaben zur Prüfung angezeigt.

**Main Success Scenario:**

1. Das System lädt alle Prüfungsaufgaben.

#### _UC005: Prüfungsteilnahme korrigieren_
**Primary Actor:** Korrektor

**Stakeholders and Interests:**

- _Korrektor_ will eine Prüfungsteilnahme eines Studenten Aufgabe für Aufgabe korrigieren.

**Preconditions:**

- Der User ist im System angemeldet und hat die Prüfung geöffnet.

**Postconditions:**

- Dem User wurden alle gelöste Aufgaben eines Prüfungsteilnehmer zur Korrektur angezeigt, welche vom User korrigiert wurden.

**Main Success Scenario:**

1. Der User wählt aus der Liste aller Prüfungsteilnahmen eine aus und öffnet sie.
2. UC007 durchführen.
Wiederhole 2 bis alle Aufgaben korrigiert sind

**Level:**

include:

 - UC007

#### _UC006: Prüfungsaufgabe korrigieren_
**Primary Actor:** Korrektor

**Stakeholders and Interests:**

- _Korrektor_ will alle Lösungen aller Teilnehmer einer Aufgabe korrigieren.

**Preconditions:**

- Der User ist im System angemeldet und hat die Prüfung geöffnet.

**Postconditions:**

- Dem User wurden alle gelöste Aufgaben einer Aufgabe aller Prüfungsteilnehmer zur Korrektur angezeigt, welche vom User korrigiert wurden.

**Main Success Scenario:**

1. Der User wählt aus der Liste aller Prüfungsaufgaben eine aus und öffnet sie.
2. UC007 durchführen.
Wiederhole 2 bis alle Aufgaben korrigiert sind

**Level:**

include:

 - UC007


#### _UC007: Aufgabe korrigieren_
**Primary Actor:** Korrektor

**Stakeholders and Interests:**

- _Korrektor_ will eine gelöste Prüfungsaufgabe eines Teilnehmers korrigieren.

**Preconditions:**

- Der User ist im System angemeldet und kommt über UC005 oder UC006.

**Postconditions:**

- Der User hat die Aufgabe korrigiert.

**Main Success Scenario:**

1. Das System lädt die Aufgabe.
2. Der User korrigiert die Aufgabe, fügt einen Kommentar hinzu, bewertet sie und drückt auf nächste Aufgabe korrigieren.


#### _UC008: Prüfung für Review freigeben_
**Primary Actor:** Korrektor

**Stakeholders and Interests:**

- _Korrektor_ will nach der Korrektur der Prüfung die Prüfung für den Review freigeben.

**Preconditions:**

- Der User ist im System angemeldet und hat alle Prüfungsteilnahmen korrigiert.

**Postconditions:**

- Die Prüfung wurde einem _Reviewer_ zum Review freigegeben.

**Main Success Scenario:**

1. Der User drückt auf zum Review freigeben und wählt den Reviewer aus.
2. Das System weist dem Reviewer die Prüfung zum reviewen zu.

#### _UC009: Review abarbeiten_
**Primary Actor:** Korrektor

**Stakeholders and Interests:**

- _Korrektor_ will nach dem Review der Prüfung die Beanstandungen durchgehen.

**Preconditions:**

- Der User ist im System angemeldet.
- Der _Reviewer_ hat während dem Review eine Beurteilung abgelehnt.

**Postconditions:**

- Alle Beanstandungen des _Reviewer_ wurden durch den _Korrektor_ angenommen und umgesetzt oder verworfen.

**Main Success Scenario:**

1. Der User drückt auf zum Review abarbeiten.
2. Das System lädt die n-te Beanstandung.
3. Der User nimmt die Beanstandung an und korregiert die Bewertung oder verwirft sie.
Wiederhole 2 bis 3 bis alle Beanstandungen durchgearbeitet sind.


#### _UC011: Prüfungsteilnahme reviewen_
**Primary Actor:** Reviewer

**Stakeholders and Interests:**

- _Reviewer_ will alle Lösungen einer Prüfungsteilnahme reviewen.

**Preconditions:**

- Der User ist im System angemeldet und hat die Prüfung geöffnet.

**Postconditions:**

- Dem User wurden alle bewerteten Aufgaben aller Prüfungsteilnehmer zur Korrektur angezeigt, welche vom User gereviewt wurden.

**Main Success Scenario:**

1. Der User wählt aus der Liste aller Prüfungsteilnahmen eine aus und öffnet sie.
2. UC013 durchführen.
Wiederhole 2 bis alle Aufgaben korrigiert sind

**Level:**

include:

 - UC013

#### _UC012: Prüfungsaufgabe reviewen_
**Primary Actor:** Reviewer

**Stakeholders and Interests:**

- _Reviewer_ will alle Bewertungen aller Teilnehmer einer Aufgabe reviewen.

**Preconditions:**

- Der User ist im System angemeldet und hat die Prüfung geöffnet.

**Postconditions:**

- Dem User wurden alle bewerteten Aufgaben einer Aufgabe aller Prüfungsteilnehmer zum Review angezeigt, welche vom User reviewt wurden.

**Main Success Scenario:**

1. Der User wählt aus der Liste aller Prüfungsaufgaben eine aus und öffnet sie.
2. UC013 durchführen.
Wiederhole 2 bis alle Aufgaben korrigiert sind

**Level:**

include:

 - UC013

#### _UC013: Aufgabe reviewen_
**Primary Actor:** Reviewer

**Stakeholders and Interests:**

- _Reviewer_ will eine bewertete Prüfungsaufgabe eines Teilnehmers reviewen.

**Preconditions:**

- Der User ist im System angemeldet und kommt über UC011 oder UC012.

**Postconditions:**

- Der User hat die Aufgabe gereviewt.

**Main Success Scenario:**

1. Das System lädt die Aufgabe.
2. Der User reviewt die Aufgabe, akzeptiert oder lehnt die Bewertung ab und fügt optional einen Kommentar hinzu.

#### _UC014: Review abschliessen_
**Primary Actor:** Reviewer

**Stakeholders and Interests:**

- _Reviewer_ will nach der Durchführung des Reviews die Prüfung dem _Korrektor_ zur Überarbeitung oder zum Abschluss geben.

**Preconditions:**

- Der User ist im System angemeldet und hat einen Review aller Aufgaben durchgeführt.

**Postconditions:**

- Dem _Korrektor_ wurde die Prüfung zur Überarbeitung oder zum Abschluss zugewiesen.

**Main Success Scenario:**

1. Der User drückt auf Review abschliessen.
2. Das System weist der Prüfung den Status basierend auf dem Review-Ergebnis zu.

**Extension:**

<ol start=2>
  <li>
    <ol type="a">
      <li>Wenn alle Aufgaben akzeptiert wurden, erhält die Prüfung den Status `TODO Status`</li>
      <li>Wenn eine oder mehrere Aufgaben zurückgewiesen wurden, erhält die Prüfung den Status `TODO Status`</li>
    </ol>
  </li>
</ol>

#### _UC016: Prüfungskorrektur abschliessen_
**Primary Actor:** Korrektor

**Stakeholders and Interests:**

- _Korrektor_ will nach Erhalt und Bearbeitung des Reviews die Prüfung abschliessen und für die Notenfreigabe freigeben.

**Preconditions:**

- Der User ist im System eingeloggt, der _Reviewer_ hat UC014 durchgeführt und alle Beanstandungen des _Reviewers_ wurden überarbeitet.

**Postconditions:**

- Der User hat die Prüfung für die Notenfreigabe freigeben.

**Main Success Scenario:**

1. Der User drückt auf Korrektur abschliessen.
2. Das System setzt den Status der Prüfung auf `TODO Status`.

#### _UC017: Notenskala festlegen_
**Primary Actor:** Korrektor

**Stakeholders and Interests:**

- _Korrektor_ will die Notenskala der Prüfung festlegen.

**Preconditions:**

- Der User ist im System eingeloggt und hat eine Prüfung geöffnet.

**Postconditions:**

- Die Notenskale der Prüfung wurde gesetzt/bearbeitet.

**Main Success Scenario:**

1. Der User ändert die Notenskala der Prüfung.
2. Das System setzt den neuen Wert.

#### _UC018: Prüfung auswerten_
**Primary Actor:** Korrektor

**Stakeholders and Interests:**

- _Korrektor_ will eine Prüfung auswerten, um Notendurchschnitt und -median zu sehen.

**Preconditions:**

- Der User ist im System angemeldet und hat eine Prüfung geöffnet.

**Postconditions:**

- Dem User wird eine Auswertung (Notendurchschnitt und -median) über die Prüfung angezeigt.

**Main Success Scenario:**

1. Das System generiert die Auswertung.


#### _UC023: Prüfung auf Modulebene auswerten_
**Primary Actor:** Korrektor

**Stakeholders and Interests:**

- _Korrektor_ will eine Prüfung auf Modulebene mit vorherigen Durchführungen vergleichen, um zu sehen, ob Änderungen an der Moduldurchführung die Qualität des Unterrichts verbessert haben.

**Preconditions:**

- Der User ist im System angemeldet und hat eine Prüfung geöffnet.

**Postconditions:**

- Dem User wird eine Auswertung auf Modulebene über vorherigen Durchführungen angezeigt. Es ist ersichtlich, wie sich der Notendurchschnitt verändert hat.

**Main Success Scenario:**

1. Der User drückt auf mit vorherigen Durchführungen vergleichen.
2. Das System generiert die Auswertung.

#### _UC019: ToDo's anzeigen_
**Primary Actor:** Korrektor, Reviewer

**Stakeholders and Interests:**

- _Korrektor_ will auf der Übersicht/Dashboard eine Liste mit allen Prüfungen, welche korrigiert werden müssen, oder dessen Korrektur nach dem Review eine Überarbeitung erfodert.
- _Reviewer_ will auf der Übersicht/Dashboard eine Liste mit allen Prüfungen, welche gereviewt werden müssen.

**Preconditions:**

- Der User ist im System angemeldet.

**Postconditions:**

- Dem User werden alle relevanten Prüfungen angezeigt, welche noch eine Aktion erfordern.

**Main Success Scenario:**

1. Der User öffnet die Übersicht/Dashboard.
2. Das System lädt alle offenen Prüfungen, welche für den User relevant sind.

#### _UC022: online Prüfungseinsicht für Studenten_
**Primary Actor:** Student

**Stakeholders and Interests:**

- _Studenten_ will die Prüfung einsehen.

**Preconditions:**

- Der User hat eine Prüfung geschrieben, welche im Status `appeal` ist.

**Main Success Scenario:**

1. Der User öffnet die gewünschte Prüfung.
2. Das System zeigt dem User die 1. Aufgabe inklusiv Bewertung an.
3. Der User kann zur nächsten Aufgabe navigieren.
Wiederhole bis alle Aufgaben durchnavigiert sind.

## Erweiterungen

Im folgenden werden Use Cases kurz aufgelistet, welche das Projekt in seiner Funktionalität vervollständigen würden, um die angestrebte Vision zu erreichen.
Diese Use Cases sind nicht Teil des Scopes und werden bei Bedarf und freier Zeit eingeplannt und umgesetzt.
Grundsätzlich sollen sie eine Übersicht für folge Projekte bieten und aufzeigen, was alles noch möglich sein könnte.

### Diagramm
![](resources/UseCaseDiagramErweiterung.svg)

### Beschreibung

#### _UC101: Login durchführen_
Es soll möglich sein, dass sich User mit ihren HSR-Logindaten (Adunis, Moodle) einloggen können.

#### _UC102: CRUD Prüfung_
Die _Verwaltung_ erstellt initile Prüfungen mit allen Daten (Durchführungsdatum, etc.) und weist die Prüfung einem _Korrektor_ zu.
Dieser _Korrektor_ erfasst die Prüfungsaufgaben online. Die Prüfung lässt sich für die Durchführung ausdrucken.

#### _UC103: Prüfung scannen_
Nach der Durchführung der Prüfung kann die _Verwaltung_ oder der _Korrektor_ die Prüfung scannen.
Dabei wird UC104 und UC105 durchgeführt.

#### _UC104: Prüfungsaufgaben aufsplitten_
Die einglesenen Prüfungsaufgaben werden in einzelne Teilaufgaben aufgesplittet und so in System gespielt, damit bei der Korrektur einzelne Aufgaben durchkorrigiert werden können.

#### _UC105: Multiple-Choice-Aufgaben automatisch auswerten_
Multiple-Choice-Aufgaben werden vom System automatisch erkannt und ausgewertet.

#### _UC106: CRUD Korrektor_
Es soll möglich sein, _Korrektoren_ zu verwalten.

#### _UC107: CRUD Verwaltung_
Es soll möglich sein, die _Verwaltung_ zu verwalten.

#### _UC108: CRUD Reviewer_
Es soll möglich sein, die _Reviewer_ zu verwalten.

#### _UC108: CRUD Student_
Es soll möglich sein, die _Studenten_ zu verwalten.

#### _UC109: CRUD Benutzerrolle_
Es soll möglich sein, die Benutzerrollen zu verwalten.

#### _UC110: Rekurs durchführen_
Den _Studenten_ soll die Möglichkeit geboten werden, Rekurse anzustossen und durch den Rekursprozess geführt zu werden.diff

#### _UC111: Rekurs verwalten_
Die _Verwaltung_ hat die Möglichkeit laufende Rekurse zu verwalten.

#### _UC112: Aktion loggen_
Das System soll eine Historie über Änderungen an der Prüfung, Korrektur, Reviews und Rekurse erstellen

#### _UC113: _Notenexport durchführen_
Es soll möglich sein, einen Export im csv-Format mit den Daten (Student, Matrikel-Nr, erreichte Punktzahl, totale Punktzahl, Note) zu generieren.

## Korrektur-Prozess

![](resources/exam_states.svg)

Eine Prüfung durchläuft während der Korrektur verschiedene Zustände. Nach dem Import der Prüfungsdaten ist eine Prüfung in der Korrekturphase. Hier müssen alle Aufgaben korrigiert werden.

Sind alle Aufgaben korrigiert, so kann die Prüfung manuell in den nächsten Zustand, Review, überführt werden. Diese Phase läuft analog zur Korrektur ab, wobei der Reviewer bestehende Korrekturen anpassen und kommentieren kann.

Ist ein Review abgeschlossen (manueller Schritt), so muss der Korrektor diese Reviews wiederum annehmen oder ablehnen. Diese Phase nennt sich Approval. Aufgaben, welche im Review weder kommentiert noch korrigiert wurden, sind automatisch approved.

Sind alle Aufgaben approved, wird die Prüfung manuell in den Zustand appeal gesetzt. In dieser Phase verweilt eine Prüfung solange, bis die Rekursfrist abgelaufen oder ein allfälliger Rekurs abgeschlossen ist. Anschliessend wird eine Prüfung automatisch archiviert.

## Unterstützte Aufgabentypen
In einer ersten Phase unterstützen wir nur Textaufgaben. Diese haben eine textuelle Beschreibung und Antwort. In einer zweiten Phase (bei genügend Zeit oder nicht mehr im Rahmen des Engineering-Projekts) kommt der Aufgabentyp Multiple-Choice hinzu. Potentielle weitere Aufgabentypen (Bsp. mit Bildern oder Programmieraufgaben) müssen mit den Dozenten abgeklärt werden.

## Nicht-funktionale Anforderungen

Die nicht-funktionalen Anforderungen sind in den folgenden Kapiteln gemäss ISO 9126 gruppiert.

<figure>
<img src="resources/iso_9126_quality_model.png">
<figcaption>Quality-Modell Framework gemäss <a href="http://www.cse.unsw.edu.au/~cs3710/PMmaterials/Resources/9126-1%20Standard.pdf">ISO 9126</a></figcaption>
</figure>

Im Rahmen des Engineering-Projekts können nicht alle nicht-funktionalen Anforderungen komplett umgesetzt werden, da dies den Zeitrahmen sprengen würde.
Alle aufgelisteten nicht-funktionalen Anforderungen wurden beim Design beachtet. Im Engineering-Projekt werden aber nur die fett-markierten Anforderungen komplett umgesetzt.

### Funktionalität (Functionality)

1. **Während der Korrektur müssen alle Prüfungen anonym/pseudonym sein, damit Sympathie, Hautfarbe, Geschlecht oder Herkunft von Studierenden keinen Einfluss auf die Noten haben.** (suitability)
2. **Die resultierenden Note muss auf 2 Nachkommastellen genau angegeben und mathematisch auf bzw. abgerundet werden.** (accuracy)
3. **Die Berechnung der resultierenden Note muss genau aufgeschlüsselt dargestellt werden, damit deren Berechnung nachvollzogen werden kann.**  (accuracy)
4. **Es müssen weitere Notensysteme innerhalb von 5 Arbeitstagen integriert werden können, um das Produkt auch im Ausland vertreiben zu können.** (interoperability)
5. **Die Anbindung an eine andere Datenbank-Schnittstelle muss innerhalb von 10 Tagen realisiert werden können.** (interoperability)
6. **Änderungen an Noten und Korrekturen müssen immer mit Autor, Datum und getätigten Änderungen protokolliert werden, damit die Korrektur stets nachvollzogen werden kann.** (security)
7. **Review-Kommentare dürfen nicht für alle Benutzer (Bsp. Studenten) einsehbar sein, da diese nur dem internen Austausch im Review-Prozess dienen.** (security)
8. **Das System darf keine Passwörter speichern. Die Authentifizierung und Autorisierung erfolgt ausschliesslich über Drittsysteme (Bsp. LDAP), damit die Passwörter zentral verwaltet und abgesichert werden.** (security)
9. Bestehende Infrastruktur für Authentifizierung und Autorisierung (LDAP) muss ohne den Zwang, neue Gruppen zu definieren, verwendet werden können. (interoperability)
10. Die resultierenden Noten müssen über eine API exportiert werden können, damit die Anwendung mit bestehenden Systemen (Bsp. Adunis) kombiniert werden kann.  (interoperability)
11. Die Noten müssen beim Export signiert werden, damit deren Echtheit und Integrität verifiziert werden kann. (security)
12. Sämtliche Daten dürfen nur über verschlüsselte Verbindungen übertragen werden, die minimal den [Empfehlungen und Schlüssellängen des BSI (BSI TR-02102-1, Version 2017-01)](https://www.bsi.bund.de/SharedDocs/Downloads/DE/BSI/Publikationen/TechnischeRichtlinien/TR02102/BSI-TR-02102.pdf;jsessionid=D931A7BAEAA3051CEC548F944E39CA15.1_cid360?__blob=publicationFile&v=3) entsprechen. (security)
13. Die Daten müssen gemäss [DSG Art. 7](https://www.admin.ch/opc/de/classified-compilation/19920153/index.html#a7) vor unbefugtem Zugriff geschützt sein. (security)
14. Manipulationen auf der Datenbank müssen automatisch erkannt werden, um Manipulation über den Direktzugriff auf die Datenbank zu verhindern. (security)
15. Die digitale Archivierung muss gemäss dem [Reglement der HSR (Stand 15. Dezember 2016)](https://www.hsr.ch/index.php?eID=tx_nawsecuredl&u=0&g=0&t=1489320430&hash=f9d9dba4e0bff31b13f5819829b710d15dc25654&file=fileadmin/user_upload/customers/hsr/HSR-INTERN/Schulleitung/Reglemente/BSc_Pruefungsreglement_Neuausgabe_15._Dezember_2016.pdf) erfolgen. (compliance)

## Zuverlässigkeit (Reliability)

1. **Die Integrität der Daten darf ausnahmslos nie verletzt werden, da Prüfungen Dokumente mit rechtlicher Bindung sind.** (maturity, fault tolerance)
2. **Bei einem kompletten Systemausfall muss das Gesamtsystem innerhalb von 24 Stunden von einer Person wiederhergestellt werden können.** (recoverability)
3. **Ausnahmefehler dürfen das System nicht zum Absturz bringen.** (fault tolerance)
4. Bei kritschen Fehlern, wie beispielsweise fehlender Speicherplatz, wird die Systemadministration umgehend informiert und den Benutzern eine verständliche Meldung angezeigt. (fault tolerance)
5. 99% Verfügbarkeit während der Korrekturphase, da eine Prüfungskorrektur sonst nicht möglich ist. (maturity)
6. 200 oder mehr Nutzer (skalierbar) müssen die Anwendung gleichzeitig nutzen können, da die Prüfungskorrektur nur zweimal jährlich im gleichen Zeitraum stattfindet.

## Benutzbarkeit (Usability)
1. **Für den Korrektor muss nach der Anmeldung sofort ersichtlich sein, welche Aufgaben seine Aufmerksamkeit erfordern.** (understandability)
2. **Für das Verständnis der Bedienung der Anwendung muss nur der Ablauf des Prüfungsprozesses bekannt sein.** (learnability)
3. Zu jedem Zeitpunkt muss der Benutzer effizient Hilfe im Handbuch finden können (Bsp. Name der Aktivität gut sichtbar und Benutzerdokumentation nach Aktivitäten aufgebaut). (learnability)
4. **Für das ganze System muss ein einheitliches Stylesheet verwendet werden.** (operability)
5. Ein Hilfe-Button zeigt zu jeder Aktivität einen Link an die entsprechende Stelle der Benutzerdokumentation. (learnability)
6. Vorgenommene Korrekturen werden zwischengespeichert, damit beispielsweise bei einem Browser-Absturz nicht die Arbeit von >1 Minute verloren geht.

## Effizienz (Efficiency)
1. Das System muss innerhalb von max. 0.5 Sekunden nach Starten der Aktion durch den Nutzer antworten, auch wenn 200 Nutzer die Anwendung parallel verwenden. (time behaviour)

## Änderbarkeit (Maintainability)
1. **Jede Änderung an einer Korrektur muss protokolliert werden.** (analysability)
2. **Alle Fehler müssen automatisch protokolliert werden.** (analysability)
3. **Dynamische Konfigurationen von Prüfungsreglementen / Modulstrukturen für andere Hochschulen müssen innerhalb von 30 Arbeitstagen eingefügt werden können.** (changability)
4. **Neue Prüfungsaufgabentypen (Bsp. Multiple-Choice) müssen innerhalb von 10 Arbeitstagen eingeführt werden können.**
5. **Die Business-Logik und alle Use-Cases müssen mit automatischen Unit-Tests abgedeckt sein**. (testability)
6. **Alle Use-Cases des Web-Interfaces müssen über automatische Browser-Tests abgedeckt sein**. (testability)
7. Automatische Updates müssen ohne manuelles Eingreifen eingespielt werden können (stability)

## Übertragbarkeit (Portability)
1. **Das System muss auch als Docker-Image ausgeliefert werden, um eine schnelle Installation zu ermöglichen.** (installability)
2. **Das System verwendet keine proprietären Datenbankfunktionen, um deren Austauschbarkeit zu gewährleisten.** (comformance)
3. Die Software muss auf Linux und Windows-Servern deployed werden können. (adaptability)
4. Die Daten müssen in einem Format vorgehalten bzw. exportiert werden, welches auch von anderen Systemen gelesen werden kann (Bsp. JSON/CSV). (replaceability)
