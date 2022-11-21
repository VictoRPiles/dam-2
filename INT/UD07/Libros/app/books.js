const fs = require('fs')
/* HTML Elements */
const table = document.getElementById('books-table')
const reloadBtn = document.getElementById('reload-btn')
const hiddenContainer = document.getElementById('hidden-container')
const bookInfoContainer = document.getElementById('book-info')
const sidebarPortrait = document.getElementById('sidebar-portrait')
const sidebarBookTitle = document.getElementById('book-title')
const sidebarBookAuthor = document.getElementById('book-author')
const sidebarBookPrice = document.getElementById('book-price')
const sidebarBookIsBook = document.getElementById('book-type-book')
const sidebarBookIsComic = document.getElementById('book-type-comic')
const saveBtn = document.getElementById('save-btn')

/* JSON Data */
const booksJson = require('../res/data/books.json')

let thisBookId = 0

fillTable()
clickablePortraits()

/**
 * Fills the {@link table} with the {@link booksJson} data.
 * <p>
 * Sets an id for each row.
 */
function fillTable() {
	let tbodyHTML = ''
	for (const book in booksJson) {
		tbodyHTML += `
         <tr id="table-row${book}">
            <td>${booksJson[book].title}</td>
            <td>
                <img class="img-circle media-object" src="../res/img/${booksJson[book].img}" width="32" height="32" alt="portrait"">
            </td>
        </tr>
        `
	}
	table.innerHTML = tbodyHTML
}

/**
 * Displays or hides the {@link hiddenContainer}.
 */
reloadBtn.addEventListener('click', () => {
	if (hiddenContainer.style.display === 'none') {
		hiddenContainer.style.display = 'block'
	} else {
		hiddenContainer.style.display = 'none'
	}
})

/**
 * Adds a click eventListener for each row in the table.
 * <p>
 * Changes the info to the info of the clicked book.
 * <p>
 * Displays the {@link bookInfoContainer} if it's hidden.
 */
function clickablePortraits() {
	for (const book in booksJson) {
		document.getElementById('table-row' + book).addEventListener('click', () => {
			thisBookId = book
			/* Change the image */
			sidebarPortrait.setAttribute('src', '../res/img/' + booksJson[book].img)
			/* Change the form placeholder */
			sidebarBookTitle.value = booksJson[book].title
			sidebarBookAuthor.value = booksJson[book].author
			sidebarBookPrice.value = booksJson[book].price
			if (booksJson[book].libro) {
				sidebarBookIsBook.checked = true
				sidebarBookIsComic.checked = false
			} else {
				sidebarBookIsBook.checked = false
				sidebarBookIsComic.checked = true
			}
			/* Display block if it's hidden */
			if (bookInfoContainer.style.display === 'none') {
				bookInfoContainer.style.display = 'block'
			}
		})
	}
}

/**
 * Saves the updated info to the JSON.
 */
saveBtn.addEventListener('click', () => {
	booksJson[thisBookId].title = sidebarBookTitle.value
	booksJson[thisBookId].author = sidebarBookAuthor.value
	booksJson[thisBookId].price = sidebarBookPrice.value
	booksJson[thisBookId].libro = sidebarBookIsBook.checked

	console.log(booksJson)
	//TODO
	fs.writeFileSync('../res/data/books.json', booksJson)
})