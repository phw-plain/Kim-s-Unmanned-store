"use strict";

// 모듈
const express = require("express");
const bodyParser = require("body-parser");
const app = express();

// 라우팅
const home = require("./src/routes/home");

// 앱 세팅
app.set("views", "./src/public/views");
app.set("view engine", "jsx");
app.use(express.static(`${__dirname}/src/public`));
app.engine('jsx', require('express-react-views').createEngine())
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true}));


app.use("/", home); // use -> 미들 웨어를 등록해주는 메서드.

module.exports = app;
