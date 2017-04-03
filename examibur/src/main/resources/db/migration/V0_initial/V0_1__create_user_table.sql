CREATE TABLE user_t (
    userid integer NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL
);

CREATE SEQUENCE user_t_userid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_t OWNER TO examibur;
ALTER TABLE user_t_userid_seq OWNER TO examibur;
ALTER SEQUENCE user_t_userid_seq OWNED BY user_t.userid;

ALTER TABLE ONLY user_t 
    ALTER COLUMN userid SET DEFAULT nextval('user_t_userid_seq'::regclass),
    ADD CONSTRAINT user_t_pkey PRIMARY KEY (userid);

SELECT pg_catalog.setval('user_t_userid_seq', 1, false);
