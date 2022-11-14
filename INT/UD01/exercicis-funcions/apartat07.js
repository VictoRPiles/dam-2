function apartat07(alumne, ...notes) {
    let suma = notes.reduce((previousValue, currentValue) => previousValue + currentValue);
    let mitja = suma / notes.length;
    return `Nota mitja de ${alumne} -> ${mitja}.`;
}

console.log(apartat07("Victor", 5, 10));
console.log(apartat07("Ferran", 8, 10));
console.log(apartat07("Pepe", 4.25, 6, 8.5, 9));