/* Create the tables Departments and Teachers */
CREATE TABLE Departments
(
    dept_num int primary key,
    name     varchar(20),
    office   varchar(20)
);
CREATE TABLE Teachers
(
    id         int primary key,
    name       varchar(15),
    surname    varchar(40),
    email      varchar(50),
    start_date date,
    dept_num   int References Departments (dept_num)
);

/* Insert these data in Departments */
INSERT INTO Departments (dept_num, name, office)
VALUES (10, 'INFORMATICA', 'DESPA6'),
       (20, 'COMERCIO', 'DESPA7'),
       (30, 'ADMINISTRATIVO', 'DESPA8'),
       (40, 'FOL', 'DESPA5');
/* Insert these data in Teachers */
INSERT INTO Teachers(id, name, surname, email, start_date, dept_num)
VALUES (1, 'Luz', 'Martinez', 'luz.martinez@iesabastos.org', '1990-01-01', 10),
       (2, 'Cristina', 'Ausina', 'c.ausina@iesabastos.org', '1990-02-01 ', 10),
       (3, 'Imma', 'Cabanes', 'i.cabanes@iesabastos.org', '1990-03-01', 10),
       (4, 'Mercedes', 'Sánchez', 'm.sanchez@iesabastos.org', NULL, 40);

/* Show the teachers (name, surname and email) that started 20 or more years ago. */
SELECT Teachers.name, Teachers.surname, Teachers.email
FROM Teachers
WHERE (YEAR(start_date) + 20) <= YEAR (CURRENT DATE);

/* Show all teachers in the office DESPA6. */
SELECT Teachers.id, Teachers.name, surname, office
FROM Teachers,
     Departments
WHERE Teachers.dept_num = Departments.dept_num
  AND Departments.office = 'DESPA6';

/* For all teachers of the department of FOL , update their start date to 01/09/2000. */
UPDATE Teachers
SET start_date = '2000-09-01'
WHERE id = (SELECT Teachers.id
            FROM Teachers,
                 Departments
            WHERE Teachers.dept_num = Departments.dept_num
              AND Departments.name = 'FOL');