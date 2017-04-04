INSERT INTO user_t (firstname, lastname) values
	('Maximilian', 'Mueller'),
	('Ralph', 'Maur'),
	('Katja', 'Pfeiffer'),
	('Jürgen', 'König'),
	('Stefan', 'Böhm'),
	('Christina', 'Theiss'),
	('Matthias', 'Schuhmacher'),
	('Dominik', 'Ostermann'),
	('Uwe', 'Baer'),
	('Florian', 'Forster');

INSERT INTO module_t (name) values
	('SE1'),
	('SE2'),
	('InfSi1'),
	('Dbs1'),
	('Dbs2'),
	('BuRe1'),
	('BuRe2'),
	('VSS'),
	('MsTe');

/* same exams: 1/2, 3/8, 4/6, 5/7 */
INSERT INTO exam_t (allowedtimeinmin, duedate, state, exam_authorid, exam_moduleid) values
	(120, '2015-01-21', 'ARCHIVED', 4, 1),
	(120, '2015-08-21', 'ARCHIVED', 4, 2),
	(120, '2016-08-15', 'APPEAL', 5, 3),
	(60, '2017-01-16', 'APPROVAL', 9, 6),
	(120, '2017-01-18', 'REVIEW', 4, 4),
	(60, '2017-01-19', 'REVIEW', 9, 7),
	(120, '2017-01-25', 'CORRECTION', 4, 5),
	(120, '2017-01-27', 'CORRECTION', 5, 3);

INSERT INTO allowedutility_t (exam_examid, allowedutility) values
	(1, 'keine (closed book)'),
	(2, 'keine (closed book)'),
	(3, 'keine (closed book)'),
	(4, 'Vorlesungsunterlagen'),
	(4, 'Eigene Vorlesungsnotizen'),
	(4, 'Lehrbücher'),
	(5, 'Eine A4 Seite (doppelseitig beschrieben oder bedruckt)'),
	(6, 'Vorlesungsunterlagen'),
	(6, 'Eigene Vorlesungsnotizen'),
	(6, 'Lehrbücher'),
	(7, 'Eine A4 Seite (doppelseitig beschrieben oder bedruckt)'),
	(8, 'keine (closed book)');

INSERT INTO examparticipation_t (participationdate, pseudonym, examparticipation_exam, examparticipation_participantid) values
	('2016-08-15', 'Anonymes Känguru', 3, 8),
	('2016-08-15', 'Anonyme Giraffe', 3, 1),
	('2016-08-15', 'Anonymes Nashorn', 3, 10),
	('2017-01-16', 'Anonymes Zebra', 4, 3),
	('2017-01-16', 'Anonyme Gazelle', 4, 2),
	('2017-01-16', 'Anonymes Einhorn', 4, 1),
	('2017-01-18', 'Anonymer Löwe', 5, 8),
	('2017-01-18', 'Anonymes Lama', 5, 3),
	('2017-01-18', 'Anonymes Pony', 5, 10),
	('2017-01-19', 'Anonymes Eichhörnchen', 6, 6),
	('2017-01-19', 'Anonyme Springmaus', 6, 7),
	('2017-01-19', 'Anonyme Viper', 6, 1),
	('2017-01-25', 'Anonymes Kamel', 7, 2),
	('2017-01-25', 'Anonyme Ringelnatter', 7, 3),
	('2017-01-25', 'Anonymer Puma', 7, 6),
	('2017-01-27', 'Anonymer Panda', 8, 1),
	('2017-01-27', 'Anonymer Elefant', 8, 2),
	('2017-01-27', 'Anonymer Flamingo', 8, 3);

