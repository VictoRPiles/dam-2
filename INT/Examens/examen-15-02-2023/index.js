const {MongoClient} = require('mongodb');

let collectionsNames = [];

let collections = [];

async function run() {
	/* ########## OJO: MONGO ATLAS ########## */
	const client = await MongoClient.connect(
		'mongodb+srv://<usuari>:<contrasenya>@<cluster>',
		{useNewUrlParser: true, useUnifiedTopology: true}
	);
	try {
		const database = client.db('examen2ev');

		let collectionsList = database.listCollections().toArray();

		(await collectionsList).forEach(e => {
			collectionsNames.push(e.name);
		});

		const query = {};
		for (let collection in collectionsNames) {
			let thisCollection = database.collection(collectionsNames[collection]);
			collections.push(await thisCollection.find(query).toArray());
		}
	} finally {
		// Ensures that the client will close when you finish/error
		await client.close();
	}
}

const select = document.getElementById('select');
const tableHeader = document.getElementById('table-header');
const tableBody = document.getElementById('table-body');
const sidebar = document.getElementById('sidebar');

function getSelectedCollection() {
	let selected = select.value;
	let indexOfSelected = collectionsNames.indexOf(selected);
	return collections[indexOfSelected];
}

function createTableHeader(selectedCollection) {
	let firstCollectionElement = selectedCollection[0];
	let firstElementKeys = Object.keys(firstCollectionElement);
	tableHeader.innerHTML = '';
	for (let key in firstElementKeys) {
		tableHeader.innerHTML += `<td>${firstElementKeys[key]}</td>`;
	}
	tableHeader.innerHTML += `<td>Ver imagen</td>`;
	return selectedCollection;
}

function createTableBody(selectedCollection) {
	tableBody.innerHTML = '';
	for (let element in selectedCollection) {
		let elementKeys = Object.keys(selectedCollection[element]);
		console.log(selectedCollection[element]);
		tableBody.innerHTML += `<tr id="row${element}"></tr>`;
		let row = document.getElementById('row' + element);
		for (let key in elementKeys) {
			let value = selectedCollection[element][elementKeys[key]];
			row.innerHTML += `<td>${value}</td>`;
		}
		row.innerHTML += `<td><button id="button${element}"><span class="icon icon-camera"></span></button></td>`;
	}
}

function createTableImageButton(selectedCollection) {
	for (let element in selectedCollection) {
		let button = document.getElementById('button' + element);
		button.addEventListener('click', () => {
			console.log('button' + element);
			let collectionElement = selectedCollection[element];
			sidebar.style.display = 'initial';
			sidebar.innerHTML = `<img src="images/${collectionElement.img}" onerror="this.onerror=null;this.src='images/no-img.jpg'" width="100%" alt="${collectionElement.img}">`;
		});
	}
}

run().then(() => {
	for (let collection in collections) {
		console.log(collections[collection]);
		select.innerHTML += `<option>${collectionsNames[collection]}</option>`;
	}

	/* Primera colección */
	let selectedCollection = getSelectedCollection();
	createTableHeader(selectedCollection);
	createTableBody(selectedCollection);
	createTableImageButton(selectedCollection);

	select.addEventListener('change', () => {
		selectedCollection = getSelectedCollection();
		createTableHeader(selectedCollection);
		createTableBody(selectedCollection);
		createTableImageButton(selectedCollection);
	});
});
