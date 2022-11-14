/* Contador de clicks */
const btnContador = document.getElementById('btn-contador')
const contador = document.getElementById('text-contador')
let i = 0;
btnContador.addEventListener('click', () => {
    if (i !== 69) contador.innerHTML = "Has fet click " + i + " vegades"
    else contador.innerHTML = "Has fet click " + i + " vegades <3"
    console.log(++i)
})

/* Formulari */
const btnForm = document.getElementById('btn-form')
const textForm = document.getElementById('text-form')
const inputForm = document.getElementById('input-form')
btnForm.addEventListener('click', () => {
    textForm.innerHTML = "Has escrit " + String(inputForm.value)
})
inputForm.addEventListener("keyup", (e) => {
    if (e.key === "Enter") {
        textForm.innerHTML = "Has escrit " + String(inputForm.value)
    }
})

/* Tabla */
const tabla = document.getElementById('tabla-datos')
clientes = [
    {'id': 1, 'name': 'Victor', 'apellido': 'piles'},
    {'id': 2, 'name': 'Sergio', 'apellido': 'Castillo'},
    {'id': 3, 'name': 'Nando', 'apellido': 'piles'}
]
for (const c in clientes) {
    tabla.innerHTML += '<tr>' +
        '<th scope="row">' + c.id + '</th>' +
        '<td>Larry</td>' +
        '<td>the Bird</td>' +
        '<td>@twitter</td>' +
        '</tr>'
}