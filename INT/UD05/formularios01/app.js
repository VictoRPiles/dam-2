const fs = require('fs')

/* cargar el fichero de clientes en el array */
let fichero = fs.readFileSync('clientes.json')
let clientes = new Array()

clientes = JSON.parse(fichero)

let posicion = 0
let inicioApp = true

/**
 * Recoloca la posición a la primera y muestra los datos.
 * 
 * Llama a {@code guardarEnMemoria}.
 * Llama a {@code mostrarDatos}.
 */
const btnPrimero = document.getElementById('primero')
btnPrimero.addEventListener('click', () => {
    guardarEnMemoria()

    posicion = 0
    mostrarDatos()
})
/**
 * Recoloca la posición a la siguiente y muestra los datos.
 * 
 * Llama a {@code guardarEnMemoria}.
 * Llama a {@code mostrarDatos}.
 */
const btnSiguiente = document.getElementById('siguiente')
btnSiguiente.addEventListener('click', () => {
    guardarEnMemoria()

    if (posicion < clientes.length - 1) {
        posicion++
    }
    mostrarDatos()
})
/**
 * Recoloca la posición a la anterior y muestra los datos.
 * 
 * Llama a {@code guardarEnMemoria}.
 */
const btnAnterior = document.getElementById('anterior')
btnAnterior.addEventListener('click', () => {
    guardarEnMemoria()

    if (posicion > 0) {
        posicion--
    }
    mostrarDatos()
})
/**
 * Recoloca la posición a la última y muestra los datos.
 * 
 * Llama a {@code guardarEnMemoria}.
 * Llama a {@code mostrarDatos}.
 */
const btnUltimo = document.getElementById('ultimo')
btnUltimo.addEventListener('click', () => {
    guardarEnMemoria()

    posicion = clientes.length - 1
    mostrarDatos()
})
/**
 * Borra del array de clientes en cliente en la posicion actual.
 * Recoloca la posición a la anterior si esta no era la primera, o a la siguiente si lo era.
 * 
 * Llama a {@code guardarEnMemoria}.
 * Llama a {@code mostrarDatos}.
 */
const btnBorrar = document.getElementById('borrar')
btnBorrar.addEventListener('click', () => {
    guardarEnMemoria()

    clientes.splice(posicion, 1)

    /* Cambio de posición a la siguiente o anterior */
    if (posicion > 0) {
        posicion--
    } else if (posicion < clientes.length - 1) {
        posicion++
    }
    mostrarDatos()
})
const btnInsertar = document.getElementById('carpeta')
btnInsertar.addEventListener('click', () => {
    guardarEnMemoria()
    limpiarDatos()

    btnAnterior.attributes.di
})
/**
 * Guarda el array de clientes en el fichero json.
 * 
 * Llama a {@code guardarEnMemoria}.
 */
const btnGuardar = document.getElementById('disquete')
btnGuardar.addEventListener('click', () => {
    guardarEnMemoria()

    fs.writeFileSync('clientes.json', JSON.stringify(clientes))
})
/**
 * Muestra los datos del cliente con la posición actual en el formulario.
 */
function mostrarDatos() {
    document.getElementById('dni').value = clientes[posicion].dni
    document.getElementById('nombre').value = clientes[posicion].nombre
    document.getElementById('telefono').value = clientes[posicion].telefono
}
/**
 * Limpia los datos del formulario
 */
function limpiarDatos() {
    document.getElementById('dni').value = ""
    document.getElementById('nombre').value = ""
    document.getElementById('telefono').value = ""
}
/**
 * Si no es el primer botón que se toca en la App, guarda los cambios en la posición actual del array.
 * Cambia el valor de inicioApp a {@code false}.
 */
function guardarEnMemoria() {
    if (!inicioApp) {
        clientes[posicion].dni = document.getElementById('dni').value
        clientes[posicion].nombre = document.getElementById('nombre').value
        clientes[posicion].telefono = document.getElementById('telefono').value
    }
    inicioApp = false
}
