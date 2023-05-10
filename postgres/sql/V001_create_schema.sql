CREATE SCHEMA nexushub_platform;

ALTER SCHEMA nexushub_platform OWNER TO "nexushub";

CREATE TYPE nexushub_platform.subject_color AS ENUM (
    'RED',
    'BLUE',
    'GREEN',
    'YELLOW',
    'ORANGE',
    'PURPLE',
    'PINK',
    'BROWN',
    'BLACK',
    'WHITE',
    'GRAY'
);

ALTER TYPE nexushub_platform.subject_color OWNER TO "nexushub";

CREATE TABLE nexushub_platform.subject(
    id uuid not null,
    name varchar(255) not null,
    difficulty integer not null,
    color nexushub_platform.subject_color not null
);

ALTER TABLE nexushub_platform.subject OWNER TO "nexushub";

ALTER TABLE nexushub_platform.subject
    ADD CONSTRAINT subject_pk PRIMARY KEY (id);