/* example solutions (ID: 1-18) */
INSERT INTO solution_t (solution_type, solutiontext) values
	('TextSolution', 'Jedes CBC Verfahren bei dem der IV voraussagbar ist, ist prinzipiell anfällig für Watermarking.'),
	('TextSolution', 'Das XTS-AES Verfahren benützt Ciphertext-Stealing, um den letzten Klartextblock vor der Verschlüsselung mit Ciphertext des vorletzten Blocks auf 16 Bytes aufzufüllen und hängt den nichtgestohlenen Teil des Ciphertexts an den Schluss.'),
	('TextSolution', 'Jeder Klartextblock wird durch XOR-Encrypt-XOR mit einem Bitvektor X modifiziert, der durch Verschlüsselung mit einem zweiten AES Key aus der Sektornummer gewonnen wird und durch Galois Multiplikation blockabhängig modifiziert wird.'),
	('TextSolution', 'Jedes CBC Verfahren bei dem der IV voraussagbar ist, ist prinzipiell anfällig für Watermarking.'),
	('TextSolution', 'Das XTS-AES Verfahren benützt Ciphertext-Stealing, um den letzten Klartextblock vor der Verschlüsselung mit Ciphertext des vorletzten Blocks auf 16 Bytes aufzufüllen und hängt den nichtgestohlenen Teil des Ciphertexts an den Schluss.'),
	('TextSolution', 'Jeder Klartextblock wird durch XOR-Encrypt-XOR mit einem Bitvektor X modifiziert, der durch Verschlüsselung mit einem zweiten AES Key aus der Sektornummer gewonnen wird und durch Galois Multiplikation blockabhängig modifiziert wird.'),
	('TextSolution', 'Die rechtsanwendenden Behörden müssen die Verfassungsbestimmung beachten.'),
	('TextSolution', 'Ja'),
	('TextSolution', 'Nein'),
	('TextSolution', 'Die rechtsanwendenden Behörden müssen die Verfassungsbestimmung beachten.'),
	('TextSolution', 'Ja'),
	('TextSolution', 'Nein'),
	('TextSolution', 'ACID: Atomicity (Integrity), Consistency, Isolation (Concurrency, Multiuser), Durability (Security), Kapselung'),
	('TextSolution', 'Transaktionen (Atomicity), Konsistenz (Consisteny), Mehrbenutzerbetrieb (Isolation), Grosse Datenmengen (Durability), Sicherheit (Durability), Datentypen, Abfragesprache, Backup und Recovery'),
	('TextSolution', 'Ersetzt NULL durch den nächsten Parameter, der nicht null ist.'),
	('TextSolution', 'ACID: Atomicity (Integrity), Consistency, Isolation (Concurrency, Multiuser), Durability (Security), Kapselung'),
	('TextSolution', 'Transaktionen (Atomicity), Konsistenz (Consisteny), Mehrbenutzerbetrieb (Isolation), Grosse Datenmengen (Durability), Sicherheit (Durability), Datentypen, Abfragesprache, Backup und Recovery'),
	('TextSolution', 'Ersetzt NULL durch den nächsten Parameter, der nicht null ist.');

