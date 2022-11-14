const fs = require('fs')

/* ========== DOM ========== */
const superheroImg = document.getElementById('img-superhero')

const inputVote = document.getElementById('input-vote')

const btnVote = document.getElementById('btn-vote')
const btnNext = document.getElementById('btn-next')
const btnPrev = document.getElementById('btn-prev')

const tableSuperheroData = document.getElementById('td-superhero-data')
const tablePublisherData = document.getElementById('td-publisher-data')
const tableAlterData = document.getElementById('td-alter-data')
const tableAppearanceData = document.getElementById('td-appearance-data')
const tableCharactersData = document.getElementById('td-characters-data')

/* ========== Read superheroes data ========== */
const rawSuperheroesData = fs.readFileSync('res/data/superheroes.json')
const jsonSuperheroesData = JSON.parse(rawSuperheroesData.toString())

/* ========== Superhero ========== */
let superheroIndex = 0
changeSuperhero(superheroIndex)

btnNext.addEventListener('click', () => {
    if (superheroIndex < jsonSuperheroesData.length - 1) {
        superheroIndex++
        changeSuperhero(superheroIndex)
    }
})

btnPrev.addEventListener('click', () => {
    if (superheroIndex > 0) {
        superheroIndex--
        changeSuperhero(superheroIndex)
    }
})

function changeSuperhero(index) {
    tableSuperheroData.innerText = jsonSuperheroesData[index].superhero
    tablePublisherData.innerText = jsonSuperheroesData[index].publisher
    tableAlterData.innerText = jsonSuperheroesData[index].alter_ego
    tableAppearanceData.innerText = jsonSuperheroesData[index].first_appearance
    tableCharactersData.innerText = jsonSuperheroesData[index].characters
    superheroImg.setAttribute('src', ('../res/img/' + jsonSuperheroesData[index].img))
}

/* ========== Read votes data ========== */
const rawVotesData = fs.readFileSync('res/data/votes.json')
const jsonVotesData = JSON.parse(rawVotesData.toString())

/* ========== Vote ========== */
btnVote.addEventListener('click', () => {
    let voteName = inputVote.value.toString()

    let insert = {
        id: superheroIndex,
        voter: voteName
    }

    jsonVotesData.push(insert)

    fs.writeFileSync('res/data/votes.json', JSON.stringify(jsonVotesData))
})