function apartat01(cadena1, cadena2) {
    if (typeof cadena1 !== "string" ||
        typeof cadena2 !== "string") throw new Error("El paràmetre no es de tipo string.");

    if (cadena1.length > cadena2.length) return cadena1;
    else return cadena2;
}

console.log(apartat01("cadena", "cadena mes llarga"))
console.log(apartat01("cadena mes llarga", "cadena"))
/* llança l'excepció */
try {
    console.log(apartat01("cadena", 2))
} catch (e) {
    console.log(e.message);
}