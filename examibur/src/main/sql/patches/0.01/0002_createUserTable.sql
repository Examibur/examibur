BEGIN;

INSERT INTO version_t (id, release, name, type, description) VALUES (
    nextval('version_id_seq'), 
    '0.01', 
    '0002_createUserTable', 
    'feature',
    'Initial user table.'
);

COMMIT;