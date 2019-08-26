ALTER TABLE bdays.users ADD role character varying(100);
UPDATE bdays.users SET role='ROLE_ADMIN' where id=1;
UPDATE bdays.users SET role='ROLE_USER' where id=2;
ALTER TABLE bdays.users ALTER COLUMN role SET NOT NULL;