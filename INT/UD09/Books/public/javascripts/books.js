'use strict'

const imageWrapper = document.getElementById('wrapper')

const fetch = require('node-fetch')
const recurso = 'http://127.0.0.1:3000'
//Get para todos los libros:
fetch(recurso + '/books')
	.then(res => res.json())
	.then(json => inicio(json))

function inicio(json) {
	let innerHTML = ''
	for (const element in json) {
		let image = json[element].img
		innerHTML += `<div><img width="200px" height="auto" src="http://127.0.0.1:3000/images/${image}"></div>`
	}
	imageWrapper.innerHTML = innerHTML
}