create database examen2hiber;
use examen2hiber;

create table fabricantes
(
    CIF       VARCHAR(10) PRIMARY KEY,
    nombre    VARCHAR(30),
    descrip   VARCHAR(50),
    domicilio VARCHAR(30)
);

INSERT INTO fabricantes
VALUES ('B11111', 'Oficinia SA', 'Mobiliario de oficina', 'Sol 11, Valencia');
INSERT INTO fabricantes
VALUES ('B22222', 'Electro SL   ', 'Electrodomésticos industriales', 'Guttemberg 22, Paterna');
INSERT INTO fabricantes
VALUES ('B33333', 'Tecnalia SLU ', 'Ordenadores y servidores a medida', 'Altamar 33, Gandia');
INSERT INTO fabricantes
VALUES ('B44444', 'Hidronatur SL', 'Bañeras, jacuzzis e hidromasajes', 'Castellon 44 Alicante');

create table productos
(
    ref        INT PRIMARY KEY,
    nombre     VARCHAR(30),
    precio     INT,
    cif_fabric VARCHAR(10),
    FOREIGN KEY (cif_fabric) REFERENCES fabricantes (CIF)
);

INSERT INTO productos
VALUES (1, 'Mesa esquinera', 650, 'B11111');
INSERT INTO productos
VALUES (2, 'Arcón congelador', 350, 'B22222');
INSERT INTO productos
VALUES (3, 'Servidor altas capacidades', 3500, 'B33333');
INSERT INTO productos
VALUES (4, 'Hidromasaje 16 chorros', 2200, 'B44444');