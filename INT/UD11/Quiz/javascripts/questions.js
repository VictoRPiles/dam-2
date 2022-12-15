/* ==================== DATABASE ==================== */

const {MongoClient} = require('mongodb')
// Connection URI
const uri = 'mongodb://localhost:27017'
// Create a new MongoClient
const client = new MongoClient(uri)

async function run() {
	try {
		// Connect the client to the server (optional starting in v4.7)
		await client.connect()
		// Establish and verify connection
		await client.db('admin').command({ping: 1})
		console.log('Connected successfully to server')

		// Call main function
		await main()
	} finally {
		// Ensures that the client will close when you finish/error
		await client.close()
	}
}

/* ==================== PROGRAM LOGIC ==================== */

const questionsContainer = document.getElementById('questions')
const checkButton = document.getElementById('check')
const results = document.getElementById('results')

async function main() {
	// Get all records from: quiz -> questions
	const result = await client.db('quiz').collection('questions').find({}).toArray()

	let correct = 0
	let incorrect = 0

	let innerHTML = ''
	let contador = 0

	/*
	 For each question, generate the HTML
	 The id for radio group will be radios + question._id
	 The id for radios will be radio + question._id + number
	*/
	result.forEach(question => {
		contador++
		innerHTML += `
			<img class="img-circle media-object pull-left" src="images/${contador}.png" width="32" height="32" alt="question-image">
			<div class="media-body">
				<strong>${question.pregunta}</strong>
				<div><input type="radio" id="radio${question._id}1" name="radios${question._id}" value="a" > ${question.rA}</div>
				<div><input type="radio" id="radio${question._id}2" name="radios${question._id}" value="b" > ${question.rB}</div>
				<div><input type="radio" id="radio${question._id}3" name="radios${question._id}" value="c" > ${question.rC}</div>
			</div>
			<hr>`
	})
	questionsContainer.innerHTML = innerHTML

	// When the 'check' button is clicked
	checkButton.addEventListener('click', () => {
		correct = 0
		incorrect = 0

		// For each question, check the answer and the correct answer
		result.forEach(question => {
			let correctAnswer = question.correcta

			let radio1 = document.getElementById('radio' + question._id + '1')
			let radio2 = document.getElementById('radio' + question._id + '2')
			let radio3 = document.getElementById('radio' + question._id + '3')

			if (radio1.checked && radio1.value === correctAnswer ||
				radio2.checked && radio2.value === correctAnswer ||
				radio3.checked && radio3.value === correctAnswer) {
				correct++
			} else {
				incorrect++
			}
		})

		// Print the result in the header
		results.innerText = 'Correct: ' + correct + ', Incorrect: ' + incorrect
	})
}

// Start the program
run().catch(console.dir)