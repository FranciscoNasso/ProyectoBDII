USE Campeonato;
INSERT INTO Carrera(nombre) VALUES
    ('Ingenieria en Informatica'),
    ('Ingenieria Civil'),
    ('Medicina'),
    ('Derecho'),
    ('Administracion de Empresas'),
    ('Psicologia'),
    ('Arquitectura'),
    ('Contaduria Publica'),
    ('Comunicacion Social'),
    ('Ingenieria Industrial'),
    ('Biologia'),
    ('Enfermeria'),
    ('Economia'),
    ('Ingenieria Quimica'),
    ('Diseno Grafico');

INSERT INTO Pais(nombre) VALUES 
    ('Uruguay'),
    ('Argentina'),
    ('Brasil'),
    ('Chile');

INSERT INTO Usuario(id, nombre, apellido, email, id_carrera) VALUES
    (54802569, 'Pablo', 'Constantino', 'pablo.constantino@gmail.com', 1),
    (54802570, 'María', 'González', 'maria.gonzalez@gmail.com', 2),
    (54802571, 'Juan', 'Pérez', 'juan.perez@gmail.com', 3),
    (54802572, 'Ana', 'Rodríguez', 'ana.rodriguez@gmail.com', 4),
    (54802573, 'Carlos', 'Sánchez', 'carlos.sanchez@gmail.com', 5),
    (54802574, 'Lucía', 'Martínez', 'lucia.martinez@gmail.com', 6);

INSERT INTO Administrador(id) VALUES
    (54802569),
    (54802570),
    (54802571);

INSERT INTO Participante(id, campeon, subcampeon) VALUES
    (54802572, 'Uruguay', 'Argentina'),
    (54802573, 'Uruguay', 'Chile'),
    (54802574, 'Uruguay', 'Brasil');

INSERT INTO Login(ci, contrasenia) VALUES
    (54802569, 'bfd59291e825b5f2bbf1eb76569f8fe7'),
    (54802570, 'bfd59291e825b5f2bbf1eb76569f8fe7'),
    (54802571, 'bfd59291e825b5f2bbf1eb76569f8fe7'),
    (54802572, 'bfd59291e825b5f2bbf1eb76569f8fe7'),
    (54802573, 'bfd59291e825b5f2bbf1eb76569f8fe7'),
    (54802574, 'bfd59291e825b5f2bbf1eb76569f8fe7');

select * from Carrera;

select * from Usuario;