/* participant solutions (ID: 19-72) */
INSERT INTO solution_t (solution_type, solutiontext) values
	('TextSolution', 'Ich weiss es nicht.'),
	('TextSolution', 'keine Ahnung.'),
	('TextSolution', ''),
	('TextSolution', 'leider nein'),
	('TextSolution', ''),
	('TextSolution', 'nope'),
	('TextSolution', 'Ich habe keine Idee.'),
	('TextSolution', ''),
	('TextSolution', 'Wenn ich raten müsste, hätte ich auch keine Ahnung.'),
	('TextSolution', '42'),
	('TextSolution', ''),
	('TextSolution', 'Das ist zu schwer für mich.'),
	('TextSolution', 'Ich habe keine Ahnung.'),
	('TextSolution', ''),
	('TextSolution', 'Also doch lieber Gärtner...'),
	('TextSolution', 'Ich bin überfordert.'),
	('TextSolution', 'Bitte helfen Sie mir.'),
	('TextSolution', ''),
	('TextSolution', 'Es liegt mir auf der Zunge.'),
	('TextSolution', 'keine Idee.'),
	('TextSolution', ''),
	('TextSolution', 'Vielleicht die nächste...'),
	('TextSolution', ''),
	('TextSolution', 'Annahme: Die Lösung ist 3.5?'),
	('TextSolution', 'Das haben wir nicht im Unterricht behandelt.'),
	('TextSolution', ''),
	('TextSolution', 'Jein'),
	('TextSolution', 'Ich weiss es nicht.'),
	('TextSolution', 'keine Ahnung.'),
	('TextSolution', ''),
	('TextSolution', 'leider nein'),
	('TextSolution', ''),
	('TextSolution', 'nope'),
	('TextSolution', 'Ich habe keine Idee.'),
	('TextSolution', ''),
	('TextSolution', 'Wenn ich raten müsste, hätte ich auch keine Ahnung.'),
	('TextSolution', '42'),
	('TextSolution', ''),
	('TextSolution', 'Das ist zu schwer für mich.'),
	('TextSolution', 'Ich habe keine Ahnung.'),
	('TextSolution', ''),
	('TextSolution', 'Also doch lieber Gärtner...'),
	('TextSolution', 'Ich bin überfordert.'),
	('TextSolution', 'Bitte helfen Sie mir.'),
	('TextSolution', ''),
	('TextSolution', 'Es liegt mir auf der Zunge.'),
	('TextSolution', 'keine Idee.'),
	('TextSolution', ''),
	('TextSolution', 'Vielleicht die nächste...'),
	('TextSolution', ''),
	('TextSolution', 'Annahme: Die Lösung ist 3.5?'),
	('TextSolution', 'Das haben wir nicht im Unterricht behandelt.'),
	('TextSolution', ''),
	('TextSolution', 'Jein');

INSERT INTO excercise_t (excercise_type, maxpoints, excercise_examid, excercise_graderid, excercise_reviewerid, excercise_examplesolutionid, taskdescription) values
	('TextExercise', 5, 3, 5, 4, 1, 'Es sei bei einem AES-CBC basierten Disk Encryption System bekannt, dass der Initiali-sierungsvektor (IV) für die Verschlüsselung eines Sektors n mit der Formel IV(n) = 512 n + 255 berechnet wird. Ist dieses Verfahren resistent gegen Watermarks?'),
	('TextExercise', 5, 3, 5, 4, 2, 'Mit dem XTS-AES Verschlüsselungsverfahren können Dateien so verschlüsselt werden, dass der Ciphertext nicht mehr Speicherplatz braucht als der Klartext. Wie ist dies möglich?'),
	('TextExercise', 5, 3, 5, 4, 3, 'Geben Sie zwei Gründe an, warum kein einziges Byte eines XTS-AES verschlüsselten Sektors, der von einem Angreifer in einen unbenutzten Sektor auf der Festplatte verscho-ben wird, erfolgreich entschlüsselt wird?'),
	('TextExercise', 2, 4, 9, 5, 7, 'Eine rechtmässig erlassene bundesrätliche Verordnung aus dem Jahre 2015 steht im Widerspruch zu einer Bestimmung der Bundesverfassung aus dem Jahre 1912. Was wird angewendet?'),
	('TextExercise', 2, 4, 9, 5, 8, 'Ein Internet-Provider mit Sitz in Bern bewahrt unter anderem die IP-Adresse seiner Kunden während sechs Monaten auf. Stützt sich diese Datenbearbeitung auf eine gesetzliche Grundlage?'),
	('TextExercise', 2, 4, 9, 5, 9, 'Die Kantonspolizei St. Gallen dokumentiert auf dem Kantonsgebiet angehobene Strafuntersuchungen in einer kantonalen elektronischen Polizeidatenbank. Ist dafür der Eidgenössische Datenschutzbeauftragte die zuständige Aufsichtsbehörde?'),
	('TextExercise', 3, 5, 4, 5, 13, 'Zählen Sie drei Vorteile von Datenbank-Systemen gegenüber konventionellen Dateisystemen auf.'),
	('TextExercise', 5, 5, 4, 5, 14, 'Nennen Sie fünf wichtige Funktionen, die ein Datenbankmanagementsystem erfüllen muss.'),
	('TextExercise', 2, 5, 4, 5, 15, 'Was macht die COALESCE-Funktion?'),
	('TextExercise', 2, 6, 9, 5, 10, 'Eine rechtmässig erlassene bundesrätliche Verordnung aus dem Jahre 2015 steht im Widerspruch zu einer Bestimmung der Bundesverfassung aus dem Jahre 1912. Was wird angewendet?'),
	('TextExercise', 2, 6, 9, 5, 11, 'Ein Internet-Provider mit Sitz in Bern bewahrt unter anderem die IP-Adresse seiner Kunden während sechs Monaten auf. Stützt sich diese Datenbearbeitung auf eine gesetzliche Grundlage?'),
	('TextExercise', 2, 6, 9, 5, 12, 'Die Kantonspolizei St. Gallen dokumentiert auf dem Kantonsgebiet angehobene Strafuntersuchungen in einer kantonalen elektronischen Polizeidatenbank. Ist dafür der Eidgenössische Datenschutzbeauftragte die zuständige Aufsichtsbehörde?'),
	('TextExercise', 3, 7, 4, 5, 16, 'Zählen Sie drei Vorteile von Datenbank-Systemen gegenüber konventionellen Dateisystemen auf.'),
	('TextExercise', 5, 7, 4, 5, 17, 'Nennen Sie fünf wichtige Funktionen, die ein Datenbankmanagementsystem erfüllen muss.'),
	('TextExercise', 2, 7, 4, 5, 18, 'Was macht die COALESCE-Funktion?'),
	('TextExercise', 5, 8, 5, 4, 4, 'Es sei bei einem AES-CBC basierten Disk Encryption System bekannt, dass der Initiali-sierungsvektor (IV) für die Verschlüsselung eines Sektors n mit der Formel IV(n) = 512 n + 255 berechnet wird. Ist dieses Verfahren resistent gegen Watermarks?'),
	('TextExercise', 5, 8, 5, 4, 5, 'Mit dem XTS-AES Verschlüsselungsverfahren können Dateien so verschlüsselt werden, dass der Ciphertext nicht mehr Speicherplatz braucht als der Klartext. Wie ist dies möglich?'),
	('TextExercise', 5, 8, 5, 4, 6, 'Geben Sie zwei Gründe an, warum kein einziges Byte eines XTS-AES verschlüsselten Sektors, der von einem Angreifer in einen unbenutzten Sektor auf der Festplatte verscho-ben wird, erfolgreich entschlüsselt wird?');

