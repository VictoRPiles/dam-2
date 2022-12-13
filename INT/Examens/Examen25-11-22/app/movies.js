/* JSON */
const fs = require('fs')
const colors = JSON.parse(fs.readFileSync('res/data/colors.json').toString())
const movies = JSON.parse(fs.readFileSync('res/data/movies.json').toString())
/* DOM */
const categoriesSubsection = document.getElementById('categories-subsection')
const portrait = document.getElementById('portrait')
const btnVisit = document.getElementById('btn-visit-movie')
const title = document.getElementById('title')
const time = document.getElementById('time')
const rating = document.getElementById('rating')
const description = document.getElementById('description')
/* Electron Dialog */
const {dialog} = require('@electron/remote')
/* Globals */
let movie = 0
let forward = true

/* Initial data */
showCategories(movies[movie].genres)
showTitle(movies[movie].title)
showTime(movies[movie].date + ' ' + movies[movie].hours + 'h ' + movies[movie].minutes + 'm ')
showRating(movies[movie].rating)
showDescription(movies[movie].description)

/* ==================== Show data ==================== */
/**
 * Actualiza las categorías.
 * Lee desde colors.json los colores relacionados con las categorías y los aplica.
 * @param categories Las categorías de la película.
 */
function showCategories(categories) {
	let html
	let colorValue = 'white'
	categoriesSubsection.innerHTML = ''

	for (const category in categories) {
		for (const color in colors) {
			/* Match color with category */
			if (colors[color].genre === categories[category]) {
				colorValue = colors[color].color
				console.log(colorValue)
				break
			}
		}
		/* Prepare the HTML object */
		html = `
		<div class="padded-horizontally-less">
			<span style="color: ${colorValue}" class="icon icon-record"></span>
			<span style="color: ${colorValue}">${categories[category]}</span>
		</div>`

		categoriesSubsection.innerHTML += html
	}
}

/**
 * Muestra el título.
 * @param movieTitle El titulo.
 */
function showTitle(movieTitle) {
	title.innerHTML = movieTitle
}

/**
 * Muestra el año de lanzamiento y la duración.
 * @param movieTime El año de lanzamiento y la duración.
 */
function showTime(movieTime) {
	time.innerHTML = movieTime
}
/**
 * Muestra la puntuación.
 * @param movieRating La puntuación.
 */
function showRating(movieRating) {
	rating.innerHTML = movieRating
}
/**
 * Muestra la descripción.
 * @param movieDescription La descripción.
 */
function showDescription(movieDescription) {
	description.innerText = movieDescription
}

/* ==================== Change movie ==================== */
/**
 * Cambia de película al hacer click en la foto.
 * Actualiza todos los datos de la página y comprueba si és el primer o último elemento.
 */
portrait.addEventListener('click', () => {
	/* If first movie is reached */
	if (movie <= 0 && !forward) {
		dialog.showMessageBox(Window.this, {
			'type': 'info',
			'title': 'Info',
			'message': 'Forward direction'
		})
		forward = true
	}
	/* If last movie is reached */
	else if (movie >= movies.length - 1 && forward) {
		dialog.showMessageBox(Window.this, {
			'type': 'info',
			'title': 'Info',
			'message': 'Backward direction'
		})
		forward = false
	}
	/* Change image */
	if (forward) {
		movie++
		portrait.setAttribute('src', ('../res/img/' + movie + '.jpg'))
	} else {
		movie--
		portrait.setAttribute('src', ('../res/img/' + movie + '.jpg'))
	}
	/* Update data */
	showCategories(movies[movie].genres)
	showTitle(movies[movie].title)
	showTime(movies[movie].date + ' ' + movies[movie].hours + 'h ' + movies[movie].minutes + 'm ')
	showRating(movies[movie].rating)
	showDescription(movies[movie].description)
})

/* ==================== Visit movie ==================== */
/**
 * Permite visitar la página web que hay en el json de la película.
 */
btnVisit.addEventListener('click', () => {
	window.open(movies[movie].website)
})