const {MongoClient} = require("mongodb");

// Replace the uri string with your connection string.
const uri = "mongodb+srv://<usuari>:<contrasenya>@<cluster>";

const client = new MongoClient(uri);

async function run() {
    try {
        const database = client.db('clientes');
        const movies = database.collection('clientes');

        // Query for a movie that has the title 'Back to the Future'
        const query = {nombre: 'Christoforo'};
        const movie = await movies.findOne(query);

        console.log(movie);
    } finally {
        // Ensures that the client will close when you finish/error
        await client.close();
    }
}

run().catch(console.dir);