INSERT INTO excercisesolution_t (isdone, excercisesolution_excerciseid, excercisesolution_participationid, excercisesolution_participantsolutionid) values
	(TRUE, 1, 1, 19),
	(TRUE, 2, 1, 20),
	(TRUE, 3, 1, 21),
	(TRUE, 1, 2, 22),
	(TRUE, 2, 2, 23),
	(TRUE, 3, 2, 24),
	(TRUE, 1, 3, 25),
	(TRUE, 2, 3, 26),
	(TRUE, 3, 3, 27),
	(FALSE, 4, 4, 28),
	(TRUE, 5, 4, 29),
	(FALSE, 6, 4, 30),
	(TRUE, 4, 5, 31),
	(TRUE, 5, 5, 32),
	(FALSE, 6, 5, 33),
	(TRUE, 4, 6, 34),
	(TRUE, 5, 6, 35),
	(TRUE, 6, 6, 36),
	(TRUE, 7, 7, 37),
	(TRUE, 8, 7, 38),
	(TRUE, 9, 7, 39),
	(TRUE, 7, 8, 40),
	(TRUE, 8, 8, 41),
	(TRUE, 9, 8, 42),
	(TRUE, 7, 9, 43),
	(TRUE, 8, 9, 44),
	(TRUE, 9, 9, 45),
	(TRUE, 10, 10, 46),
	(TRUE, 11, 10, 47),
	(TRUE, 12, 10, 48),
	(TRUE, 10, 11, 49),
	(TRUE, 11, 11, 50),
	(TRUE, 12, 11, 51),
	(FALSE, 10, 12, 52),
	(FALSE, 11, 12, 53),
	(FALSE, 12, 12, 54),
	(TRUE, 13, 13, 55),
	(TRUE, 14, 13, 56),
	(TRUE, 15, 13, 57),
	(TRUE, 13, 14, 58),
	(TRUE, 14, 14, 59),
	(TRUE, 15, 14, 60),
	(TRUE, 13, 15, 61),
	(TRUE, 14, 15, 62),
	(TRUE, 15, 15, 63),
	(TRUE, 16, 16, 64),
	(TRUE, 17, 16, 65),
	(TRUE, 18, 16, 66),
	(FALSE, 16, 17, 67),
	(FALSE, 17, 17, 68),
	(FALSE, 18, 17, 69),
	(FALSE, 16, 18, 70),
	(FALSE, 17, 18, 71),
	(FALSE, 18, 18, 72);

