ALTER TABLE exercise_t
    ADD title character varying(255),
    ADD orderinexam integer NOT NULL DEFAULT 0;
    
-- update demo data
UPDATE exercise_t SET title = 'AES-CBC Disk Encryption' WHERE exerciseid = 1;
UPDATE exercise_t SET title = 'XTS-AES Speicherplatz Ausnutzung' WHERE exerciseid = 2;
UPDATE exercise_t SET title = 'XTS-AES Verschiebung' WHERE exerciseid = 3;
UPDATE exercise_t SET title = 'Verordnung gegen Verfassung' WHERE exerciseid = 4;
UPDATE exercise_t SET title = 'Datenbearbeitung' WHERE exerciseid = 5;
UPDATE exercise_t SET title = 'Aufsichtsbehörde Datenschutz' WHERE exerciseid = 6;
UPDATE exercise_t SET title = 'Vorteile Datenbank-Systeme' WHERE exerciseid = 7;
UPDATE exercise_t SET title = 'Wichtige Funktionen DBMS' WHERE exerciseid = 8;
UPDATE exercise_t SET title = 'COALESCE-Funktion' WHERE exerciseid = 9;
UPDATE exercise_t SET title = 'Verordnung gegen Verfassung' WHERE exerciseid = 10;
UPDATE exercise_t SET title = 'Datenbearbeitung' WHERE exerciseid = 11;
UPDATE exercise_t SET title = 'Zuständige Aufsichtsbehörde' WHERE exerciseid = 12;
UPDATE exercise_t SET title = 'Vorteile Datenbank-Systeme' WHERE exerciseid = 13;
UPDATE exercise_t SET title = 'Wichtige Funktionen DBMS' WHERE exerciseid = 14;
UPDATE exercise_t SET title = 'COALESCE-Funktion' WHERE exerciseid = 15;
UPDATE exercise_t SET title = 'AES-CBC Disk Encryption  ' WHERE exerciseid = 16;
UPDATE exercise_t SET title = 'XTS-AES Speicherplatz Ausnutzung' WHERE exerciseid = 17;
UPDATE exercise_t SET title = 'XTS-AES Verschiebung' WHERE exerciseid = 18;

UPDATE exercise_t SET orderinexam = 1 WHERE exerciseid = 1;
UPDATE exercise_t SET orderinexam = 2 WHERE exerciseid = 2;
UPDATE exercise_t SET orderinexam = 3 WHERE exerciseid = 3;
UPDATE exercise_t SET orderinexam = 1 WHERE exerciseid = 4;
UPDATE exercise_t SET orderinexam = 2 WHERE exerciseid = 5;
UPDATE exercise_t SET orderinexam = 3 WHERE exerciseid = 6;
UPDATE exercise_t SET orderinexam = 1 WHERE exerciseid = 7;
UPDATE exercise_t SET orderinexam = 2 WHERE exerciseid = 8;
UPDATE exercise_t SET orderinexam = 3 WHERE exerciseid = 9;
UPDATE exercise_t SET orderinexam = 1 WHERE exerciseid = 10;
UPDATE exercise_t SET orderinexam = 2 WHERE exerciseid = 11;
UPDATE exercise_t SET orderinexam = 3 WHERE exerciseid = 12;
UPDATE exercise_t SET orderinexam = 1 WHERE exerciseid = 13;
UPDATE exercise_t SET orderinexam = 2 WHERE exerciseid = 14;
UPDATE exercise_t SET orderinexam = 3 WHERE exerciseid = 15;
UPDATE exercise_t SET orderinexam = 1 WHERE exerciseid = 16;
UPDATE exercise_t SET orderinexam = 2 WHERE exerciseid = 17;
UPDATE exercise_t SET orderinexam = 3 WHERE exerciseid = 18;
