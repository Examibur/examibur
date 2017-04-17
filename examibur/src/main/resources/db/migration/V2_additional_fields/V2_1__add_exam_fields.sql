ALTER TABLE exam_t
    ADD lastmodified DATE,
    ADD creationdate DATE NOT NULL DEFAULT now();
    
-- Update demo data
UPDATE exam_t SET creationdate = '2015-01-05' WHERE examid = 1;
UPDATE exam_t SET creationdate = '2015-08-10' WHERE examid = 2;
UPDATE exam_t SET creationdate = '2016-08-06' WHERE examid = 3;
UPDATE exam_t SET creationdate = '2017-01-03' WHERE examid = 4;
UPDATE exam_t SET creationdate = '2017-01-04' WHERE examid = 5;
UPDATE exam_t SET creationdate = '2017-01-05' WHERE examid = 6;
UPDATE exam_t SET creationdate = '2017-01-10' WHERE examid = 7;
UPDATE exam_t SET creationdate = '2017-01-12' WHERE examid = 8;

UPDATE exam_t SET lastmodified = '2015-01-20' WHERE examid = 1;
UPDATE exam_t SET lastmodified = '2015-08-20' WHERE examid = 2;
UPDATE exam_t SET lastmodified = '2016-08-25' WHERE examid = 3;
UPDATE exam_t SET lastmodified = '2017-01-20' WHERE examid = 4;
UPDATE exam_t SET lastmodified = '2017-01-26' WHERE examid = 5;
UPDATE exam_t SET lastmodified = '2017-01-29' WHERE examid = 6;
UPDATE exam_t SET lastmodified = '2017-01-28' WHERE examid = 7;
UPDATE exam_t SET lastmodified = '2017-01-29' WHERE examid = 8;