/* CORRECTIONS */
INSERT INTO excercisegrading_t (createdinstate, creationdate, isfinalgrading, excercisegrading_authorid, excercisegrading_excercisesolutionid, points, comment, reasoning) values
	('CORRECTION', '2016-08-22', TRUE, 5, 1, 5, 'Sehr gut gelöst.', ''),
	('CORRECTION', '2016-08-22', FALSE, 5, 2, 5, '', ''),
	('CORRECTION', '2016-08-22', TRUE, 5, 3, 5, 'Perfekt', ''),
	('CORRECTION', '2016-08-22', FALSE, 5, 4, 2, 'Begründung fehlt', ''),
	('CORRECTION', '2016-08-22', TRUE, 5, 5, 5, '', ''),
	('CORRECTION', '2016-08-22', TRUE, 5, 6, 4, 'Rechnungsfehler', ''),
	('CORRECTION', '2016-08-22', FALSE, 5, 7, 2, 'Begründung fehlt', ''),
	('CORRECTION', '2016-08-22', TRUE, 5, 8, 4, 'Lösungsweg stimmt, aber Endresultat falsch.', ''),
	('CORRECTION', '2016-08-22', FALSE, 5, 9, 0, '', ''),
	('CORRECTION', '2017-01-18', FALSE, 9, 10, 1, 'Begründung fehlt', ''),
	('CORRECTION', '2017-01-18', TRUE, 9, 11, 2, 'Korrekt', ''),
	('CORRECTION', '2017-01-18', FALSE, 9, 12, 0, '', ''),
	('CORRECTION', '2017-01-18', TRUE, 9, 13, 1, 'Begründung fehlt', ''),
	('CORRECTION', '2017-01-18', TRUE, 9, 14, 1, '', ''),
	('CORRECTION', '2017-01-18', FALSE, 9, 15, 1, '', ''),
	('CORRECTION', '2017-01-18', TRUE, 9, 16, 2, 'Sehr gut', ''),
	('CORRECTION', '2017-01-18', TRUE, 9, 17, 1, 'Begründung fehlt', ''),
	('CORRECTION', '2017-01-18', TRUE, 9, 18, 2, '', ''),
	('CORRECTION', '2017-01-23', TRUE, 4, 19, 3, 'Korrekt', ''),
	('CORRECTION', '2017-01-23', TRUE, 4, 20, 5, 'stimmt alles', ''),
	('CORRECTION', '2017-01-23', FALSE, 4, 21, 0, 'falsch', ''),
	('CORRECTION', '2017-01-23', TRUE, 4, 22, 2, '', ''),
	('CORRECTION', '2017-01-23', TRUE, 4, 23, 4, 'Eine Funktion fehlt.', ''),
	('CORRECTION', '2017-01-23', FALSE, 4, 24, 1, 'nicht ganz korrekt', ''),
	('CORRECTION', '2017-01-23', TRUE, 4, 25, 3, '', ''),
	('CORRECTION', '2017-01-23', TRUE, 4, 26, 5, 'Perfekt', ''),
	('CORRECTION', '2017-01-23', FALSE, 4, 27, 2, 'Das stimmt auch', ''),
	('CORRECTION', '2017-01-25', FALSE, 9, 28, 1, 'Begründung fehlt', ''),
	('CORRECTION', '2017-01-25', TRUE, 9, 29, 2, 'Korrekt', ''),
	('CORRECTION', '2017-01-25', FALSE, 9, 30, 0, '', ''),
	('CORRECTION', '2017-01-25', TRUE, 9, 31, 1, 'Begründung fehlt', ''),
	('CORRECTION', '2017-01-25', TRUE, 9, 32, 1, '', ''),
	('CORRECTION', '2017-01-25', FALSE, 9, 33, 1, '', ''),
	('CORRECTION', '2017-01-25', TRUE, 9, 34, 2, 'Sehr gut', ''),
	('CORRECTION', '2017-01-25', TRUE, 9, 35, 1, 'Begründung fehlt', ''),
	('CORRECTION', '2017-01-25', TRUE, 9, 36, 2, '', ''),
	('CORRECTION', '2017-01-28', TRUE, 4, 37, 3, 'Korrekt', ''),
	('CORRECTION', '2017-01-28', TRUE, 4, 38, 5, 'stimmt alles', ''),
	('CORRECTION', '2017-01-28', TRUE, 4, 39, 0, 'falsch', ''),
	('CORRECTION', '2017-01-28', TRUE, 4, 40, 2, '', ''),
	('CORRECTION', '2017-01-28', TRUE, 4, 41, 4, 'Eine Funktion fehlt.', ''),
	('CORRECTION', '2017-01-28', TRUE, 4, 42, 1, 'nicht ganz korrekt', ''),
	('CORRECTION', '2017-01-28', TRUE, 4, 43, 3, '', ''),
	('CORRECTION', '2017-01-28', TRUE, 4, 44, 5, 'Perfekt', ''),
	('CORRECTION', '2017-01-28', TRUE, 4, 45, 2, 'Das stimmt auch', ''),
	('CORRECTION', '2017-01-29', TRUE, 5, 46, 2, 'Begründung fehlt', ''),
	('CORRECTION', '2017-01-29', TRUE, 5, 47, 5, '', ''),
	('CORRECTION', '2017-01-29', TRUE, 5, 48, 4, 'Rechnungsfehler', '');

