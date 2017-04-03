CREATE TABLE solution_t (
    solutionid integer NOT NULL,
    solution_type character varying(31),
    solutiontext character varying(255) NOT NULL
);

CREATE SEQUENCE solution_t_solutionid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE solution_t OWNER TO examibur;
ALTER TABLE solution_t_solutionid_seq OWNER TO examibur;
ALTER SEQUENCE solution_t_solutionid_seq OWNED BY solution_t.solutionid;


ALTER TABLE ONLY solution_t 
    ALTER COLUMN solutionid SET DEFAULT nextval('solution_t_solutionid_seq'::regclass),
    ADD CONSTRAINT solution_t_pkey PRIMARY KEY (solutionid);


SELECT pg_catalog.setval('solution_t_solutionid_seq', 1, false);
