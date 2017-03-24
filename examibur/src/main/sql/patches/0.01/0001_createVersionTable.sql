BEGIN;

CREATE SEQUENCE version_id_seq;

CREATE TABLE version_t (
    id bigint primary key,
    release varchar(255) not null,	-- the release (ex. 0.5, 0.6)
    name varchar(255) not null,		-- the name of the patch file without .sql
    type varchar(255) not null,
    description text not null, 		
    applied_timestamp timestamp with time zone not null default now()
);

INSERT INTO version (id, release, name, type, description) VALUES (
    nextval('version_id_seq'), 
    '0.01', 
    '0001_createVersionTable', 
    'infrastructure',
    'Documents the patches that have already been applied to the database.'
);

COMMIT;