let valors = ((iterador, valor) => {
    let valors = [];
    /* el primer valor s'afegeix directament */
    valors.push(valor);

    for (let i = 1; i <= iterador; i++) {
        valors.push(valor * (2 * i));
    }
    
    return valors;
});
console.log(valors(4, 5));
console.log(valors(4, 6));
console.log(valors(4, 7));