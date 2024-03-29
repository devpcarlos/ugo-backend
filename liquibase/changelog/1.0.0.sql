--liquibase formatted sql
--changeset carlosjesus.barreraaleman:public.param_changelog.20240329_1700.1 context:test,dev,prod

CREATE TABLE public.param
(
	id              BIGSERIAL NOT NULL,
	value           TEXT NOT NULL,
	description     CHARACTER VARYING(127) NOT NULL,
	created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	updated_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	CONSTRAINT pk_param PRIMARY KEY (id)
);

--changeset carlosjesus.barreraaleman:public.role_changelog.20240329_1700.1 context:test,dev,prod

CREATE TABLE public.role
(
	id   BIGSERIAL NOT NULL,
	CONSTRAINT pk_role PRIMARY KEY (id)
);

--changeset carlosjesus.barreraaleman:ugo.schema_changelog.20240329_1700.1 context:test,dev,prod

CREATE SCHEMA IF NOT EXISTS ugo;

--changeset carlosjesus.barreraaleman:public.param_changelog.20240329_1700.2 context:test,dev,prod

ALTER TABLE public.param SET SCHEMA ugo;

--changeset carlosjesus.barreraaleman:public.role_changelog.20240329_1700.2 context:test,dev,prod

ALTER TABLE public.role SET SCHEMA ugo;

--changeset carlosjesus.barreraaleman:ugo.role_changelog.20240329_1700.1 context:test,dev,prod

ALTER TABLE ugo.role ADD COLUMN name CHARACTER VARYING(63) NOT NULL;

--changeset carlosjesus.barreraaleman:ugo.role_changelog.20240329_1700.2 context:test,dev,prod

ALTER TABLE ugo.role ADD COLUMN created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now();

--changeset carlosjesus.barreraaleman:ugo.role_changelog.20240329_1700.3 context:test,dev,prod

ALTER TABLE ugo.role ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now();

--changeset carlosjesus.barreraaleman:ugo.app_user_changelog.20240329_1700.1 context:test,dev,prod

CREATE TABLE ugo.app_user
(
	id                   BIGSERIAL NOT NULL,
	role_id              BIGINT NOT NULL,
	name                 CHARACTER VARYING(63) NOT NULL,
	paternal_surname     CHARACTER VARYING(63),
	maternal_surname     CHARACTER VARYING(63),
	email                CHARACTER VARYING(255) NOT NULL,
	password             TEXT,
	email_confirmed      BOOLEAN NOT NULL DEFAULT false,
	created_at           TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	updated_at           TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	CONSTRAINT pk_app_user PRIMARY KEY (id)
);

ALTER TABLE ugo.app_user ADD CONSTRAINT fk_app_user_role_id FOREIGN KEY (role_id) REFERENCES ugo.role (id);

--changeset carlosjesus.barreraaleman:ugo.booking_changelog.20240329_1700.1 context:test,dev,prod

CREATE TABLE ugo.booking
(
	id   BIGSERIAL NOT NULL,
	CONSTRAINT pk_booking PRIMARY KEY (id)
);

--changeset carlosjesus.barreraaleman:ugo.booking_status_changelog.20240329_1700.1 context:test,dev,prod

CREATE TABLE ugo.booking_status
(
	id   BIGSERIAL NOT NULL,
	CONSTRAINT pk_booking_status PRIMARY KEY (id)
);

--changeset carlosjesus.barreraaleman:ugo.booking_changelog.20240329_1700.2 context:test,dev,prod

ALTER TABLE ugo.booking ADD COLUMN booking_status_id BIGINT NOT NULL;

--changeset carlosjesus.barreraaleman:ugo.booking_changelog.20240329_1700.3 context:test,dev,prod

ALTER TABLE ugo.booking ADD CONSTRAINT fk_booking_booking_status_id FOREIGN KEY (booking_status_id) REFERENCES ugo.booking_status (id);

--changeset carlosjesus.barreraaleman:ugo.booking_status_changelog.20240329_1700.2 context:test,dev,prod

ALTER TABLE ugo.booking_status ADD COLUMN name CHARACTER VARYING(15) NOT NULL;

--changeset carlosjesus.barreraaleman:ugo.booking_status_changelog.20240329_1700.3 context:test,dev,prod

ALTER TABLE ugo.booking_status ADD COLUMN created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now();

--changeset carlosjesus.barreraaleman:ugo.booking_status_changelog.20240329_1700.4 context:test,dev,prod

ALTER TABLE ugo.booking_status ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now();

