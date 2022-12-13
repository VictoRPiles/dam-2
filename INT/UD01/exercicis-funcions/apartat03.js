let ocurrencies = (cadena, caracter) => {
    if (caracter.length !== 1) throw new Error("El segon argument no té una longitud de 1.");

    let contador = 0;
    for (let i = 0; i < cadena.length; i++) {
        if (cadena[i].toLowerCase() === caracter.toLowerCase()) contador++;
    }

    return contador;
}
let cadena = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas molestie massa libero, ac consectetur ex scelerisque eu. Aenean sit amet.";
console.log(ocurrencies(cadena, "a") + " ocurrències.");
console.log(ocurrencies(cadena, "e") + " ocurrències.");
console.log(ocurrencies(cadena, "r") + " ocurrències.");
/* llança l'excepció */
try {
    console.log(ocurrencies(cadena, "Lorem"));
} catch (e) {
    console.log(e.message);
}