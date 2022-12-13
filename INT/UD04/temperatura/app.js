const botonConvertir = document.getElementById("convertir")
const botonLimpiar = document.getElementById("limpiar")

const inputCelsius = document.getElementById("celsius")
const inputKelvin = document.getElementById("kelvin")

botonConvertir.addEventListener("click", () => {
    let valorCelsius = inputCelsius.value
    let valorKelvin = inputKelvin.value

    /* de celsius a kelvin */
    if (valorCelsius != "") {
        console.log("Valor celsius: " + valorCelsius)
        valorKelvin = (Number(valorCelsius) + 273.15).toFixed(2)
        console.log("Valor kelvin: " + valorKelvin)
    }
    /* de kelvin a celsius */
    if (valorKelvin != "") {
        console.log("Valor kelvin: " + valorKelvin)
        valorCelsius = (Number(valorKelvin) - 273.15).toFixed(2)
        console.log("Valor celsius: " + valorCelsius)
    }

    inputKelvin.value = valorKelvin
    inputCelsius.value = valorCelsius
})

botonLimpiar.addEventListener("click", () => {
    inputCelsius.value = ""
    inputKelvin.value = ""
})