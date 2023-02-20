const {MongoClient} = require('mongodb');

const uri = 'mongodb+srv://<usuari>:<contrasenya>@<cluster>';

const client = new MongoClient(uri);

/**
 * Run a MongoDB query.
 * @param query The query, pass "" for find all.
 * @returns A promise with the retrieved documents.
 */
async function runQuery(query) {
    try {
        const database = client.db('books');
        const collection = database.collection('books');

        return await collection.find(query).toArray();
    } catch (e) {
        /* Ensures that the client will close when you finish / error */
        await client.close();
    }
}

/**
 Fill the image gallery with the book images.
 */
async function fillImageGallery() {
    const images = document.getElementById('gallery-images');
    await runQuery('').then(result => result.forEach(element => {
        console.log('Inserting "' + element.title + '" in the image gallery...');
        let newImageHTML = `
        <!-- Container -->
        <div class="col-lg-2 col-md-4 col-6 w-auto" id="${element.title}-container">
            <!-- Image -->
            <figure class="figure rounded-2 shadow-lg">
                <img alt="images/${element.img}" class="img-fluid img-thumbnail figure-img shadow m-2" src="images/${element.img}" style="width: 200px; height: 300px; object-fit: fill">
                <!-- Caption -->
                <p class="figure-caption my-0 mx-2"><strong>${element.title}</strong></p>
                <p class="figure-caption mt-0 mx-2">${element.author}</p>
            </figure>
        </div>`;
        images.innerHTML += newImageHTML;
    }));
}

/**
 * Add an EventListener to the images.
 *
 * When an image is clicked, the value of the element 'cart-button' will be increased.
 */
async function addImageOnClickListener() {
    const cartButton = document.getElementById('cart-button');
    const itemsInCart = document.getElementById('items-in-cart');
    let index = 0;
    await runQuery('').then(result => result.forEach(element => {
        let imageContainer = document.getElementById(element.title + '-container');
        imageContainer.addEventListener('click', () => {
            cartButton.textContent++;

            index++;
            itemsInCart.innerHTML +=
                `<a aria-current="true" class="list-group-item list-group-item-action py-3 lh-tight" href="#" id="item-on-cart-${index}">
                    <img alt="${element.img}"alt="images/EXTERNAL_FRAGMENT" class = 'img-fluid rounded' src="images/${element.img}"${element.img} style="width: 40px; height: 60px; object-fit: fill">
                    <div class="d-flex w-100 align-items-center justify-content-between">
                        <strong class="mb-1">${element.title}</strong>
                    </div>
                    <div class="col-10 mb-1 small">${element.author}</div>
                    <button class="btn btn-outline-danger" type="button" id="delete-button-${index}">
                        <svg class="bi bi-trash" fill="currentColor" height="16" viewBox="0 0 16 16" width="16" xmlns="http://www.w3.org/2000/svg">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                            <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" fill-rule="evenodd"/>
                        </svg>
                    </button>
                </a>`;
            deleteButtonClickListener(index);
        });
    }));
}

function deleteButtonClickListener(index) {
    const button = document.getElementById('delete-button-' + index);

    button.addEventListener('click', () => {
        let index2 = index
        const item = document.getElementById('item-on-cart-' + index2);
        console.log(button);
        console.log(item);
        item.innerHTML = '';
        console.log(item);
    });
}

fillImageGallery().then(() => addImageOnClickListener());

const cartButton = document.getElementById('cart-button');
const sidebar = document.getElementById('sidebar');

cartButton.addEventListener('click', () => {
    console.log('Cart button clicked');
    if (sidebar.style.display === 'none') {
        sidebar.style.setProperty('display', 'flex');
    } else if (sidebar.style.display === 'flex') {
        sidebar.style.setProperty('display', 'none');
    }
});
