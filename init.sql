CREATE DATABASE IF NOT EXISTS Campeonato;
USE Campeonato;

CREATE TABLE Pais(
    nombre VARCHAR(50) PRIMARY KEY
);

CREATE TABLE Partido(
    id INT PRIMARY KEY,
    fecha DATE,
    hora TIME,
    id_paisLocal VARCHAR(50),
    id_paisVisitante VARCHAR(50),
    goles_paisLocal INT,
    goles_paisVisitante INT,
    FOREIGN KEY (id_paisLocal) REFERENCES Pais(nombre),
    FOREIGN KEY (id_paisVisitante) REFERENCES Pais(nombre)
);

CREATE TABLE Usuario(
    id INT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    email VARCHAR(50),
    jwt VARCHAR(255)
);

CREATE TABLE Administradores(
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Usuario(id)
);

CREATE TABLE Participante(
    id INT PRIMARY KEY,
    campeon VARCHAR(50),
    subcampeon VARCHAR(50),
    FOREIGN KEY (id) REFERENCES Usuario(id),
    FOREIGN KEY (campeon) REFERENCES Pais(nombre),
    FOREIGN KEY (subcampeon) REFERENCES Pais(nombre)
);

CREATE TABLE Prediccion(
    id INT PRIMARY KEY,
    id_partido INT,
    id_participante INT,
    goles_paisLocal INT,
    goles_paisVisitante INT,
    FOREIGN KEY (id_partido) REFERENCES Partido(id),
    FOREIGN KEY (id_participante) REFERENCES Participante(id)
);

CREATE TABLE Login(
    ci INT PRIMARY KEY,
    contrasenia VARCHAR(50),
    FOREIGN KEY (ci) REFERENCES Usuario(id)
);