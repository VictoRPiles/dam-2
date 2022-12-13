const {MongoClient} = require('mongodb')
// Connection URL
const url = 'mongodb://localhost:27017'
const client = new MongoClient(url)
// Database Name
const dbName = 'contactos'

async function main() {
// Use connect method to connect to the server
	await client.connect()
	console.log('Connected successfully to server')
	const db = client.db(dbName)
	const collection = db.collection('contactos')
	return 'done.'
}

main()
	.then(console.log)
	.catch(console.error)
	.finally(() => client.close())

var express = require('express')
var path = require('path')
var cookieParser = require('cookie-parser')
var logger = require('morgan')

var indexRouter = require('./routes/index')
var app = express()

app.use(logger('dev'))
app.use(express.json())
app.use(express.urlencoded({extended: false}))
app.use(cookieParser())
app.use(express.static(path.join(__dirname, 'public')))

app.use('/', indexRouter)

module.exports = app