BEGIN;

CREATE TABLE IF NOT EXISTS alumnos (
    id bigserial NOT NULL PRIMARY KEY,
    nombre character varying(64),
    apellido character varying(64),
    fecha_nac date
);

CREATE TABLE IF NOT EXISTS profesores (
    id bigserial NOT NULL PRIMARY KEY,
    nombre character varying(64),
    apellido character varying(64),
    fecha_contrato date
);

CREATE TABLE IF NOT EXISTS asignaturas (
    id bigserial NOT NULL PRIMARY KEY,
    nombre character varying(64),
    profesor_id bigint REFERENCES profesores(id)
);

CREATE TABLE IF NOT EXISTS matriculas
(
    id bigserial NOT NULL PRIMARY KEY,
    alumno_id bigint REFERENCES alumnos(id),
    asignatura_id bigint REFERENCES asignaturas(id),
    curso bigint,
    calificacion smallint
);

END;