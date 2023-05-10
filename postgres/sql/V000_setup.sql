CREATE ROLE "nexushub" WITH SUPERUSER;
CREATE USER "nexushub-ro" WITH PASSWORD 'nexushub-ro' IN ROLE "nexushub";
CREATE USER "nexushub-dml" WITH PASSWORD 'nexushub-dml' IN ROLE "nexushub";
CREATE USER "nexushub-app" WITH PASSWORD 'nexushub-app' IN ROLE "nexushub";

ALTER USER "nexushub-app" SET search_path = public, nexushub;

CREATE DATABASE "nexushub" WITH OWNER "nexushub-app";