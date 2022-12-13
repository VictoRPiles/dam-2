const fs = require('fs')
const {dialog} = require('@electron/remote')

let sheros = require('./data/superheroes.json')
let votes = require('./data/votes.json')

let img = document.getElementById('img')
let superhero = document.getElementById('superhero')
let publisher = document.getElementById('publisher')
let alter_ego = document.getElementById('alter_ego')
let first_appearance = document.getElementById('first_appearance')
let characters = document.getElementById('characters')
let votante = document.getElementById('voter')
let titulo = document.getElementById('titulo')

let posicion = 0

let mostrar = (i) => {
	img.src = 'http://localhost:3000/images/' + sheros[i].img
	superhero.innerHTML = sheros[i].superhero
	publisher.innerHTML = sheros[i].publisher
	alter_ego.innerHTML = sheros[i].alter_ego
	first_appearance.innerHTML = sheros[i].first_appearance
	characters.innerHTML = sheros[i].characters
}

mostrar(0)

document.getElementById('btAnterior').addEventListener('click', () => {
	if (posicion === 0) {
		dialog.showErrorBox('Attention', 'First Superhero')
	} else {
		posicion--
		mostrar(posicion)
	}
})

document.getElementById('btSiguiente').addEventListener('click', () => {
	if (posicion === sheros.length - 1) {
		dialog.showErrorBox('Attention', 'Last Superhero')
	} else {
		posicion++
		mostrar(posicion)
	}
})
document.getElementById('btVotar').addEventListener('click', () => {
	let newVote = {}
	if (votante.value === '') {
		dialog.showErrorBox('Attention', 'type your name')
	} else {
		//actualizo el vectro y el fichero de votos
		newVote = {
			id: posicion,
			voter: votante.value
		}
		votes.push(newVote)
		fs.writeFileSync('./data/votes.json', JSON.stringify(votes))
		//calculo el superheroe más votado
		let maxVotes = 0
		let idmax = 0
		let vectorAux = []
		for (let i = 0; i < sheros.length; i++) {
			vectorAux = votes.filter(function (v) {
				return v.id === i
			})
			if (maxVotes < vectorAux.length) {
				maxVotes = vectorAux.length
				idmax = i
			}
		}
		//actualizo la cabecera
		titulo.innerHTML = `the most voted superhero is ${sheros[idmax].superhero} with ${maxVotes} votes`
	}
})