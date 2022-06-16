// "use strict";

// // 모듈
// const express = require("express");
// const bodyParser = require("body-parser");
// const app = express();

// // 라우팅
// const home = require("./client/src/public/index.html");

// // 앱 세팅
// app.set("views", "./src/public/views");
// app.set("view engine", "jsx");
// app.use(express.static(`${__dirname}/src/public`));
// app.engine('jsx', require('express-react-views').createEngine())
// app.use(bodyParser.json());
// app.use(bodyParser.urlencoded({ extended: true}));


// app.use("/", home); // use -> 미들 웨어를 등록해주는 메서드.

// module.exports = app;

const express = require('express');
const path = require('path');
const cors = require('cors');
const url = require('url');   
const bodyParser = require("body-parser");
const app = express();

app.use(express.json()); 
app.use(express.urlencoded( {extended : false } ));

app.use( express.static( path.join(__dirname, 'client/build') ) );

app.get('*', function(request, response){
  response.sendFile( path.join(__dirname, 'client/build/index.html') )
});


app.get('/', function(request, response){
    response.sendFile( path.join(__dirname, 'client/build/index.html') )
});

const server = require('http').createServer(app);

app.use(cors()); // cors 미들웨어를 삽입합니다.

app.post("/login", (req, res) => {
  console.log('/login 호출됨.');

  const paramId  = req.body.user_id || req.query.user_id;
  const paramPw  = req.body.user_pw || req.query.user_pw;

  // 로그인 성공시 아래 주소로 이동
  if(paramId == 1 || paramPw == 1) { 
    console.log('로그인 성공')
    
    let data = [
    {
      id : "000012",
      display : "Galaxy S22+",
      time : "2022.5.9 17:55"
    }, {
      id : "003420",
      display : "Galaxy Z Flip3 5G",
      time : "2022.6.12 8:13"
    }, {
      id : "024150",
      display : "Galaxy Z Flip3 5G",
      time : "2022.6.12 9:13"
    }, {
      id : "007320",
      display : "Galaxy Z Flip3 5G",
      time : "2022.6.12 10:13"
    }, {
      id : "124370",
      display : "Galaxy Z Flip3 5G",
      time : "2022.6.12 11:13"
    }
  ]
  
  res.send(data)

  } else {
    console.log('로그인 실패')
  }
 



  // 실패
  // alert("입력 오류! 아이디와 비밀번호를 다시 확인해주세요.")
});

app.post("/products", (req, res) => {
  console.log('/products 호출됨.');

  let data = [
    { // 1번째 양식으로 데이터 전송 필요
      code : "0",
      name : "토종 햇 당근",
      text : "상품 설명1",
      stock : "10",
      price : "1000",
      category : "채소",
      img : "https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__340.jpg"
    }, { 
      code : "1",
      name : "야이셔 레몬",
      text : "상품 설명2",
      stock : "10",
      price : "2000",
      category : "과일",
      img : "https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830__340.jpg"
    }, { 
      code : "2",
      name : "바나나는 길어",
      text : "상품 설명3",
      stock : "0",
      price : "3000",
      category : "과일",
      img : "https://cdn.pixabay.com/photo/2016/09/03/20/48/bananas-1642706__340.jpg"
    }, { 
      code : "3",
      name : "사과는 맛있어",
      text : "상품 설명4",
      stock : "10",
      price : "4000",
      category : "과일",
      img : "https://cdn.pixabay.com/photo/2016/11/18/13/47/apple-1834639_960_720.jpg"
    }, { 
      code : "4",
      name : "외국산 당근",
      text : "상품 설명5",
      stock : "10",
      price : "1000",
      category : "채소",
      img : "https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__340.jpg"
    }, { 
      code : "5",
      name : "늙은 레몬",
      text : "상품 설명6",
      stock : "10",
      price : "2000",
      category : "과일",
      img : "https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830__340.jpg"
    }, { 
      code : "6",
      name : "바나나 6개",
      text : "상품 설명7",
      stock : "10",
      price : "3000",
      category : "과일",
      img : "https://cdn.pixabay.com/photo/2016/09/03/20/48/bananas-1642706__340.jpg"
    }
  ]

  res.send(data);
})

app.post("/products/search", (req, res) => {
  console.log('/products/search 호출됨.');

  // 상품 목록 갯수
  let data =[8]

  res.send(data);
})

// 기존 기기 연동
app.post("/connect", (req, res) => {
  console.log('/connect 호출됨.');

  
  const paramCode  = req.body.barcode || req.query.barcode;

  console.log(paramCode)
})

// 새로운 기기 추가
app.post("/connect/new", (req, res) => {
  console.log('/connect/new 호출됨.');

  
  const paramCode  = req.body.barcode || req.query.barcode;

  console.log(paramCode)
})

server.listen(5000, ()=>{
  console.log('server is running on 5000')
})