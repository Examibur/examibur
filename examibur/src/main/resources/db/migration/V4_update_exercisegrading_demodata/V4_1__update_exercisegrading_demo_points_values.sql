UPDATE exercisegrading_t SET points = 0 where comment = 'Begründung fehlt';
UPDATE exercisegrading_t SET points = 0 where comment = 'Rechnungsfehler';
UPDATE exercisegrading_t SET points = 2 where comment = 'Eine Funktion fehlt.';