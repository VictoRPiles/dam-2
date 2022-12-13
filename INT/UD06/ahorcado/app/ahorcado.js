const ePalabra = document.getElementById('input-palabra');
const eLetra = document.getElementById('input-letra');
const eGuiones = document.getElementById('p-guiones');
const eImagenAhorcado = document.getElementById('img-ahorcado');

let fallos = 0;
let palabraInicial;

/**
 * Añade un evento al elemento {@code ePalabra}.
 * Reemplaza el texto de {@code eGuiones} por un guion por cada letra.
 * Inhabilita la entrada de más texto.
 */
ePalabra.addEventListener('keyup', (e) => {
    if (e.key === 'Enter') {
        let guiones = "";

        palabraInicial = ePalabra.value;

        for (let i = 0; i < ePalabra.value.length; i++) {
            guiones += "-";
        }
        eGuiones.innerText = guiones;
        ePalabra.disabled = true
    }
})

/**
 * Añade un evento al elemento {@code eLetra}.
 * Comprueba si la letra introducida en {@code eLetra} se encuentra en el {@code ePalabra}.
 * Reemplaza un guion por la letra correspondiente.
 */
eLetra.addEventListener('keyup', (e) => {
    if (e.key === 'Enter') {
        let acierto = false;

        let guiones = eGuiones.innerText;
        let letra = eLetra.value;

        for (let i = 0; i < ePalabra.value.length; i++) {
            if (letra === ePalabra.value[i]) {
                guiones = guiones.substring(0, i) + letra + guiones.substring(i + 1, guiones.length);
                acierto = true;
            }
        }

        if (!acierto) {
            fallos++;
            checkFallos();
        }
        eGuiones.innerText = guiones;
        checkVictoria();
    }
})

/**
 * Comprueba si ha ganado.
 * Si se ha ganado inserta la imagen de victoria.
 */
function checkVictoria() {
    console.log(eGuiones.innerText);
    console.log(palabraInicial)
    if (eGuiones.innerText === palabraInicial) {
        ePalabra.disable = true;
        eLetra.disable = true;
        eImagenAhorcado.src = '../img/win.png';
    }
}

/**
 * Cambia la imagen del ahorcado según los fallos.
 * Si los fallos son >= 7, inserta la imagen de derrota.
 */
function checkFallos() {
    console.log("Fallos: " + fallos);

    if (fallos >= 7) {
        eImagenAhorcado.src = '../img/skull.jpg';
    } else eImagenAhorcado.src = '../img/' + fallos + '.png';
}