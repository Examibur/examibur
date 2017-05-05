ALTER TABLE user_t
    ADD username VARCHAR(255) NOT NULL DEFAULT 'n/a';  
      
-- Update demo data
UPDATE user_t SET username = 'maximilian.mueller' WHERE userid = 1;
UPDATE user_t SET username = 'ralph.maur' WHERE userid = 2;
UPDATE user_t SET username = 'katja.pfeiffer' WHERE userid = 3;
UPDATE user_t SET username = 'juergen.koenig' WHERE userid = 4;
UPDATE user_t SET username = 'stefan.boehm' WHERE userid = 5;
UPDATE user_t SET username = 'christina.theiss' WHERE userid = 6;
UPDATE user_t SET username = 'matthias.schumacher' WHERE userid = 7;
UPDATE user_t SET username = 'dominik.ostermann' WHERE userid = 8;
UPDATE user_t SET username = 'uwe.baer' WHERE userid = 9;
UPDATE user_t SET username = 'florian.forster' WHERE userid = 10;

