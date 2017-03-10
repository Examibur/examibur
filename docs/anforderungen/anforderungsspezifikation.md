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

Die Ansprüche der Stakeholder werden als Uses Stories erfasst, um so den Sinn und Zweck von Examibur klar aufzuzeigen und den Mehrwert für alle Stakeholder aufzuzeigen.

### Lehranstalt

#### **US101** Qualitätssicherung

Als Lehranstalt möchte ich die Qualität der Prüfungskorrekturen hochhalten, um Rekurskosten zu sparen.

#### **US102** Rekurszprozess

Als Lehranstalt möchte ich den Rekursprozess abgebildet haben, um so die rechtlichen Vorgaben einhalten zu können.

### Dozent
#### **US201** Prüfung erstellen

Als Dozent möchte ich Prüfungen online erstellen können, um so eine einfache, zentrale und für Nachfolge transparente Ablage der Prüfung zu erreichen.

#### **US202** Qualität aufrecht erhalten

Als Dozent möchte ich Prüfungskorrekturen reviewen lassen, um so die Qualität und Fairness der Bewertungen aufrecht zu halten und allfällige Flücktigkeitsfehler zu vermeiden.

#### **US203** Prüfung online korrigieren

Als Dozent möchte ich Prüfungen online korrigieren, um so den Papierkrieg einzudämmen, Verluste vorzubeugen und Transparenz zu gewährleisten.

### Student
#### **US301** anoynme Korrektur

Als Student möchte ich einen anonyme Korrektur der Prüfung erhalten, um so eine faire Beurteilung aller Studenten sicherzustellen.

#### **US302** Prüfungsreview

Als Student möchte ich eine zweite Beurteilung der Korrektur nach dem 4-Augen-Prinzip, um so die Qualität der Korrekturen hoch und fair zu halten.

#### **US303** Prüfungseinsicht

Als Student möchte ich die Prüfung inklusiv Korrektur nach Freischaltung der Noten einsehen, um so das Zustandkommen der Note zu verstehen und um Wissenslücken am Ende des Moduls zu identifizieren.

#### **US304** Rekursprozess

Als Student möchte ich den Rekursprozess bei Ungereinheimten anstossen, um so alle Fristen und Bedingungen für einen gültigen Rekurs einhalten zu können.

## Use Cases

* Übersicht/Dashboard anzeigen //TODO auf Wireframes warten
  * Liste der zu korrigierende Prüfungen anzeigen
  * zu korrigierende Prüfung öffnen
  * Module des Dozent anzeigen
  * vergangenes Moduldurchführungen öffnen
  * Statistik (Modul/Prüfungsebene/im Vergleich zu vorherigen Jahrgängen) über die Prüfungen dieser Moduldurchführung anzeigen
* Prüfung korrigieren
  * gleiche Prüfungsaufgaben nacheinander durchkorrigieren
* Prüfung-Notenskala festlegen
* Prüfung nach Korrektur reviewen (4-Augen-Prinzip)
* Prüfung abschliessen
  * sobald alle Rekursfristen durch sind
* Notenexport durchführen
  * Um eine einfache Migrierung in anderen Systeme zu gewährleisten
* Prüfung an Student online zur Einsicht freigeben
  * einzelne korrigierte Aufgaben können durchgesehen werden

## Erweiterungen

Im folgenden werden Use Cases kurz aufgelistet, welche das Projekt in seiner Funktionalität vervollständigen würden, um die angestrebte Vision zu erreichen.
Diese Use Cases sind nicht Teil des Scopes und werden bei Bedarf und freier Zeit eingeplannt und umgesetzt.
Grundsätzlich sollen sie eine Übersicht für folge Projekte bieten und aufzeigen, was alles noch möglich sein könnte.

* Login durchführen
* Registrierung durchführen
* CRUD Dozent
* CRUD Korrektor
* CRUD Student
* CRUD Benutzerrolle
* Rekurs-Prozess abbilden
* Prüfung scannen
  * Multiple-Choice-Aufgabe automatisch auswerten
  * Prüfungsaufgaben aufsplitten
* CRUD Prüfung
* Aktionen loggen
  * Historie über Änderungen an der Prüfung und Korrektur erstellen

## User Stories
