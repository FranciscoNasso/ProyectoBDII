CREATE DATABASE IF NOT EXISTS Campeonato;
USE Campeonato;

CREATE TABLE Pais(
    nombre VARCHAR(50) PRIMARY KEY,
    iso VARCHAR(2) NOT NULL
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
    email VARCHAR(50),
    jwt VARCHAR(255),
    id_carrera INT NOT NULL
);

CREATE TABLE Administrador(
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
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_partido INT,
    id_participante INT,
    goles_pais_local INT,
    goles_pais_visitante INT,
    puntos INT DEFAULT 0,
    FOREIGN KEY (id_partido) REFERENCES Partido(id),
    FOREIGN KEY (id_participante) REFERENCES Participante(id)
);

CREATE TABLE Login(
    ci INT PRIMARY KEY,
    contrasenia VARCHAR(50),
    FOREIGN KEY (ci) REFERENCES Usuario(id)
);

CREATE TABLE Carrera(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150)
)

CREATE TABLE Ranking(
    id_participante INT PRIMARY KEY,
    puntaje_final INT,
    FOREIGN KEY (id_participante) REFERENCES Participante(id)
)