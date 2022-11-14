/* array de objectes JSON. */
let dades = [
    {nombre: "Nacho Vidal", telefono: "966112233", edad: 40},
    {nombre: "Ana Frank", telefono: "911223344", edad: 35},
    {nombre: "Mario Bros", telefono: "611998877", edad: 15},
    {nombre: "Laura Pausini", telefono: "633663366", edad: 17}
];

/* ordena el array de objectes JSON per edad. */
dades.sort((json1, json2) => {
    return json1.edad - json2.edad;
});

console.log(dades);

console.log("El major es:")
console.log(dades[dades.length - 1]);