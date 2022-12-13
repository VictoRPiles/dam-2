let datos = [
	{nombre: 'Nacho', telefono: '966112233', edad: 40},
	{nombre: 'Ana', telefono: '911223344', edad: 35},
	{nombre: 'Mario', telefono: '611998877', edad: 15},
	{nombre: 'Laura', telefono: '633663366', edad: 17}
]
nuevaPersona({nombre: 'Juan', telefono: '965661564', edad: 60})
nuevaPersona({nombre: 'Rodolfo', telefono: '910011001', edad: 20})
borrarPersona('910011001')

function nuevaPersona(dato) {
	datos.push(dato)
}

function borrarPersona(dato) {
	datos = datos.filter(persona => persona.telefono !== dato)
}

console.log(datos)