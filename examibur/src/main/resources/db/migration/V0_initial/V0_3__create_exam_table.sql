CREATE TABLE allowedUtility_t (
    exam_examid bigint,
    allowedutility character varying(255)
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

    
ALTER TABLE allowedUtility_t OWNER TO examibur;
ALTER TABLE exam_t OWNER TO examibur;
ALTER TABLE exam_t_examid_seq OWNER TO examibur;
ALTER SEQUENCE exam_t_examid_seq OWNED BY exam_t.examid;

ALTER TABLE ONLY exam_t 
    ALTER COLUMN examid SET DEFAULT nextval('exam_t_examid_seq'::regclass),
    ADD CONSTRAINT exam_t_pkey PRIMARY KEY (examid),
    ADD CONSTRAINT fk_exam_t_exam_authorid FOREIGN KEY (exam_authorid) REFERENCES user_t(userid),
    ADD CONSTRAINT fk_exam_t_exam_moduleid FOREIGN KEY (exam_moduleid) REFERENCES module_t(moduleid);

ALTER TABLE ONLY allowedUtility_t
    ADD CONSTRAINT fk_allowedUtility_t_exam_examid FOREIGN KEY (exam_examid) REFERENCES exam_t(examid);


SELECT pg_catalog.setval('exam_t_examid_seq', 1, false);