/* REVIEWS */
INSERT INTO excercisegrading_t (createdinstate, creationdate, isfinalgrading, excercisegrading_authorid, excercisegrading_excercisesolutionid, points, comment, reasoning) values
	('REVIEW', '2016-08-25', TRUE, 4, 2, 3, '', 'Stimmt nicht mit der Musterlösung überein'),
	('REVIEW', '2016-08-25', TRUE, 4, 4, 3, '', 'Ein Teilpunkt kann noch gegeben werden.'),
	('REVIEW', '2016-08-25', TRUE, 4, 7, 3, '', ''),
	('REVIEW', '2016-08-25', TRUE, 4, 9, 5, '', 'Korrektur vergessen?'),
	('REVIEW', '2017-01-20', TRUE, 5, 10, 2, '', 'Ein weiterer Teilpunkt für das richtige Resultat'),
	('REVIEW', '2017-01-20', TRUE, 5, 12, 1, '', 'Korrektur vergessen?'),
	('REVIEW', '2017-01-20', TRUE, 5, 15, 2, '', 'Ich würde für diese Antwort noch einen Punkt geben.'),
	('REVIEW', '2017-01-26', TRUE, 5, 21, 1, '', 'Unter gewissen Umständen ist die Antwort korrekt'),
	('REVIEW', '2017-01-26', TRUE, 5, 24, 2, '', ''),
	('REVIEW', '2017-01-26', TRUE, 5, 27, 0, '', 'Diese Antwort ist falsch.'),
	('REVIEW', '2017-01-29', TRUE, 5, 28, 2, '', 'Ein weiterer Teilpunkt für das richtige Resultat'),
	('REVIEW', '2017-01-29', TRUE, 5, 30, 1, '', 'Korrektur vergessen?'),
	('REVIEW', '2017-01-29', TRUE, 5, 33, 2, '', 'Ich würde für diese Antwort noch einen Punkt geben.');