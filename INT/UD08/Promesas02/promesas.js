let datos = [
	{nombre: 'Nacho', telefono: '966112233', edad: 40},
	{nombre: 'Ana', telefono: '911223344', edad: 35},
	{nombre: 'Mario', telefono: '611998877', edad: 15},
	{nombre: 'Laura', telefono: '633663366', edad: 17}
]

nuevaPersona({nombre: 'Juan', telefono: '965661564', edad: 60}).then(r => {
	console.log('Insertando: ')
	console.log(r)
}).catch(r => {
	console.log('Error: ' + r)
})
nuevaPersona({nombre: 'Rodolfo', telefono: '910011001', edad: 20}).then(r => {
	console.log('Insertando: ')
	console.log(r)
}).catch(r => {
	console.log('Error: ' + r)
})

borrarPersona('910011001').then(r => {
	console.log('Borrando: ')
	console.log(r)
}).catch(r => {
	console.log('Error: ' + r)
})

async function nuevaPersona(dato) {
	return new Promise((resolve, reject) => {
		let existe = datos.some(persona => persona.telefono === dato.telefono)

		if (!existe) {
			resolve(dato)
		} else {
			reject('No se ha insertado')
		}
	})
}

async function borrarPersona(dato) {
	return new Promise((resolve, reject) => {
		let existe = datos.some(persona => persona.telefono === dato.telefono)

		if (existe) {
			let persona = datos.filter(persona => persona.telefono === dato)
			resolve(persona)
		} else {
			reject('No se ha encontrado')
		}
	})
}