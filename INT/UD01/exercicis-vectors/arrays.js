let dades = [
    {nombre: "Nacho", telefono: "966112233", edad: 40},
    {nombre: "Ana", telefono: "911223344", edad: 35},
    {nombre: "Mario", telefono: "611998877", edad: 15},
    {nombre: "Laura", telefono: "633663366", edad: 17}
];
console.log("Array original")
console.log(dades);

/* afegir elements al array. */
dades.push(
    {nombre: "Pedro", telefono: "611944444", edad: 25},
    {nombre: "Julia", telefono: "633232323", edad: 37}
)
console.log("Afegint dos elements...")
console.log(dades);

/* ordenar el array per edad. */
dades.sort((json1, json2) => {
    return json1.edad - json2.edad;
});
console.log("Ordenant per edad...")
console.log(dades);

/* ordenar el array per nom. */
dades.sort((json1, json2) => {
    return json1.nombre.localeCompare(json2.nombre);
});
console.log("Ordenant per nom...")
console.log(dades);

/* crear un nou array amb els objectes JSON que tenen el valor edad > 30. */
let majorsDeTrenta = dades.filter((json) => {
    return json.edad > 30;
});
console.log("Generant array amb majors que 30...")
console.log(majorsDeTrenta);