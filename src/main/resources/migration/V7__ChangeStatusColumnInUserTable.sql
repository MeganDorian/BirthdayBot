UPDATE bdays.users set status=true where id=1;
UPDATE bdays.users set status=true where id=150;
UPDATE bdays.users set status=true where id=151;
UPDATE bdays.users set status=true where id=152;

ALTER TABLE bdays.users RENAME COLUMN status TO active;
ALTER TABLE bdays.users ALTER COLUMN active TYPE boolean USING active::boolean;