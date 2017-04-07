
CREATE TABLE exercisegrading_t (
    exercisegradingid integer NOT NULL,
    comment character varying(255),
    createdinstate character varying(255) NOT NULL,
    creationdate date NOT NULL,
    isfinalgrading boolean,
    points double precision NOT NULL,
    reasoning character varying(255),
    exercisegrading_exercisesolutionid bigint NOT NULL,
    exercisegrading_authorid bigint NOT NULL
);

CREATE SEQUENCE exercisegrading_t_exercisegradingid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE exercisegrading_t OWNER TO examibur;
ALTER TABLE exercisegrading_t_exercisegradingid_seq OWNER TO examibur;
ALTER SEQUENCE exercisegrading_t_exercisegradingid_seq OWNED BY exercisegrading_t.exercisegradingid;

ALTER TABLE ONLY exercisegrading_t 
    ALTER COLUMN exercisegradingid SET DEFAULT nextval('exercisegrading_t_exercisegradingid_seq'::regclass),
    ADD CONSTRAINT exercisegrading_t_pkey PRIMARY KEY (exercisegradingid),
    ADD CONSTRAINT fk_exercisegrading_t_exercisegrading_authorid FOREIGN KEY (exercisegrading_authorid) REFERENCES user_t(userid),
    ADD CONSTRAINT fk_exercisegrading_t_exercisegrading_exercisesolutionid FOREIGN KEY (exercisegrading_exercisesolutionid) REFERENCES exercisesolution_t(exercisesolutionid);

SELECT pg_catalog.setval('exercisegrading_t_exercisegradingid_seq', 1, false);
