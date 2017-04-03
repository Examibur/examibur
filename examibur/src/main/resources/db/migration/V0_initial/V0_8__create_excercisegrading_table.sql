
CREATE TABLE excercisegrading_t (
    excercisegradingid integer NOT NULL,
    comment character varying(255),
    createdinstate character varying(255) NOT NULL,
    creationdate date NOT NULL,
    isfinalgrading boolean,
    points double precision NOT NULL,
    reasoning character varying(255),
    excercisegrading_excercisesolutionid bigint NOT NULL,
    excercisegrading_authorid bigint NOT NULL
);

CREATE SEQUENCE excercisegrading_t_excercisegradingid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE excercisegrading_t OWNER TO examibur;
ALTER TABLE excercisegrading_t_excercisegradingid_seq OWNER TO examibur;
ALTER SEQUENCE excercisegrading_t_excercisegradingid_seq OWNED BY excercisegrading_t.excercisegradingid;

ALTER TABLE ONLY excercisegrading_t 
    ALTER COLUMN excercisegradingid SET DEFAULT nextval('excercisegrading_t_excercisegradingid_seq'::regclass),
    ADD CONSTRAINT excercisegrading_t_pkey PRIMARY KEY (excercisegradingid),
    ADD CONSTRAINT fk_excercisegrading_t_excercisegrading_authorid FOREIGN KEY (excercisegrading_authorid) REFERENCES user_t(userid),
    ADD CONSTRAINT fk_excercisegrading_t_excercisegrading_excercisesolutionid FOREIGN KEY (excercisegrading_excercisesolutionid) REFERENCES excercisesolution_t(excercisesolutionid);

SELECT pg_catalog.setval('excercisegrading_t_excercisegradingid_seq', 1, false);
