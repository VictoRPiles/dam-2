let coincidencia = (cadena, subCadena) => {
    return cadena.toLowerCase()
        .includes(subCadena.toLowerCase());
}
console.log(
    coincidencia("Santiago de Compostela", "COMPO") ? "Conté la cadena." : "No conté la cadena.");
console.log(
    coincidencia("Santiago de Compostela", "santi") ? "Conté la cadena." : "No conté la cadena.");
console.log(
    coincidencia("Santiago de Compostela", "caden") ? "Conté la cadena." : "No conté la cadena.");
