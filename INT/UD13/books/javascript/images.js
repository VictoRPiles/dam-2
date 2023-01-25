const {MongoClient} = require('mongodb');

const uri = 'mongodb+srv://admin:admin@cluster0.9m4bmmq.mongodb.net?retryWrites=true&w=majority';

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
                <img alt="" class="img-fluid img-thumbnail figure-img shadow m-2" src="images/${element.img}" style="width: 200px; height: 300px; object-fit: fill">
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
    await runQuery('').then(result => result.forEach(element => {
        let imageContainer = document.getElementById(element.title + '-container');
        imageContainer.addEventListener('click', () => {
            cartButton.textContent++;
            console.log('Items on cart -> ' + cartButton.textContent);
        });
    }));
}

fillImageGallery().then(() => addImageOnClickListener());