CREATE TABLE exercisesolution_t (
    exercisesolutionid integer NOT NULL,
    isdone boolean NOT NULL,
    exercisesolution_exerciseid bigint NOT NULL,
    exercisesolution_participationid bigint NOT NULL,
    exercisesolution_participantsolutionid bigint
);

CREATE SEQUENCE exercisesolution_t_exercisesolutionid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE exercisesolution_t OWNER TO examibur;
ALTER TABLE exercisesolution_t_exercisesolutionid_seq OWNER TO examibur;
ALTER SEQUENCE exercisesolution_t_exercisesolutionid_seq OWNED BY exercisesolution_t.exercisesolutionid;

ALTER TABLE ONLY exercisesolution_t
    ALTER COLUMN exercisesolutionid SET DEFAULT nextval('exercisesolution_t_exercisesolutionid_seq'::regclass),
    ADD CONSTRAINT exercisesolution_t_pkey PRIMARY KEY (exercisesolutionid),
    ADD CONSTRAINT fk_exercisesolution_t_exercisesolution_exerciseid FOREIGN KEY (exercisesolution_exerciseid) REFERENCES exercise_t(exerciseid),
    ADD CONSTRAINT fk_exercisesolution_t_exercisesolution_participantsolutionid FOREIGN KEY (exercisesolution_participantsolutionid) REFERENCES solution_t(solutionid),
    ADD CONSTRAINT fk_exercisesolution_t_exercisesolution_participationid FOREIGN KEY (exercisesolution_participationid) REFERENCES examparticipation_t(examparticipationid);

SELECT pg_catalog.setval('exercisesolution_t_exercisesolutionid_seq', 1, false);
