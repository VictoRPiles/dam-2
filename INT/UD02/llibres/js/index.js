const fs = require('fs');

/* Lectura del fitxer i conversió a vector. */
const path = '../res/books.json';
const books = JSON.parse(fs.readFileSync(path).toString());
showBooks();

/* Torna l'índex d'un llibre a través del seu titol. */
function findIndexOfBookByTitle(title) {
    return books.findIndex(book => {
        return book.title === title;
    });
}

/* Afegir. */
function add(title, author, price, img, isBook) {
    let book = {
        "title": title,
        "author": author,
        "price": price,
        "img": img,
        "eslibro": isBook
    }
    books.push(book);
}

/* Eliminar. */
function remove(title) {
    const index = findIndexOfBookByTitle(title);
    (index === -1) ? console.log("No existeix este llibre") : books.splice(index, 1);
}

/*
Modificar.
Busca el llibre pel titol, reemplaça les dades per les que es posen per paràmetre
* a no ser que el paràmetre siga null, en aquest cas no modifica la dada.
*/
function modify(title, newTitle, newAuthor, newPrice, newImg, newIsBook) {
    let index = findIndexOfBookByTitle(title);
    (index === -1) ? console.log("No existeix este llibre") : () => {
        const book = books[index];

        if (newTitle != null) book.title = newTitle;
        if (newAuthor != null) book.author = newAuthor;
        if (newPrice != null) book.price = newPrice;
        if (newImg != null) book.img = newIsBook;
        if (newIsBook != null) book.eslibro = newIsBook;

        books[index] = book;
    }
}

/* Veure els llibres. */
function showBooks() {
    console.log(books.filter(book => book.eslibro === true));
}

/* Veure els comics (els que no són llibres). */
function showComics() {
    console.log(books.filter(book => book.eslibro === false));
}

/* Guardar. */
function save() {
    fs.writeFileSync(path, JSON.stringify(books));
}