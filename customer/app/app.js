var admin = require("firebase-admin");
var firestore = require("firebase-admin/firestore");

var serviceAccount = require("./firebasekey.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

const db = firestore.getFirestore();


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

async function login(paramId, paramPw){
  const snapshot = await db.collection('Manager').where('id', '==', paramId).where('pw','==',paramPw).get();
  if(snapshot.empty){
    console.log("sfsdf")
    return false;
  }else{
    return true;
  }
}

app.post("/login", (req, res) => {
  console.log('/login 호출됨.');

  const paramId  = req.body.user_id || req.query.user_id;
  const paramPw  = req.body.user_pw || req.query.user_pw;

  console.log(paramId, paramPw);

  // 로그인 성공시 아래 주소로 이동
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
    if( login(paramId, paramPw)==false){

    res.redirect('/login')
  }else{
    res.send(data)
  }

  // 실패
  // alert("입력 오류! 아이디와 비밀번호를 다시 확인해주세요.")
});

app.post("/products", (req, res) => {
  console.log('/products 호출됨.');

  let data = [
    { 
      code : "0",
      name : "토종 햇 당근",
      text : "상품 설명1",
      cnt : "10",
      price : "1000",
      img : "https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__340.jpg"
    }, { 
      code : "1",
      name : "야이셔 레몬",
      text : "상품 설명2",
      cnt : "10",
      price : "2000",
      img : "https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830__340.jpg"
    }, { 
      code : "2",
      name : "바나나는 길어",
      text : "상품 설명3",
      cnt : "10",
      price : "3000",
      img : "https://cdn.pixabay.com/photo/2016/09/03/20/48/bananas-1642706__340.jpg"
    }, { 
      code : "3",
      name : "사과는 맛있어",
      text : "상품 설명4",
      cnt : "10",
      price : "4000",
      img : "https://cdn.pixabay.com/photo/2016/11/18/13/47/apple-1834639_960_720.jpg"
    }
  ]

  res.send(data);
})

app.post("/products/search", (req, res) => {
  console.log('/products/search 호출됨.');

  // 상품 목록 갯수
  let data =[4]

  res.send(data);
})

server.listen(5000, ()=>{
  console.log('server is running on 5000')
})