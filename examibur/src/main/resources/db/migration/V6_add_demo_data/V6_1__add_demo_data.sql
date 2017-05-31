INSERT INTO module_t (name) values
	('CN1');

/* ID = 9 */
INSERT INTO exam_t (allowedtimeinmin, duedate, state, exam_authorid, exam_moduleid, creationdate, lastmodified) values
	(120, '2017-01-27', 'CORRECTION', 9, 10, '2017-01-12', '2017-01-30');

INSERT INTO allowedutility_t (exam_examid, allowedutility) values
	(9, 'keine (closed book)');

/* ID = 19-21 */
INSERT INTO examparticipation_t (participationdate, pseudonym, examparticipation_exam, examparticipation_participantid) values
	('2017-01-27', 'Anonymer Panda', 9, 1),
	('2017-01-27', 'Anonymer Elefant', 9, 2),
	('2017-01-27', 'Anonyme Gazelle', 9, 3);

/* example solutions (ID = 73-75) */
INSERT INTO solution_t (solution_type, solutiontext) values
	('text', 'Damit alle Benutzer sich in fairer Weise die Bandbreite des Netzwerkes teilen können. Je kleiner die Segmente, desto kleiner die Verzögerung beim Zugriff.'),
	('text', 'Dem Layer 6 oder Presentation Layer.'),
	('text', 'Alle früheren Kommunikationsmedien waren Punkt-zu-Punkt-Verbindungen, während Ethernet ein Multi-Point, Shared Medium ist.');

/* participant solutions (ID: 76-84) */
INSERT INTO solution_t (solution_type, solutiontext) values
	('text', 'Damit alle Benutzer sich in fairer Weise die Bandbreite des Netzwerkes teilen können. Je kleiner die Segmente, desto kleiner die Verzögerung beim Zugriff.'),
	('text', 'Layer 6 (Transport Layer)'),
	('text', 'Alle früheren Kommunikationsmedien waren Punkt-zu-Punkt-Verbindungen, während Ethernet ein Multi-Point, Shared Medium ist.'),
	('text', 'Je kleiner die Segmente, desto kleiner die Verzögerung beim Zugriff.'),
	('text', 'Ich weiss es nicht.'),
	('text', 'Alle früheren Kommunikationsmedien waren Punkt-zu-Punkt-Verbindungen, während Ethernet ein Multi-Point, Shared Medium ist.'),
	('text', 'Damit alle Benutzer sich in fairer Weise die Bandbreite des Netzwerkes teilen können.'),
	('text', 'Layer 5'),
	('text', 'Alle früheren Kommunikationsmedien waren Punkt-zu-Punkt-Verbindungen, während Ethernet ein Multi-Point, Shared Medium ist.');

/* ID = 19-21 */
INSERT INTO exercise_t (exercise_type, maxpoints, exercise_examid, exercise_graderid, exercise_reviewerid, exercise_examplesolutionid, orderinexam, taskdescription, title) values
	('text', 2, 9, 9, 5, 73, 1, 'Warum werden Datenströme und grosse Meldungen bei der Übertragung über ein Netzwerk in Segmente aufgeteilt?', 'Segmentierung von Datenströmen'),
	('text', 2, 9, 9, 5, 74, 2, 'Welchem OSI Layer ist die Wandlung von einem 8 Bit pro Byte binären Datenformat in ein 6 Bit pro Byte Base64 Format vor der Übertragung zuzuordnen?', 'OSI Layer'),
	('text', 2, 9, 9, 5, 75, 3, 'Warum musste wegen Ethernet der OSI Data Link Layer in die MAC und LLC Sub-Layer unterteilt werden?', 'Ethernet');

/* ID = 55-63 */
INSERT INTO exercisesolution_t (isdone, exercisesolution_exerciseid, exercisesolution_participationid, exercisesolution_participantsolutionid) values
	(TRUE, 19, 19, 76),
	(TRUE, 20, 19, 77),
	(TRUE, 21, 19, 78),
	(TRUE, 19, 20, 79),
	(TRUE, 20, 20, 80),
	(TRUE, 21, 20, 81),
	(TRUE, 19, 21, 82),
	(FALSE, 20, 21, 83),
	(FALSE, 21, 21, 84);

INSERT INTO exercisegrading_t (createdinstate, creationdate, isfinalgrading, exercisegrading_authorid, exercisegrading_exercisesolutionid, points, comment, reasoning) values
	('CORRECTION', '2017-01-30', TRUE, 9, 55, 2, 'Korrekt!', ''),
	('CORRECTION', '2017-01-30', TRUE, 9, 56, 1, 'Die Nummer stimmt. Layer 6 ist aber nicht der Transport Layer!', ''),
	('CORRECTION', '2017-01-30', TRUE, 9, 57, 2, 'Korrekt!', ''),
	('CORRECTION', '2017-01-30', TRUE, 9, 58, 1, 'Stimmt, aber wieso werden sie segmentiert?', ''),
	('CORRECTION', '2017-01-30', TRUE, 9, 59, 0, 'Keine gültige Antwort.', ''),
	('CORRECTION', '2017-01-30', TRUE, 9, 60, 2, 'Korrekt!', ''),
	('CORRECTION', '2017-01-30', TRUE, 9, 61, 1, 'Stimmt, aber wieso brauche ich dafür eine Segmentierung?', '');