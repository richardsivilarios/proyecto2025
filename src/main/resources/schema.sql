CREATE SCHEMA IF NOT EXISTS academico;

CREATE TABLE IF NOT EXISTS academico.personal (
  idusuario   INTEGER PRIMARY KEY,
  nombre      VARCHAR(100) NOT NULL,
  ap          VARCHAR(100),
  am          VARCHAR(100),
  estado      SMALLINT,
  fnac        DATE,
  ecivil      VARCHAR(30),
  genero      VARCHAR(10),
  dir         VARCHAR(200),
  telf        VARCHAR(30),
  tipo        VARCHAR(30),
  foto        VARCHAR(255),
  create_time TIMESTAMP,
  update_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS academico.datos (
  idp         INTEGER PRIMARY KEY,
  cedula      VARCHAR(30) UNIQUE,
  create_time TIMESTAMP,
  update_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS academico.general (
  codg INTEGER PRIMARY KEY,
  gestion      INTEGER,
  login VARCHAR(50),
  create_time TIMESTAMP,
  update_time TIMESTAMP

)