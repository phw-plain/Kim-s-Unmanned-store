var admin = require("firebase-admin");
var firestore = require("firebase-admin/firestore");

var serviceAccount = require("./firebasekey.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

const db = firestore.getFirestore();
let paramId = "";
let Phonedata = [];

const express = require('express');
const path = require('path');
const cors = require('cors');
const url = require('url');
const bodyParser = require("body-parser");
const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use(express.static(path.join(__dirname, 'client/build')));

app.get('*', function (request, response) {
  response.sendFile(path.join(__dirname, 'client/build/index.html'))
});


app.get('/', function (request, response) {
  response.sendFile(path.join(__dirname, 'client/build/index.html'))
});

const server = require('http').createServer(app);

app.use(cors()); // cors 미들웨어를 삽입합니다.



app.post("/login", async (req, res) => {
  console.log('/login 호출됨.');

  paramId = req.body.user_id || req.query.user_id;
  let paramPw = req.body.user_pw || req.query.user_pw;

  console.log(paramId, paramPw);
  const snapshot = await db.collection('Manager').where('id', '==', paramId).where('pw', '==', paramPw).get();
  console.log(snapshot);
  if (!snapshot.empty) {
    const sfRef = db.collection('Manager').doc(paramId).collection('barcode');
    const snapshot = await sfRef.get();
    snapshot.forEach(doc => {
      console.log(doc.id, '=>', doc.data());
      Phonedata.push(doc.data())
    });
    console.log(Phonedata);
    res.send(Phonedata)
    console.log("로그인 성공")
  } else {
    console.log("로그인 실패")
  }
});

app.post("/products", (req, res) => {
  console.log('/products 호출됨.');

  let data = [
    { // 1번째 양식으로 데이터 전송 필요
      code: "0",
      name: "토종 햇 당근",
      text: "상품 설명1",
      stock: "10",
      price: "1000",
      img: "https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__340.jpg"
    }, {
      code: "1",
      name: "야이셔 레몬",
      text: "상품 설명2",
      stock: "10",
      price: "2000",
      img: "https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830__340.jpg"
    }, {
      code: "2",
      name: "바나나는 길어",
      text: "상품 설명3",
      stock: "0",
      price: "3000",
      img: "https://cdn.pixabay.com/photo/2016/09/03/20/48/bananas-1642706__340.jpg"
    }, {
      code: "3",
      name: "사과는 맛있어",
      text: "상품 설명4",
      stock: "10",
      price: "4000",
      img: "https://cdn.pixabay.com/photo/2016/11/18/13/47/apple-1834639_960_720.jpg"
    }, {
      code: "4",
      name: "외국산 당근",
      text: "상품 설명5",
      stock: "10",
      price: "1000",
      category: "채소",
      img: "https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__340.jpg"
    }, {
      code: "5",
      name: "늙은 레몬",
      text: "상품 설명6",
      stock: "10",
      price: "2000",
      category: "과일",
      img: "https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830__340.jpg"
    }, {
      code: "6",
      name: "바나나 6개",
      text: "상품 설명7",
      stock: "10",
      price: "3000",
      category: "과일",
      img: "https://cdn.pixabay.com/photo/2016/09/03/20/48/bananas-1642706__340.jpg"
    }
  ]

  res.send(data);
})

app.post("/products/search", (req, res) => {
  console.log('/products/search 호출됨.');

  // 상품 목록 갯수
  let data = [8]

  res.send(data);
})

app.post("/connect", async (req, res) => {
  console.log('/connect 호출됨.');
  const paramCode = req.body.barcode || req.query.barcode;
  console.log(paramCode)
  let hello = {
    id: paramId
  }
  let cart = {}
  const ysy = await db.collection('code').doc(paramCode).set(hello);
  const cityRef = db.collection('Manager').doc(paramId).collection('barcode').doc(paramCode);
  const doc = await cityRef.get();
  if (!doc.exists) {
    res.send(false);
    console.log('No such document!');
  } else {
    res.send(true);
    console.log('Document data:', doc.data());
  }
})


server.listen(5000, () => {
  console.log('server is running on 5000')
});