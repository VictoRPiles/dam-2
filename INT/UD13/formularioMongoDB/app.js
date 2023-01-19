const {MongoClient} = require("mongodb");

// Replace the uri string with your connection string.
const uri = "mongodb+srv://NedLudd:CdwPLwYuXfDUyW7D@cluster0.9m4bmmq.mongodb.net/?retryWrites=true&w=majority";

const client = new MongoClient(uri);
let collection
let clientes = []

async function run() {
    try {
        const database = client.db('clientes');
        collection = database.collection('clientes');

        // Query for a result that has the title 'Back to the Future'
        const query = {};
        clientes = await collection.find(query).toArray()
    } finally {
        // Ensures that the client will close when you finish/error
        await client.close();
    }
}

run().catch(console.dir);


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
btnGuardar.addEventListener('click', async () => {
    console.log(clientes[posicion])
    await client.connect()
    await collection.updateOne(
        {
            dni: clientes[posicion].dni,
            nombre: clientes[posicion].nombre,
            telefono: clientes[posicion].telefono
        },
        {
            $set: {
                dni: document.getElementById('dni').value,
                nombre: document.getElementById('nombre').value,
                telefono: document.getElementById('telefono').value
            }
        }
    )
    guardarEnMemoria()
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
