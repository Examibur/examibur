
CREATE TABLE exercise_t (
    exerciseid integer NOT NULL,
    exercise_type character varying(31),
    maxpoints double precision NOT NULL,
    exercise_examid bigint NOT NULL,
    exercise_graderid bigint,
    exercise_reviewerid bigint,
    exercise_examplesolutionid bigint,
    taskdescription character varying(255) NOT NULL
);

CREATE SEQUENCE exercise_t_exerciseid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE exercise_t OWNER TO examibur;
ALTER TABLE exercise_t_exerciseid_seq OWNER TO examibur;
ALTER SEQUENCE exercise_t_exerciseid_seq OWNED BY exercise_t.exerciseid;

ALTER TABLE ONLY exercise_t 
    ALTER COLUMN exerciseid SET DEFAULT nextval('exercise_t_exerciseid_seq'::regclass),
    ADD CONSTRAINT exercise_t_pkey PRIMARY KEY (exerciseid),
    ADD CONSTRAINT fk_exercise_t_exercise_examid FOREIGN KEY (exercise_examid) REFERENCES exam_t(examid),
    ADD CONSTRAINT fk_exercise_t_exercise_examplesolutionid FOREIGN KEY (exercise_examplesolutionid) REFERENCES solution_t(solutionid),
    ADD CONSTRAINT fk_exercise_t_exercise_graderid FOREIGN KEY (exercise_graderid) REFERENCES user_t(userid),
    ADD CONSTRAINT fk_exercise_t_exercise_reviewerid FOREIGN KEY (exercise_reviewerid) REFERENCES user_t(userid);

SELECT pg_catalog.setval('exercise_t_exerciseid_seq', 1, false);
