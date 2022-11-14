/* array de objectes JSON. */
let dades = [
    {nombre: "Nacho", telefono: "966112233", edad: 40},
    {nombre: "Ana", telefono: "911223344", edad: 35},
    {nombre: "Mario", telefono: "611998877", edad: 15},
    {nombre: "Laura", telefono: "633663366", edad: 17}
];

/* torna un array amb soles els noms. */
let noms = dades.map((json) => {
    return json.nombre;
});

console.log(noms);