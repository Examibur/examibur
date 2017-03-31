CREATE TABLE module_t (
    moduleid integer NOT NULL,
    name character varying(255) NOT NULL
);

CREATE SEQUENCE module_t_moduleid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE module_t OWNER TO examibur;
ALTER TABLE module_t_moduleid_seq OWNER TO examibur;
ALTER SEQUENCE module_t_moduleid_seq OWNED BY module_t.moduleid;

ALTER TABLE ONLY module_t 
    ALTER COLUMN moduleid SET DEFAULT nextval('module_t_moduleid_seq'::regclass),
    ADD CONSTRAINT module_t_pkey PRIMARY KEY (moduleid);
    
SELECT pg_catalog.setval('module_t_moduleid_seq', 1, false);
