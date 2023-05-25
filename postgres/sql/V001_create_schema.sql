CREATE SCHEMA nexushub_platform;

ALTER SCHEMA nexushub_platform OWNER TO "nexushub";

CREATE TABLE nexushub_platform.application_user(
    id uuid NOT NULL,
    name varchar NOT NULL,
    username varchar NOT NULL UNIQUE,
    login varchar NOT NULL UNIQUE,
    password varchar NOT NULL,
    is_account_non_expired boolean default false,
    is_account_nonLocked boolean default false,
    is_credentials_non_expired boolean default false,
    is_enabled boolean default false
);

ALTER TABLE nexushub_platform.application_user OWNER TO "pipegine";

ALTER TABLE nexushub_platform.application_user
    ADD CONSTRAINT application_user_pkey PRIMARY KEY (id);

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
    user_id uuid not null,
    color nexushub_platform.subject_color not null
);

ALTER TABLE nexushub_platform.subject OWNER TO "nexushub";

ALTER TABLE nexushub_platform.subject
    ADD CONSTRAINT subject_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.subject
    ADD CONSTRAINT subject_user_fk
        FOREIGN KEY (user_id) REFERENCES nexushub_platform.application_user(id);

CREATE TABLE nexushub_platform.cycle(
    id uuid not null,
    name varchar(255) not null,
    description varchar(255),
    user_id uuid not null,
    amount_hours float not null
);

ALTER TABLE nexushub_platform.cycle OWNER TO "nexushub";

ALTER TABLE nexushub_platform.cycle
    ADD CONSTRAINT cycle_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.cycle
    ADD CONSTRAINT cycle_user_fk
        FOREIGN KEY (user_id) REFERENCES nexushub_platform.application_user(id);

CREATE TYPE nexushub_platform.sequence_status AS ENUM (
    'RUNNING',
    'FINISHED',
    'SKIPPED'
);

CREATE TABLE nexushub_platform.sequence(
    id uuid not null,
    number integer not null,
    last_sequence_subject_number integer not null,
    status nexushub_platform.sequence_status not null,
    cycle_id uuid not null
);

ALTER TABLE nexushub_platform.sequence OWNER TO "nexushub";

ALTER TABLE nexushub_platform.sequence
    ADD CONSTRAINT sequence_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.sequence
    ADD CONSTRAINT sequence_cycle_fk
        FOREIGN KEY (cycle_id) REFERENCES nexushub_platform.cycle(id);

CREATE TYPE nexushub_platform.sequence_subject_status AS ENUM (
    'PENDING',
    'STUDYING',
    'FINISHED',
    'SKIPPED'
);

CREATE TABLE nexushub_platform.sequence_subject(
    id uuid not null,
    sequence_number integer not null,
    hours float not null,
    studied_hours float not null,
    status nexushub_platform.sequence_subject_status not null,
    sequence_id uuid not null
);

ALTER TABLE nexushub_platform.sequence_subject OWNER TO "nexushub";

ALTER TABLE nexushub_platform.sequence_subject
    ADD CONSTRAINT sequence_subject_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.sequence_subject
    ADD CONSTRAINT sequence_subject_sequence_fk
        FOREIGN KEY (sequence_id) REFERENCES nexushub_platform.sequence(id);

