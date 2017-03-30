BEGIN;

INSERT INTO version_t (id, release, name, type, description) VALUES (
    nextval('version_id_seq'), 
    '0.01', 
    '0005_createExamParticipationTable', 
    'feature',
    'Initial examparticipation table.'
);


CREATE TABLE examparticipation_t (
    examparticipationid integer NOT NULL,
    participationdate date NOT NULL,
    pseudonym character varying(255),
    examparticipation_exam bigint NOT NULL,
    examparticipation_participantid bigint NOT NULL
);

CREATE SEQUENCE examparticipation_t_examparticipationid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE examparticipation_t OWNER TO examibur;
ALTER TABLE examparticipation_t_examparticipationid_seq OWNER TO examibur;
ALTER SEQUENCE examparticipation_t_examparticipationid_seq OWNED BY examparticipation_t.examparticipationid;

ALTER TABLE ONLY examparticipation_t 
    ALTER COLUMN examparticipationid SET DEFAULT nextval('examparticipation_t_examparticipationid_seq'::regclass),
    ADD CONSTRAINT examparticipation_t_pkey PRIMARY KEY (examparticipationid),
    ADD CONSTRAINT fk_examparticipation_t_examparticipation_exam FOREIGN KEY (examparticipation_exam) REFERENCES exam_t(examid),
    ADD CONSTRAINT fk_examparticipation_t_examparticipation_participantid FOREIGN KEY (examparticipation_participantid) REFERENCES user_t(userid);
    
SELECT pg_catalog.setval('examparticipation_t_examparticipationid_seq', 1, false);

COMMIT;
