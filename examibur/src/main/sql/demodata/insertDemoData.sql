BEGIN;

INSERT INTO module_t(moduleid, name)
    VALUES (1, 'CS101');

INSERT INTO user_t(userid, firstname, lastname)
    VALUES (1, 'Jonas', 'Matter');

INSERT INTO exam_t(
            examid, allowedtimeinmin, duedate, state, exam_authorid, exam_moduleid)
    VALUES (1, 120, now(), 'CORRECTION', 1, 1);
    
INSERT INTO allowedUtility_t(
        exam_examid, allowedutility)
	VALUES (1, 'calculator');
INSERT INTO allowedUtility_t(
    	exam_examid, allowedutility)
VALUES (1, 'Toffifee');

COMMIT;