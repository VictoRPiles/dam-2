function apartat08(...cadenes) {
    console.log(cadenes);
    console.log(cadenes.sort((cadena1, cadena2) => cadena1.length - cadena2.length));
}

apartat08("un", "dos", "tres", "pollito", "ingles");