/* Create the tables Departments and Teachers, with the following structure: */
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
    dept_num References Departments (dept_num)
);

/*
Insert these data in Departments:
10, INFORMATICA, DESPA6
20, COMERCIO, DESPA7
30, ADMINISTRATIVO, DESPA8
40, FOL, DESPA5
*/
INSERT INTO Departments (dept_num, name, office)
VALUES (10, 'INFORMATICA', 'DESPA6'),
       (20, 'COMERCIO', 'DESPA7'),
       (30, 'ADMINISTRATIVO', 'DESPA8'),
       (40, 'FOL', 'DESPA5');

/*
Insert these data in Teachers:
1, Luz Martinez, luz.martinez@iesabastos.org, 01/01/90
2, Cristina Ausina, c.ausina@iesabastos.org, 01/02/90
3, Imma Cabanes, i.cabanes@iesabastos.org, 01/03/90
4, Mercedes Sánchez, m.sanchez@iesabastos.org
Knowing that teachers number 1 to 3 belong to INFORMATICA department, while teacher number 4 to FOL department.
*/
INSERT INTO Teachers(id, name, surname, email, start_date, dept_num)
VALUES (1, 'Luz', 'Martinez', 'luz.martinez@iesabastos.org', '01/01/90', 10),
       (2, 'Cristina', 'Ausina', 'c.ausina@iesabastos.org', '01/02/90', 10),
       (3, 'Imma', 'Cabanes', 'i.cabanes@iesabastos.org', '01/03/90', 10),
       (4, 'Mercedes', 'Sánchez', 'm.sanchez@iesabastos.org', NULL, 40);

/* All teachers from INFORMATICA department. */
SELECT Teachers.id, Teachers.name, Teachers.surname, Teachers.email, Teachers.start_date, Departments.name
FROM Teachers,
     Departments
WHERE Teachers.dept_num == Departments.dept_num
  AND Departments.name == 'INFORMATICA';

/* For each department, obtain all its data and the number of teachers it has. */
SELECT COUNT(*), Departments.name
FROM Teachers,
     Departments
WHERE Teachers.dept_num == Departments.dept_num
GROUP BY Teachers.dept_num;

/* Show the name and surname of each teacher and their department, ordered alphabetically by surname. */
SELECT Teachers.name, Teachers.surname, Departments.name
FROM Teachers,
     Departments
WHERE Teachers.dept_num == Departments.dept_num
ORDER BY surname;