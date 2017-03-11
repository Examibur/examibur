---
layout: default
title: Anforderungsspezifikation
---
# Einführung

## Zweck

## Gültigkeitsbereich

## Referenzen

# Allgemeine Beschreibung

## Einschränkungen

## Abhängigkeiten

# Funktionale Anforderungen

## Einschränkungen
Die Beschreibung der folgenden Kapitel wurde basierend auf der Vision von Examibur erfasst. Im Unterkapitel Use Cases ist klar ersichtlich, welche Funktionalität Scope des Projekts Examibur ist. Um die Applikation offen für folge Projekte zu halten, wurden die anderen Anforderungen zustätzlich spezifiziert (im Brief-Format), um sich keine Hindernisse einzubauen, welche eine allfällige Erweiterung erschweren könnten.

## Aktoren

| Aktor                         | Beschreibung  |
|-------------------------------|---------------|
| **Korrektor**                 | Ein Korrektor erstellt und verwaltet Prüfungen. Nach Durchführung einer Prüfung hat er die Möglichkeit die Prüfungen mit einem Scanner in das System zu spielen. Sobald die Prüfungen im System erfasst und verarbeitet wurden, kann er gleiche Prüfungsaufgaben einzeln durchgehen und korrigieren. Der Korrektor kann auch eine Prüfung eines Studenten ganz durchkorrigieren. Sind alle Aufgaben korrigiert, kann die Prüfung in den Reviewprozess übergeben werden. Aufgabenbewertungen, welche vom Reviewer zurückgewiesen werden, müssen überarbeitet werden, bevor die Prüfung in den Status korrigiert überführt werden kann. |
| **Reviewer**                  | Sobald der Korrektor eine Prüfung einem Reviewer zuordnet, kann der Reviewer Aufgabe für Aufgabe durchgehen und die Aufgabenwertungen des Korrektor überprüfen, welche er akzeptieren kann oder per Kommentar an den Korrektor korrigiert zurückweisen kann.	|
| **Verwaltung**                | Die Verwaltung der Lehranstalt erstellt initiale Prüfungen mit allen Terminen und weist diese den Korrektoren zur Erstellung der Prüfung zu. Die Verwaltung kann den Status der einzelnen Prüfung einsehen und hat die Möglichkeit die korrigierten Prüfung nach Abschluss der Reviewphase einzusehen und bei Bedarf zu exportieren. Falls ein Student den Rekursprozess startet, übernimmt die Verwaltung die Verabeitung dieses Rekurses. 	|
| **Student**                   | Der Student hat die Möglichkeit die korrigierte Prüfung nach Freischaltung der Noten einzusehen und bei Ungereinheimten den Rekursprozess anzustossen.  	|

## Stakeholder

Die Ansprüche der Stakeholder werden als Uses Stories erfasst, um so den Sinn und Zweck von Examibur und den Mehrwert für alle Stakeholder aufzuzeigen.

### Lehranstalt
#### _US101: Qualitätssicherung_

Als Lehranstalt möchte ich die Qualität der Prüfungskorrekturen hochhalten, um Rekurskosten zu sparen.

#### _US102: Rekurszprozess_

Als Lehranstalt möchte ich den Rekursprozess abgebildet haben, um so die rechtlichen Vorgaben einhalten zu können.

### Dozent
#### _US201: Prüfung erstellen_

Als Dozent möchte ich Prüfungen online erstellen können, um so eine einfache, zentrale und für Nachfolge transparente Ablage der Prüfung zu erreichen.

#### _US202: Qualität aufrechterhalten_

Als Dozent möchte ich Prüfungskorrekturen reviewen lassen, um so die Qualität und Fairness der Bewertungen aufrecht zu halten und allfällige Flücktigkeitsfehler zu vermeiden.

#### _US203: Prüfung online korrigieren_

Als Dozent möchte ich Prüfungen online korrigieren, um so den Papierkrieg einzudämmen, Verluste vorzubeugen und Transparenz zu gewährleisten.

