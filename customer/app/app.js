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

// 로그인 정보 받고 결과 값 보내기
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

app.post("/products", async (req, res) => {
  console.log('/products 호출됨.');

  const citiesRef = db.collection("Manager").doc(paramId).collection("inventory");
  const snapshot = await citiesRef.get();
  if (snapshot.empty) {
    console.log('No matching documents.');
    return;
  }

  let data = [];
  snapshot.forEach(doc => {
    data.push({
      code: doc.data().code,
      name: doc.data().name,
      text: doc.data().explain,
      stock: doc.data().amount,
      price: doc.data().price,
      category: doc.data().category,
      img: doc.data().picture
    });
    console.log(data);
  });


  res.send(data);
})

// 상품 목록 갯수 보내기
app.post("/products/search", (req, res) => {
  console.log('/products/search 호출됨.');

  let data = [8]

  res.send(data);
})
let paramCode;


// 바코드 리더 기기 연동
app.post("/connect", async (req, res) => {
  console.log('/connect 호출됨.');
  paramCode = req.body.barcode || req.query.barcode;
  console.log(paramCode)
  let hello = {
    id: paramId
  }
  await db.collection('code').doc(paramCode).set(hello);
})

//  바코드 리더 기기 연동 확인
app.post("/connect/check", async (req, res) => {
  console.log('/connect/check 호출됨.');
  console.log(paramId + " " + paramCode);
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

// 상품 결제 바코드 스캔 데이터 보내기
app.post("/buy", async (req, res) => {
  console.log('/buy 호출됨.');
  let x = db.collection('Manager').doc(paramId).collection('barcode').doc(paramCode);
  const doc = await x.get();
  if(doc.data().cart!=null){
    let y = await parseInt(doc.data().cart);
    console.log(y===8808244201014?true:false);
    db.collection('Manager').doc(paramId).collection('barcode').doc(paramCode).update({
      'cart': null
    });
    res.send({ code: y });
  }else{
    res.send(null);
  }
 
})

// 상품 교환 & 환불 신청 값 가져오기 · 결과 값 보내기
app.post("/permute/apply", (req, res) => {
  console.log(' /permute/apply 호출됨.');

  const paramName = req.body.name || req.query.name;     // 제품명
  const paramCnt = req.body.cnt || req.query.cnt;                 // 신청 수량
  const paramTel = req.body.tel || req.query.tel;                    // 전화번호
  const paramRes = req.body.res || req.query.res;                 // 유형
  const paramGro = req.body.gro || req.query.gro;               // 신청 이유

  console.log(paramName, paramCnt, paramTel, paramRes, paramGro);
  // 입력 데이터의 구매 내역이 있으면 신청 데이터 저장

  // true flase 반환  
  let bool = true;
  let a = { bool: bool }

  res.send(a);
})

// 인기순위 : 고객이 구매한 데이터 DB 값 보내기
app.post("/rank", (req, res) => {

})

server.listen(5000, () => {
  console.log('server is running on 5000')
});