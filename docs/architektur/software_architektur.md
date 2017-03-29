---
layout: default
title: Software Architektur
---

# Software Architektur

## Backend
### Framework
Für das Backend wurden die Frameworks *Spring Boot*, *Play* und *Spark* in Betracht gezogen. Nachfolgend werden einige Vor- und Nachteile der einzelnen Frameworks, bezogen auf unser Projekt, aufgelistet. Anschliessend wird ein Framework für unser Projekt ausgewählt.

#### Spring Boot
 Vorteile                               | Nachteile 
----------------------------------------|--------------------------------
 Etabliertes Framework für Web-Projekte | Sehr umfangreich und komplex
 Viele Funktionen out-of-the-box oder gut integriert mit anderen Lösungen | Niemand vom Projektteam hat Erfahrung mit Spring 
 Mit Spring Boot Starters schnell up-and-running | Dadurch aber nur schwer durchschaubar (Convention over Configuration)
  Grosse Community | 

#### Play Framework
Vorteile                                | Nachteile 
----------------------------------------|--------------------------------
Schlanker als Spring | Stark auf Scala konzipiert
Grosse und stetig wachsende Community | Niemand vom Projektteam hat Erfahrung mit Play
- | Wenig unterstützung für weitere Funktionalität, z.B. LDAP Integration

#### Spark
Vorteile                                | Nachteile 
----------------------------------------|--------------------------------
Sehr schlankes Framework | Wenig Funktionalität out-of-the-box
Einfach zu verstehende Konzepte | Für Testing, Template-Engine usw. müssen weitere Frameworks benutzt werden
Unterstützung für viele andere Frameworks | Architektur muss selbst sauber aufgebaut werden

### Entscheid
Aufgrund der Einfachheit und der Möglichkeit, sich schnell in das Framework einarbeiten zu können, haben wir uns für **Spark** entschieden. Für unsere Anforderungen genügt ein schlankes Framework, das wir mit u.a. Testing-Frameworks und OR-Mapper beliebig erweitern können.

Als Einschränkung soll beachtet werden, dass z.B. die Routes und Controller in der Dokumentation von Spark nicht sauber getrennt sind. Diese Trennung muss selbst realisiert werden und ist wichtig für den Aufbau der Architektur.

## Frontend
> TODO

## Design der Programmkonstrukte
### Programmkonstrukt Domain-Schicht
#### Datenmodell
![](resources/softwareArchitektur/datamodel.png)
