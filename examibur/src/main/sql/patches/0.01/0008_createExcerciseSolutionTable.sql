BEGIN;

INSERT INTO version_t (id, release, name, type, description) VALUES (
    nextval('version_id_seq'), 
    '0.01', 
    '0008_createExcerciseSolutionTable', 
    'feature',
    'Initial excercisesolution table.'
);

CREATE TABLE excercisesolution_t (
    excercisesolutionid integer NOT NULL,
    isdone boolean,
    excercisesolution_excerciseid bigint,
    excercisesolution_participationid bigint,
    excercisesolution_participantsolutionid bigint
);

CREATE SEQUENCE excercisesolution_t_excercisesolutionid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE excercisesolution_t OWNER TO examibur;
ALTER TABLE excercisesolution_t_excercisesolutionid_seq OWNER TO examibur;
ALTER SEQUENCE excercisesolution_t_excercisesolutionid_seq OWNED BY excercisesolution_t.excercisesolutionid;

ALTER TABLE ONLY excercisesolution_t
    ALTER COLUMN excercisesolutionid SET DEFAULT nextval('excercisesolution_t_excercisesolutionid_seq'::regclass),
    ADD CONSTRAINT excercisesolution_t_pkey PRIMARY KEY (excercisesolutionid),
    ADD CONSTRAINT fk_excercisesolution_t_excercisesolution_excerciseid FOREIGN KEY (excercisesolution_excerciseid) REFERENCES excercise_t(excerciseid),
    ADD CONSTRAINT fk_excercisesolution_t_excercisesolution_participantsolutionid FOREIGN KEY (excercisesolution_participantsolutionid) REFERENCES solution_t(solutionid),
    ADD CONSTRAINT fk_excercisesolution_t_excercisesolution_participationid FOREIGN KEY (excercisesolution_participationid) REFERENCES examparticipation_t(examparticipationid);

SELECT pg_catalog.setval('excercisesolution_t_excercisesolutionid_seq', 1, false);


COMMIT;
