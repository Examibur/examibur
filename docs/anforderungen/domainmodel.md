---
layout: default
title: Domain Model
---

# Domain Model

## Domain Model UML
![](resources/domainmodel.svg)

## Erläuterungen
- Unter `Exam` versteht man eine Prüfungsdurchführung, für die es ein oder mehrere gelöste Prüfungen der Prüfungsteilnehmer (`ExamParticipant`) gibt.
- Eine Prüfungsteilnahme (`ExcerciseParticipation`) ist eine gelöste Prüfung eines Prüfungsteilnehmers.
- Die Beziehungen `Person` zu `Author`, `Grader` und den anderen Rollen wird mit *1 zu n* modelliert, da die Rollen nicht disjunkt sind. Beispielsweise kann ein Reviewer auch ein Korrektor (`Grader`) sein, oder ein Prüfungsteilnehmer kann später einmal zu einem Korrektor werden.
- Zu jeder gelösten Aufgabenstellung `ExerciseSolution` können mehrere Korrekturen (`ExcerciseGrading`) erstellt werden. Dies ist normalerweise eine Korrektur vom Korrektor und eventuell eine zweite Korrektur vom Reviewer. Dazu kann ein Grund angegeben werden, der nur intern sichtbar ist (während der Kommentar auch dem Teilnehmer gezeigt wird). Am Schluss entscheidet der Korrektor, welche Benotung der Aufgabe die endgültige Bewertung ist (*final grading*). Bei jeder Aufgabenkorrektur wird mitgespeichert, in welcher Phase der Prüfungskorrektur (z.B. `Correction`, `Review`) diese vorgenommen wurde.
- Die Lösungen einer Prüfungsaufgabe können von verschiedenen Subtypen von `Solution` sein. Eine Prüfungsaufgabe hat genau eine Musterlösung. Diese muss vom gleichen (Sub-)Typ sein wie die Lösungen der Prüfungsteilnehmer.
- Das Flag `isDone` bei einer Aufgabenlösung (`ExcerciseSolution`) wird bei jeder Phase des Prozesses neu gesetzt. In der Korrektur-Phase zum Beispiel zeigt es an, ob die Aufgabe bereits korrigiert wurde, während es in der Approval-Phase signalisiert, ob es einen Einwand des Reviewers gibt und dieser vom Korrektor bearbeitet werden muss. Aufgrund dieses Felds werden die "ToDo's" erstellt.
