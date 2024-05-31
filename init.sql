CREATE DATABASE IF NOT EXISTS Campeonato;
USE Campeonato;

CREATE TABLE Pais(
    nombre VARCHAR(50) PRIMARY KEY
);

CREATE TABLE Partido(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    hora TIME,
    id_pais_local VARCHAR(50),
    id_pais_visitante VARCHAR(50),
    goles_pais_local INT,
    goles_pais_visitante INT,
    FOREIGN KEY (id_pais_local) REFERENCES Pais(nombre),
    FOREIGN KEY (id_pais_visitante) REFERENCES Pais(nombre)
);

CREATE TABLE Usuario(
    id INT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    email VARCHAR(50)
);

CREATE TABLE Administradores(
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Usuario(id)
);

CREATE TABLE Participante(
    id INT PRIMARY KEY,
    campeon VARCHAR(50),
    subcampeon VARCHAR(50),
    FOREIGN KEY (id) REFERENCES Usuario(id)
    FOREIGN KEY (campeon) REFERENCES Pais(nombre),
    FOREIGN KEY (subcampeon) REFERENCES Pais(nombre)
);

CREATE TABLE Prediccion(
    id INT PRIMARY KEY,
    id_partido INT,
    id_participante INT,
    goles_pais_local INT,
    goles_pais_visitante INT,
    FOREIGN KEY (id_partido) REFERENCES Partido(id),
    FOREIGN KEY (id_participante) REFERENCES Participante(id)
);