--changeset carlosjesus.barreraaleman:ugo.experience_changelog.20240329_1700.1 context:test,dev,prod

CREATE TABLE ugo.experience
(
	id               BIGSERIAL NOT NULL,
	location         CHARACTER VARYING(255),
	availability     CHARACTER VARYING(255),
	price            DOUBLE PRECISION,
	CONSTRAINT pk_experience PRIMARY KEY (id)
);

--changeset carlosjesus.barreraaleman:ugo.activity_type_changelog.20240329_1700.1 context:test,dev,prod

CREATE TABLE ugo.activity_type
(
	id             BIGSERIAL NOT NULL,
	name           CHARACTER VARYING(63) NOT NULL,
	created_at     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	updated_at     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	CONSTRAINT pk_activity_type PRIMARY KEY (id)
);

--changeset carlosjesus.barreraaleman:ugo.experience_changelog.20240329_1700.2 context:test,dev,prod

ALTER TABLE ugo.experience ADD COLUMN activity_type_id BIGINT NOT NULL;

--changeset carlosjesus.barreraaleman:ugo.experience_changelog.20240329_1700.3 context:test,dev,prod

ALTER TABLE ugo.experience ADD CONSTRAINT fk_experience_activity_type_id FOREIGN KEY (activity_type_id) REFERENCES ugo.activity_type (id);

--changeset carlosjesus.barreraaleman:ugo.experience_changelog.20240329_1700.4 context:test,dev,prod

ALTER TABLE ugo.experience ADD COLUMN created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now();

--changeset carlosjesus.barreraaleman:ugo.experience_changelog.20240329_1700.5 context:test,dev,prod

ALTER TABLE ugo.experience ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now();

--changeset carlosjesus.barreraaleman:ugo.experience_changelog.20240329_1700.6 context:test,dev,prod

ALTER TABLE ugo.experience ADD COLUMN app_user_id BIGINT NOT NULL;

--changeset carlosjesus.barreraaleman:ugo.experience_changelog.20240329_1700.7 context:test,dev,prod

ALTER TABLE ugo.experience ADD CONSTRAINT fk_experience_app_user_id FOREIGN KEY (app_user_id) REFERENCES ugo.app_user (id);

--changeset carlosjesus.barreraaleman:ugo.booking_changelog.20240329_1700.4 context:test,dev,prod

ALTER TABLE ugo.booking ADD COLUMN experience_id BIGINT NOT NULL;

--changeset carlosjesus.barreraaleman:ugo.booking_changelog.20240329_1700.5 context:test,dev,prod

ALTER TABLE ugo.booking ADD CONSTRAINT fk_booking_experience_id FOREIGN KEY (experience_id) REFERENCES ugo.experience (id);

--changeset carlosjesus.barreraaleman:ugo.booking_changelog.20240329_1700.6 context:test,dev,prod

ALTER TABLE ugo.booking ADD COLUMN app_user_id BIGINT NOT NULL;

--changeset carlosjesus.barreraaleman:ugo.booking_changelog.20240329_1700.7 context:test,dev,prod

ALTER TABLE ugo.booking ADD CONSTRAINT fk_booking_app_user_id FOREIGN KEY (app_user_id) REFERENCES ugo.app_user (id);

--changeset carlosjesus.barreraaleman:ugo.booking_changelog.20240329_1700.8 context:test,dev,prod

ALTER TABLE ugo.booking ADD COLUMN created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now();

--changeset carlosjesus.barreraaleman:ugo.booking_changelog.20240329_1700.9 context:test,dev,prod

ALTER TABLE ugo.booking ADD COLUMN updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now();

--changeset carlosjesus.barreraaleman:ugo.assessment_changelog.20240329_1700.1 context:test,dev,prod

CREATE TABLE ugo.assessment
(
	id                BIGSERIAL NOT NULL,
	app_user_id       BIGINT NOT NULL,
	comment           CHARACTER VARYING(255) NOT NULL,
	rating            INTEGER NOT NULL,
	experience_id     BIGINT NOT NULL,
	created_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	updated_at        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	CONSTRAINT pk_assessment PRIMARY KEY (id)
);

ALTER TABLE ugo.assessment ADD CONSTRAINT fk_assessment_app_user_id FOREIGN KEY (app_user_id) REFERENCES ugo.app_user (id);
ALTER TABLE ugo.assessment ADD CONSTRAINT fk_assessment_experience_id FOREIGN KEY (experience_id) REFERENCES ugo.experience (id);

