let data = (cadena) => {
    let opcions = {
        /* dia de la setmana */
        weekday: 'long',
        /* el any en número */
        year: 'numeric',
        /* el nom del mes complet */
        month: 'long',
        /* el día en número */
        day: 'numeric'
    };
    let data = new Date(cadena);
    return data.toLocaleDateString("ES", opcions);
}

console.log(data("2003-11-18")); /* el meu aniversari */
console.log(data("1944-06-06")); /* dia D */
console.log(data("1492-10-12")); /* descobriment d'Amèrica */
console.log(data("0476-09-04")); /* caiguda de l'imperi Romà */