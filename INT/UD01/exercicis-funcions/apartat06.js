function apartat06() {
    let array = [1, 2, 3, 4];
    console.log(array);
    array.push(5, 6);
    console.log(array);
    array.unshift(-1, 0);
    console.log(array);
    /* des de la posició 3, elimina 3 caràcters (3, 4, 5) */
    array.splice(3, 3);
    console.log(array);
    /* des de la penúltima posició, elimina 0 elements i inserta el 7 i el 8 */
    array.splice(array.length - 1, 0, 7, 8);
    console.log(array);
    console.log(array.join("==>"));
}

apartat06()