### Student
#### _US301: anoynme Korrektur_

Als Student möchte ich einen anonyme Korrektur der Prüfung erhalten, um so eine faire Beurteilung aller Studenten sicherzustellen.

#### _US302: Prüfungsreview_

Als Student möchte ich eine zweite Beurteilung der Korrektur nach dem 4-Augen-Prinzip, um so die Qualität der Korrekturen hoch und fair zu halten.

#### _US303: Prüfungseinsicht_

Als Student möchte ich die Prüfung inklusiv Korrektur nach Freischaltung der Noten einsehen, um so das Zustandkommen der Note zu verstehen und um Wissenslücken am Ende des Moduls zu identifizieren.

#### _US304: Rekursprozess_

Als Student möchte ich den Rekursprozess bei Ungereinheimten anstossen, um so alle Fristen und Bedingungen für einen gültigen Rekurs einhalten zu können.

## Use Cases

### Diagramm
![](resources/UseCaseDiagram.svg)

### Beschreibung

Im folgenden sind alle Use Cases im fully dressed-Format aufgelistet, welche Teil des Scopes des Projekts sind.

#### _UC001: Prüfungen anzeigen_

#### _UC002: Prüfung öffnen_
include 3, 4

#### _UC003: Prüfungsteilnahmen anzeigen_

#### _UC004: Prüfungsaufgaben anzeigen_

#### _UC005: Prüfungsteilnahme korrigieren_
include 7

#### _UC006: Prüfungsaufgabe korrigieren_
include 7

#### _UC007: Aufgabe korrigieren_

#### _UC008: Prüfung für Review freigeben_

#### _UC009: Prüfungsreviews anzeigen_

#### _UC010: Prüfungsreview öffnen_
include 11, 12

#### _UC011: Prüfungsteilnahme reviewen_
include 13

#### _UC012: Prüfungsaufgabe reviewen_
include 13

#### _UC013: Aufgabe reviewen_

#### _UC014: Prüfung zur Überarbeitung freigeben_

#### _UC016: Prüfungskorrektur abschliessen_

#### _UC017: Notenskala festlegen_

#### _UC018: Prüfung auswerten_
Statistik (Modul/Prüfungsebene/im Vergleich zu vorherigen Jahrgängen) über die Prüfungen dieser Moduldurchführung anzeigen

#### _UC019: ToDo's anzeigen_

#### _UC020: Prüfungsteilnahme an Student freigeben_

#### _UC021: Notenexport durchführen_
Um eine einfache Migrierung in anderen Systeme zu gewährleisten


## Erweiterungen

Im folgenden werden Use Cases kurz aufgelistet, welche das Projekt in seiner Funktionalität vervollständigen würden, um die angestrebte Vision zu erreichen.
Diese Use Cases sind nicht Teil des Scopes und werden bei Bedarf und freier Zeit eingeplannt und umgesetzt.
Grundsätzlich sollen sie eine Übersicht für folge Projekte bieten und aufzeigen, was alles noch möglich sein könnte.

### Diagramm
![](resources/UseCaseDiagramErweiterung.svg)

### Beschreibung

### _UC101: Login durchführen_

### _UC101: Registrierung durchführen_

### _UC103: CRUD Prüfung_
darf auch Verwaltung

### _UC104: Prüfung scannen_
include UC105, UC106

### _UC105: Multiple-Choice-Aufgaben automatisch auswerten_

### _UC106: Prüfungsaufgaben aufsplitten_

### _UC108: CRUD Korrektor_

### _UC109: CRUD Verwalter_

### _UC110: CRUD Student_

### _UC111: CRUD Benutzerrolle_

### _UC112: Rekurs durchführen_

### _UC113: Rekurs verwalten_

### _UC114: Aktion loggen_
Historie über Änderungen an der Prüfung und Korrektur erstellen
