const {MongoClient} = require("mongodb");

const uri = "mongodb+srv://admin:admin@cluster0.9m4bmmq.mongodb.net?retryWrites=true&w=majority";

const client = new MongoClient(uri);

async function runQuery(query) {
    try {
        const database = client.db('books');
        const collection = database.collection('books');

        return await collection.find(query).toArray()
    } finally {
        /* Ensures that the client will close when you finish / error */
        await client.close();
    }
}

runQuery({title: "Maus"}).then(result => result.forEach(element => console.log(element.img)))

const images = document.getElementById('gallery-images')

/*
<div class="col-lg-3 col-md-4 col-6">
            <img alt="" class="img-fluid img-thumbnail" src="images/0.jpg">
        </div>
 */