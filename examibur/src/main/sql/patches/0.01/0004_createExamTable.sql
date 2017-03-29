BEGIN;

INSERT INTO version_t (id, release, name, type, description) VALUES (
    nextval('version_id_seq'), 
    '0.01', 
    '0004_createExamTable', 
    'feature',
    'Initial exam table.'
);

CREATE TABLE exam_allowedutilities (
    exam_examid bigint,
    allowedutilities character varying(255)
);

CREATE TABLE exam_t (
    examid integer NOT NULL,
    allowedtimeinmin integer,
    duedate date,
    state character varying(255) NOT NULL,
    exam_authorid bigint,
    exam_moduleid bigint
);

CREATE SEQUENCE exam_t_examid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

    
ALTER TABLE exam_allowedutilities OWNER TO examibur;
ALTER TABLE exam_t OWNER TO examibur;
ALTER TABLE exam_t_examid_seq OWNER TO examibur;
ALTER SEQUENCE exam_t_examid_seq OWNED BY exam_t.examid;

ALTER TABLE ONLY exam_t 
    ALTER COLUMN examid SET DEFAULT nextval('exam_t_examid_seq'::regclass),
    ADD CONSTRAINT exam_t_pkey PRIMARY KEY (examid),
    ADD CONSTRAINT fk_exam_t_exam_authorid FOREIGN KEY (exam_authorid) REFERENCES user_t(userid),
    ADD CONSTRAINT fk_exam_t_exam_moduleid FOREIGN KEY (exam_moduleid) REFERENCES module_t(moduleid);

ALTER TABLE ONLY exam_allowedutilities
    ADD CONSTRAINT fk_exam_allowedutilities_exam_examid FOREIGN KEY (exam_examid) REFERENCES exam_t(examid);


SELECT pg_catalog.setval('exam_t_examid_seq', 1, false);


COMMIT;
