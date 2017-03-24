---
layout: default
title: Software Architektur
---

# Einführung
## Zweck

Ziel des Dokuments ist es, dem Leser einen Überblick über den Aufbau der Architektur und die Entscheidungen die dazu geführt haben, zu geben.

## Gültigkeitsbereich

Der Gültigkeitsbereich beschränkt sich auf die Projektdauer vom 20.02.17 bis 02.06.17. Während dieser Zeit wird das Dokument laufend aktualisiert und stellt zu jedem Zeitpunkt einen genauen Überblick über das Projekt zur Verfügung.

## Referenzen

In der nachfolgenden Tabelle sind alle Dokumente und Links aufgelistet, welche für das Projekt von Relevanz sind. Diese Liste wird laufend auf dem aktuellen Stand gehalten.

| **Name**                          | **Referenz**                                                                                                                                                                                                                                         |
| --------------------------------- | ----------------------------- |

# Software Architektur

## Programmkonstruktbildung mit Schichtenmodellbezug

![](resources/softwareArchitektur/schichtenDiagram.svg)

In diesem Abschnitt wird vertiefter in die einzelnen Teile bis auf die Top-Level-Konstrukte eingegangen. Nachfolgend werden die einzelnen Schichten und Konstrukte weiter ausgeführt. **Grau markierte Schichten sind nicht Scope des Projekts und werden weggelassen. So erfolgt der Zugriff des UI direkt auf die Business-Schicht und auf ein Benutzeraktion-Tracking für Auditing-Zwecke wird verzichtet.**

### ui
#### Application

Application ist der zentrale Einstiegspunkt der Spark-Applikation.
Das Routing der Requests auf die einzelnen Controller wird in dieser Schicht durchgeführt.

#### webapp
In dieser Schicht sind die dargestellten Elemente enthalten, sowie de Resourcen (Bilder, JavaScript und sprachabhängige Textfiles) und die Konfiguration der Webapplikation.

Dazu gehören Freemarker-Templates und die Stylesheet Layout Defintionen.

#### controller
Die clientspezifische Business Logik ist in dieser Schicht enthalten, so werden unteranderem die Serviceaufrufe auf die Business-Tier abgewickelt.
Dazu gehört auch das Aufbereiten der Daten in die clientinterne Datenmodelle, das Überführen in die zuständigen Views, sowie das Abarbeiten von Events.

#### view
Die Views, welche das Aufbereiten und Rendering der Freemarker-Templates durchführen, sind hier enthalten.

#### model
Enthält das clientinterne Datenmodell.

#### util
Darin sind Hilfsklassen enthalten, die eine spezifische Funktionalität kapseln, wie zum Beispiel i18n, Konvertierung, Formatierung, Validierung, Exception Handling, etc.

### service
Diese Schicht agiert als Proxy zwischen der clientspezifischen Business Logik und der Business Schicht. **In einem ersten Schritt wird diese Schicht weggelassen werden.** Falls die Applikation auf eine verteilte Applikation umgestellt werden muss, sei dies aus Performance- oder Sicherheits-Gründen, kann diese Schicht schnell dazwischen geschaltet werden.

Es würde sich dabei um Proxy-Methoden halten, die die Aufrufe einfach in die Business-Schicht weiterleiten und keine eigene Logik enthalten.

### business
Die Business Logik der eigenen Services und für die Aufbereitung von Daten der Datenbank ist in dieser Schicht enthalten.

#### mapper
Das Mapping der Domain-Objekte aus der Integration-Schicht in die Business-Objekte ist hier enthalten.

#### bo
Business-Objekte der Business-Schicht.

### messaging
Enthält die Queue für das Tracking der Benutzeraktionen. Jede ausgeführte Aktion erzeugt eine Nachricht, die in diese Queue gestellt wird.

In einem ersten Schritt wird auf den Einsatz einer Message-Queue verzichtet.

Lösungen wie ActiveMQ kann hier in Betracht gezogen werden.

### integration
In dieser Schicht wird das objektrelationale Mapping der zu persistierenden Daten durchgeführt. Für das Mapping wird EclipseLink verwendet.

DAO's sind für das Lesen und Schreiben auf den Datenbank verantwortlich.

#### do
Hier sind die Domain-Objekte enthalten, welche die Datenbanken abbilden.

#### sql
In dieser Schicht werden die einzelnen Datenbank-Patches gespeichert. Dies ermöglicht eine automatische Aktualisierung der Datenbank auf dem lokalen Entwicklungssystem und der Produktion während dem Build-Prozess.

## Design der Programmkonstrukte

### Prorgrammkonstrukt UI Tier
> TODO

### Prorgrammkonstrukt Business Tier
> TODO

### Prorgrammkonstrukt Integration Tier
> TODO

#### Datenmodell
>  TODO

## Zusammenspiel der Programmkonstrukte
In diesem Kapitel wird das Zusammenspiel der einzelnen Programmkonstrukte beschrieben. Dazu gehört auch der Zugriff auf die eigenen Datenbank. Das Zusammenspiel wird anhand von UML-Sequenzdiagrammen dargestellt.

![](resources/softwareArchitektur/zusammenspielProgrammkonstrukte.svg)

## Exception-Handling im Web-Teil
> TODO

## Physisches Datenmodell
> Datenmodell mit FK etc.

## Entscheidungsfindung
### Frontend
#### Framework
Für das Backend wurden die Frameworks *Spring Boot*, *Play* und *Spark* in Betracht gezogen. Nachfolgend werden einige Vor- und Nachteile der einzelnen Frameworks, bezogen auf unser Projekt, aufgelistet. Anschliessend wird ein Framework für unser Projekt ausgewählt.

##### Spring Boot
 Vorteile                               | Nachteile
----------------------------------------|--------------------------------
 Etabliertes Framework für Web-Projekte | Sehr umfangreich und komplex
 Viele Funktionen out-of-the-box oder gut integriert mit anderen Lösungen | Niemand vom Projektteam hat Erfahrung mit Spring
 Mit Spring Boot Starters schnell up-and-running | Dadurch aber nur schwer durchschaubar (Convention over Configuration)
  Grosse Community |

##### Play Framework
Vorteile                                | Nachteile
----------------------------------------|--------------------------------
Schlanker als Spring | Stark auf Scala konzipiert
Grosse und stetig wachsende Community | Niemand vom Projektteam hat Erfahrung mit Play
- | Wenig unterstützung für weitere Funktionalität, z.B. LDAP Integration

##### Spark
Vorteile                                | Nachteile
----------------------------------------|--------------------------------
Sehr schlankes Framework | Wenig Funktionalität out-of-the-box
Einfach zu verstehende Konzepte | Für Testing, Template-Engine usw. müssen weitere Frameworks benutzt werden
Unterstützung für viele andere Frameworks | Architektur muss selbst sauber aufgebaut werden

#### Entscheid
Aufgrund der Einfachheit und der Möglichkeit, sich schnell in das Framework einarbeiten zu können, haben wir uns für **Spark** entschieden. Für unsere Anforderungen genügt ein schlankes Framework, das wir mit u.a. Testing-Frameworks und OR-Mapper beliebig erweitern können.

Als Einschränkung soll beachtet werden, dass z.B. die Routes und Controller in der Dokumentation von Spark nicht sauber getrennt sind. Diese Trennung muss selbst realisiert werden und ist wichtig für den Aufbau der Architektur.

### Backend
> TODO
