var express = require('express')
var path = require('path')
var cookieParser = require('cookie-parser')
var logger = require('morgan')

var indexRouter = require('./routes/index')

var app = express()

app.use(logger('dev'))
app.use(cookieParser())
app.use(express.static(path.join(__dirname, 'public')))

app.use('/', indexRouter)
// /books -> public/json/books.json
// noinspection JSCheckFunctionSignatures
app.use('/books', express.static(path.join(__dirname, 'public/json/books.json')))

module.exports = app
