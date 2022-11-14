/* DOM */
const valorCredit = document.getElementById('valor-credit')
const aposta = document.getElementById('input-aposta')
const palo = document.getElementById('select-palo')
const btnApostar = document.getElementById('btn-apostar')
const btnReset = document.getElementById('btn-reset')
const imgCarta01 = document.getElementById('img-carta01')
const imgCarta02 = document.getElementById('img-carta02')
const imgCarta03 = document.getElementById('img-carta03')
const imgCarta04 = document.getElementById('img-carta04')
const imgCarta05 = document.getElementById('img-carta05')
const imgCarta06 = document.getElementById('img-carta06')
const imgAnimacio = document.getElementById('img-animacio')
const notificacio = document.getElementById('notificacio')

const imgRuta = '../res/img/'
const imgCartes = [imgCarta01, imgCarta02, imgCarta03, imgCarta04, imgCarta05, imgCarta06]

let quantitatAposta
let paloAposta
let comptadorAcerts = 0
let comptadorFallos = 0
let guanyador = false


/* ========== APOSTAR ========== */
function apostar()
{
	const MIN = 100
	const MAX = 500

	console.log('Aposta: ' + quantitatAposta + ' a ' + paloAposta)

	/* Si no està en els llimits, no s'aposta */
	if (quantitatAposta < MIN || quantitatAposta > MAX) {
		console.log('Aposta fora de els límits')
		notificacio.innerText = 'No es pot apostar fora de els límits (100-500)'
		notificacio.opened = true
		return
	}

	/* Aposta en nombres rojos */
	if (quantitatAposta > parseInt(valorCredit.textContent)) {
		console.log('Aposta en nombres rojos')
		notificacio.innerText = 'Estàs apostant en nombres rojos'
		notificacio.opened = true
	}

	valorCredit.textContent -= quantitatAposta

	colorValorCredit()
	btnApostar.disabled = true
	btnApostar.style.background = 'grey'
}

btnApostar.addEventListener('click', () =>
{
	quantitatAposta = aposta.value
	paloAposta = palo.value
	apostar(quantitatAposta, paloAposta)
})


/* ========== RESET ========== */
function reset()
{
	console.log('Reset')
	for (let i = 0; i < imgCartes.length; i++) {
		imgCartes[i].setAttribute('src', imgRuta + 'dorso.png')
	}
	btnApostar.disabled = false
	btnApostar.style.backgroundColor = 'green'
	btnReset.style.backgroundColor = 'grey'

	quantitatAposta = 0
	comptadorAcerts = 0
	comptadorFallos = 0
	guanyador = false
	imgAnimacio.setAttribute('src', imgRuta + 'neutral.png')
}

btnReset.addEventListener('click', () =>
{
	reset()
})


/* ========== CARTA ========== */
function cartaRandom(i)
{
	const cartes = ['bastos', 'copes', 'espases', 'oros']

	/* Si ja s'ha girat la carta, no continua */
	if (imgCartes[i].getAttribute('src') !== imgRuta + 'dorso.png') {
		return
	}
	/* Si no s'ha apostat, no continua */
	if (btnApostar.disabled === false) {
		return
	}

	/* Nombre aleatori entre 1 i 4 */
	let random = Math.floor(Math.random() * (cartes.length - 1 + 1) + 1)
	console.log('Random: ' + random)
	let carta = cartes[random - 1]

	console.log('Carta:' + carta)
	/* Canvia el valor src de la carta si no s'ha canviat encara */
	imgCartes[i].setAttribute('src', imgRuta + carta + '.png')

	checkPalo(quantitatAposta, carta)
}

imgCarta01.addEventListener('click', () =>
{
	cartaRandom(imgCartes.indexOf(imgCarta01))
})

imgCarta02.addEventListener('click', () =>
{
	cartaRandom(imgCartes.indexOf(imgCarta02))
})
imgCarta03.addEventListener('click', () =>
{
	cartaRandom(imgCartes.indexOf(imgCarta03))
})
imgCarta04.addEventListener('click', () =>
{
	cartaRandom(imgCartes.indexOf(imgCarta04))
})
imgCarta05.addEventListener('click', () =>
{
	cartaRandom(imgCartes.indexOf(imgCarta05))
})
imgCarta06.addEventListener('click', () =>
{
	cartaRandom(imgCartes.indexOf(imgCarta06))
})

/* ========== PALO ========== */
function checkPalo(quantitat, paloCarta)
{
	if (paloCarta === paloAposta) {
		comptadorAcerts++
		console.log('Acerts: ' + comptadorAcerts)
	}
	else {
		comptadorFallos++
		console.log('Fallos: ' + comptadorFallos)
	}

	if (comptadorAcerts === 3 && !guanyador) {
		console.log('Guanyador')
		guanyador = true
		/* Si guanya, se suma el doble de la quantitat apostada */
		valorCredit.textContent = parseInt(valorCredit.textContent) + parseInt(quantitat * 2)
		colorValorCredit()
		/* Canvia l'animació */
		imgAnimacio.setAttribute('src', imgRuta + 'guanya.png')
	}
	if (comptadorFallos > 3) {
		/* Canvia l'animació */
		imgAnimacio.setAttribute('src', imgRuta + 'pert.png')
	}
}

/* ========== EXTRA ========== */
function colorValorCredit()
{
	/* Canvia el color del valor del crèdit depenent de la quantitat */
	if (parseInt(valorCredit.textContent) > 0) {
		valorCredit.style.color = 'green'
	}
	else if (parseInt(valorCredit.textContent) < 0) {
		valorCredit.style.color = 'red'
	}
	else {
		/* Si és 0 */
		valorCredit.style.color = 'black'
	}
}