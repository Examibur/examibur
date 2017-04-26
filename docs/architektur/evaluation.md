---
layout: default
title: Framework Evaluation
---

# Einführung
## Zweck

Dieses Dokument beschreibt, wie und warum bestimmte Frameworks für das Projekt ausgewählt wurden.

## Gültigkeitsbereich

Der Gültigkeitsbereich beschränkt sich auf die Projektdauer vom 20.02.17 bis 02.06.17. Während dieser Zeit wird das Dokument laufend aktualisiert und stellt zu jedem Zeitpunkt einen genauen Überblick über das Projekt zur Verfügung.

## Referenzen

In der nachfolgenden Tabelle sind alle Dokumente und Links aufgelistet, welche für das Projekt von Relevanz sind. Diese Liste wird laufend auf dem aktuellen Stand gehalten.

| **Name**                          | **Referenz**                                                                                                                                                                                                                                         |
| --------------------------------- | ----------------------------- |
| Freemarker                        | [http://freemarker.org/](http://freemarker.org/)  |
| velocity                          | [http://velocity.apache.org/](http://velocity.apache.org/)  |

# Framework Evaluation

## Frontend Framework
Für das Frontend wurden die Frameworks *Spring Boot*, *Play* und *Spark* in Betracht gezogen. Nachfolgend werden einige Vor- und Nachteile der einzelnen Frameworks, bezogen auf unser Projekt, aufgelistet. Anschliessend wird ein Framework für unser Projekt ausgewählt.

### Spring Boot
 Vorteile                               | Nachteile
----------------------------------------|--------------------------------
 Etabliertes Framework für Web-Projekte | Sehr umfangreich und komplex
 Viele Funktionen out-of-the-box oder gut integriert mit anderen Lösungen | Niemand vom Projektteam hat Erfahrung mit Spring
 Mit Spring Boot Starters schnell up-and-running | Dadurch aber nur schwer durchschaubar (Convention over Configuration)
  Grosse Community |

### Play Framework
Vorteile                                | Nachteile
----------------------------------------|--------------------------------
Schlanker als Spring | Stark auf Scala konzipiert
Grosse und stetig wachsende Community | Niemand vom Projektteam hat Erfahrung mit Play
- | Wenig unterstützung für weitere Funktionalität, z.B. LDAP Integration

### Spark
Vorteile                                | Nachteile
----------------------------------------|--------------------------------
Sehr schlankes Framework | Wenig Funktionalität out-of-the-box
Einfach zu verstehende Konzepte | Für Testing, Template-Engine usw. müssen weitere Frameworks benutzt werden
Unterstützung für viele andere Frameworks | Architektur muss selbst sauber aufgebaut werden

### Entscheid
Aufgrund der Einfachheit und der Möglichkeit, sich schnell in das Framework einarbeiten zu können, haben wir uns für **Spark** entschieden. Für unsere Anforderungen genügt ein schlankes Framework, das wir mit u.a. Testing-Frameworks und OR-Mapper beliebig erweitern können.

Als Einschränkung soll beachtet werden, dass z.B. die Routes und Controller in der Dokumentation von Spark nicht sauber getrennt sind. Diese Trennung muss selbst realisiert werden und ist wichtig für den Aufbau der Architektur.

## Template-Engine
Für die Seitenbeschreibung wird das Konzept der Templates von Freemarker verwendet. Es gibt ein Basis-Template, welches das Layout und die Strukturierung vorgibt. Alle Seiten der Applikation bauen auf diesem Basis-Template auf. Dadurch ist gewährleistet, dass alle Seiten mit der gleichen Struktur und einheitlichem Aussehen daher kommen.

Zur Auswahl standen [Velocity](http://velocity.apache.org/) und [Freemarker](http://freemarker.org/), welche beide von Spark empfohlen werden.
Der Entscheid fiel auf Freemarker, weil Velocity seit 2010 keine neue Version nachgeliefert hat und wir so davon ausgehen, dass es in naher Zukunft eingestellt werden könnte.
