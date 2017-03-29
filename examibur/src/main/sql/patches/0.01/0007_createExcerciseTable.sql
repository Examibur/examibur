BEGIN;

INSERT INTO version_t (id, release, name, type, description) VALUES (
    nextval('version_id_seq'), 
    '0.01', 
    '0007_createExcerciseTable', 
    'feature',
    'Initial excercise table.'
);

CREATE TABLE excercise_t (
    excerciseid integer NOT NULL,
    excercise_type character varying(31),
    maxpoints double precision NOT NULL,
    excercise_examid bigint NOT NULL,
    excercise_graderid bigint,
    excercise_reviewerid bigint,
    excercise_examplesolutionid bigint,
    taskdescription character varying(255) NOT NULL
);

CREATE SEQUENCE excercise_t_excerciseid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE excercise_t OWNER TO examibur;
ALTER TABLE excercise_t_excerciseid_seq OWNER TO examibur;
ALTER SEQUENCE excercise_t_excerciseid_seq OWNED BY excercise_t.excerciseid;

ALTER TABLE ONLY excercise_t 
    ALTER COLUMN excerciseid SET DEFAULT nextval('excercise_t_excerciseid_seq'::regclass),
    ADD CONSTRAINT excercise_t_pkey PRIMARY KEY (excerciseid),
    ADD CONSTRAINT fk_excercise_t_excercise_examid FOREIGN KEY (excercise_examid) REFERENCES exam_t(examid),
    ADD CONSTRAINT fk_excercise_t_excercise_examplesolutionid FOREIGN KEY (excercise_examplesolutionid) REFERENCES solution_t(solutionid),
    ADD CONSTRAINT fk_excercise_t_excercise_graderid FOREIGN KEY (excercise_graderid) REFERENCES user_t(userid),
    ADD CONSTRAINT fk_excercise_t_excercise_reviewerid FOREIGN KEY (excercise_reviewerid) REFERENCES user_t(userid);

SELECT pg_catalog.setval('excercise_t_excerciseid_seq', 1, false);

COMMIT;
