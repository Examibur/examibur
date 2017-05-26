INSERT INTO exercisegrading_t (createdinstate, creationdate, isfinalgrading, exercisegrading_authorid, exercisegrading_exercisesolutionid, points, comment, reasoning) values
	('CORRECTION', '2017-01-29', TRUE, 5, 52, 2, 'Rechnungsfehler', '');

UPDATE exercisesolution_t SET isdone = TRUE WHERE exercisesolutionid = 52;
