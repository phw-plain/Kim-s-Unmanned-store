var admin = require("firebase-admin");
var firestore = require("firebase-admin/firestore");

var serviceAccount = require("./firebasekey.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

const db = firestore.getFirestore();
let Id = "";


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

app.use(cors()); // cors 미들웨어를 삽입합니다

var today = new Date();
var year = today.getFullYear();
var month = ('0' + (today.getMonth() + 1)).slice(-2);
var day = ('0' + today.getDate()).slice(-2);

var dateString = year + '-' + month + '-' + day;
let monthString = year + '-' + month;

function newDay(){
  var today = new Date();
  var year = today.getFullYear();
  var month = ('0' + (today.getMonth() + 1)).slice(-2);
  var day = ('0' + today.getDate()).slice(-2);
  var hours = ('0' + today.getHours()).slice(-2);
  var minutes = ('0' + today.getMinutes()).slice(-2);
  var seconds = ('0' + today.getSeconds()).slice(-2);
  return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}

// 로그인 정보 받고 결과 값 보내기
app.post("/login", async (req, res) => {
  console.log('/login 호출됨.');

  Id = req.body.user_id || req.query.user_id;
  let paramPw = req.body.user_pw || req.query.user_pw;
  let Phonedata = [];
  console.log(Id, paramPw);
  console.log("??")
  const snapshot = await db.collection('Manager').where('id', '==', Id).where('pw', '==', paramPw).get();
  console.log(snapshot);
  if (!snapshot.empty) {
    const sfRef = db.collection('Manager').doc(Id).collection('barcode');
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
    // 로그인 실패 시 false 반환
    let data = {bool:false}
    res.send(data)
  }
});

app.post("/products", async (req, res) => {
  console.log('/products 호출됨.');

  const citiesRef = db.collection("Manager").doc(Id).collection("inventory");
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
      stock: doc.data().cnt,
      price: doc.data().price,
      category: doc.data().category,
      img: doc.data().picture
    });
    console.log(data);
  });
  res.send(data);
})

// 상품 목록 갯수 보내기
app.post("/products/search", async (req, res) => {
  console.log('/products/search 호출됨.');
  const citiesRef = db.collection("Manager").doc(Id).collection("inventory");
  const snapshot = await citiesRef.get();
  if (snapshot.empty) {
    console.log('No matching documents.');
    return;
  }
  let dataCount = 0;
  snapshot.forEach(doc => {
    dataCount++;
  });

  res.send([dataCount]);
})
let paramCode;


// 바코드 리더 기기 연동
app.post("/connect", async (req, res) => {
  console.log('/connect 호출됨.');
  paramCode = req.body.barcode || req.query.barcode;
  console.log(paramCode)
  let hello = {
    id: Id
  }
  await db.collection('code').doc(paramCode).set(hello);
})

//  바코드 리더 기기 연동 확인
app.post("/connect/check", async (req, res) => {
  console.log('/connect/check 호출됨.');
  console.log(Id + " " + paramCode);
  const cityRef = db.collection('Manager').doc(Id).collection('barcode').doc(paramCode);
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
  let x = db.collection('Manager').doc(Id).collection('barcode').doc(paramCode);
  const doc = await x.get();
  if (doc.data().cart != null) {
    let y = await parseInt(doc.data().cart);
    db.collection('Manager').doc(Id).collection('barcode').doc(paramCode).update({
      'cart': null
    });
    res.send({ code: y });
  } else {
    res.send(null);
  }

})

let customerTel = null;
let customerId = null;
// 상품 교환 & 환불 신청: 고객 전화번호 확인하기
app.post("/permute/tel", async (req, res) => {
  console.log(' /permute/tel 호출됨.');

  customerTel  = req.body.tel || req.query.tel;            // 전화번호
  let snapshot = await db.collection('Manager').doc(Id)
  .collection('customer').where('tel', '==', customerTel).get();
  let bool = false
  if (!snapshot.empty) {
    snapshot.forEach(doc => {
      customerId = doc.id;
    });
    bool = true;
  }
  console.log(customerId)
  let a = {bool:bool} 
  res.send(a);
})

// 상품 교환 & 환불 신청: 고객 전화번호 다시 보내기
app.post("/permute/tel/get", (req, res) => {
  console.log(' /permute/tel/get 호출됨.');
  let tel = customerTel;
  let a = {tel:tel} 
  
  res.send(a);
})


// 상품 교환 & 환불 신청: 제품 코드 보내기
app.post("/permute", async (req, res) => {
  console.log(' /permute 호출됨.');
  console.log(paramCode)
  let x = db.collection('Manager').doc(Id).collection('barcode').doc(paramCode);
  const doc = await x.get();
  let answer;
  if (doc.data().permute != null) {
    answer = parseInt(doc.data().permute);
    db.collection('Manager').doc(Id).collection('barcode').doc(paramCode).update({
      'permute': null
    });
  }
  let a = { code: answer }
  res.send(a);
})

// 상품 교환 & 환불 신청: 값 가져오기·결과 값 보내기
app.post("/permute/apply", async (req, res) => {
  console.log(' /permute/apply 호출됨.');
  let bool = false;
  const paramCode = req.body.code || req.query.code;          // 아이디
  const paramName = req.body.name || req.query.name;         // 제품명
  const paramCnt = req.body.cnt || req.query.cnt;            // 신청 수량
  const paramTel = req.body.tel || req.query.tel;            // 전화번호
  const paramRes = req.body.res || req.query.res;            // 유형
  const paramGro = req.body.gro || req.query.gro;            // 신청 이유
  const paramPermute  = req.body.permute || req.query.permute; 

  const data = {
    paramCode: 'paramCode',
    paramName: 'paramName',
    paramCnt: 'paramCnt'
  };

  console.log(paramName, paramCnt, paramTel, paramRes, paramGro, paramPermute);
  // 입력 데이터의 구매 내역이 있으면 신청 데이터 저장
  const isthere = await db.collection('Manager').doc(Id).collection('customer').doc(customerId)
  .collection('order_history').where('code','==',paramCode).get();
  var newdateString = year + "-" + month + "-" + (parseInt(day)-7);
  let getDate = newDay();
  let buyday1;
  if (isthere.empty) {
    console.log('No matching documents.');
    bool = false;
  }else{
    isthere.forEach(doc => {
      buyday1 = (doc.id);
      let buyday = (doc.id).split(' ');
      if(buyday[0]>newdateString&&doc.data().cnt == parseInt(paramCnt)){
        console.log(customerId);
        bool = true;
      }
    });
    if(bool== true){
      const data = {
        paramCode: paramCode,
        paramName: paramName,
        paramCnt: paramCnt,
        paramTel: paramTel,
        paramGro:paramGro,
        paramRes:paramRes,
        paramPermute:paramPermute,
        buyday:buyday1,
        returnday:getDate
      };
      const washingtonRef = db.collection('Manager').doc(Id).collection('customer').doc(customerId);
      await db.collection('Manager').doc(Id).collection('customer').doc(customerId)
        .collection('permute').doc(getDate).set(data);
      if(paramPermute=="1"){
        await washingtonRef.update({exchange: FieldValue.increment(1)});
      }else if(paramPermute=="2"){
        await washingtonRef.update({refund: FieldValue.increment(1)});
      }
    }
  }
  // true flase 반환  
  let a = { bool: bool }

  res.send(a);
})

// 인기순위 : 고객이 구매한 데이터 DB 값 보내기
app.post("/rank", async (req, res) => {
  console.log(' /rank 호출됨.');
  let citiesRef = db.collection("Manager").doc(Id).collection("TodayRecord").doc(dateString).collection("list");
  let snapshot = await citiesRef.get();
  let data = [];
  if (snapshot.empty) {
    console.log('No matching documents.');
    return [];
  } else {
    snapshot.forEach(doc => {
      data.push({
        code: doc.id,                           // 제품 코드
        cnt: doc.data().cnt,                    // 구매 수량        
        day: dateString
      })
    })
  }
  console.log(data)
  res.send(data);
})

// 상품 결재 시 전화번호가 일치하면 DB저장, 일치: true - 불일치: false 반환
app.post("/buy/send", async (req, res) => {

  let paramCart  = req.body.cart || req.query.cart;               // 구매목록    
  const paramTel  = req.body.tel || req.query.tel;                     // 전화번호
  const paramUsePoint  = req.body.usePoint || req.query.usePoint;   // 사용 포인트
  const paramGetPoint  = req.body.getPoint || req.query.getPoint;   // 적립 포인트

  console.log(paramCart, paramTel, paramUsePoint, paramGetPoint);
  
  // true flase 반환  
  let bool = true; 
  let login_id = null;
  let snapshot1 = await db.collection('Manager').doc(Id).collection('customer').where('tel', '==', paramTel).get();
  snapshot1.forEach(doc => {
    login_id = doc.id
  });
  
  let updatepoint = db.collection('Manager').doc(Id).collection('customer').doc(login_id);
  await updatepoint.update({point: firestore.FieldValue.increment(parseInt(paramGetPoint))})
  //인벤토리에서 물건 찾기
  for (let i = 0; i < paramCart.length; i++) {
    let x = JSON.parse(paramCart[i]);
    if (paramTel !== "-1") {
      bool = true;
      var timeString = newDay();
      let data = {
        code: x.code,
        name: x.name,
        cnt: x.cnt
      }
      await db.collection('Manager').doc(Id).collection('customer').doc(login_id).collection("order_history").doc(timeString).set(data);
      if (paramUsePoint != undefined) {
        console.log("포인트를 사용하는 회원 주문 입니다!")
        console.log('적립 예정 포인트:', paramGetPoint)
        const updatePoint = db.collection('Manager').doc(Id).collection('customer').doc(login_id);
        await updatePoint.update({
          point: FieldValue.increment(-paramUsePoint)
        })
      } else {
        console.log("포인트를 사용하는 회원 주문이 아니다!")
        console.log('적립 예정 포인트:', paramGetPoint)
      }
    } else {
      bool = true;
    }
    //일일 순위
    let snapshot2 = db.collection('Manager').doc(Id).collection('TodayRecord').doc(dateString).collection('list').doc(x.code);
    let snapshot3 =  db.collection('Manager').doc(Id).collection('inventory').doc(x.code);
    let doc = await snapshot2.get();
    if (doc.exists) {
      await snapshot2.update({
        cnt: admin.firestore.FieldValue.increment(parseInt(x.cnt))
      });
      await snapshot3.update({
        amountDay: admin.firestore.FieldValue.increment(parseInt(x.cnt))
      });
      //없으면 리스트 새로 생성 
    } else {
      bool = false;
      let hello = { cnt: parseInt(x.cnt) }
      let amountDay = { amountDay : parseInt(x.cnt) }
      await snapshot3.set(amountDay);
      await snapshot2.set(hello);
    }
    //달 순위
    let monthfire = db.collection('Manager').doc(Id).collection('MonthRecord').doc(monthString).collection('list').doc(x.code);
    doc = await monthfire.get();
    if (doc.exists) {
      await monthfire.update({
        cnt: admin.firestore.FieldValue.increment(parseInt(x.cnt))
      });
      await snapshot3.update({
        amountMonth: admin.firestore.FieldValue.increment(parseInt(x.cnt))
      });
      //없으면 리스트 새로 생성 
    } else {
      let hello = { cnt: parseInt(x.cnt) }
      let amountDay = { amountDay : parseInt(x.cnt) }
      await monthfire.set(amountDay);
      await snapshot3.set(hello);
    }
    //하루 매출
    let snapshot = db.collection('Manager').doc(Id).collection('TodayRecord').doc(dateString);
    doc = await snapshot.get();
    if (doc.exists) {
      await snapshot.update({
        sales: admin.firestore.FieldValue.increment(parseInt(x.sum))
      });
    } else {
      let hello = {
        expenses: 0,
        sales: parseInt(x.sum)
      }
      await snapshot.set(hello);
    }
    //이번 달 매출
    snapshot = db.collection('Manager').doc(Id).collection('MonthRecord').doc(monthString);
    doc = await snapshot.get();
    if (doc.exists) {
      await snapshot.update({
        sales: admin.firestore.FieldValue.increment(parseInt(x.sum))
      });
    } else {
      let hello = {
        expenses: 0,
        sales: parseInt(x.sum)
      }
      await snapshot.set(hello);
    }
    
  }
  let a = { bool: bool }
    res.send(a);
});


// 상품 결재: 회원 주문 전화번호로 포인트와 비밀번호 반환 하기
app.post("/buy/send/member", async (req, res) => {
  console.log(' /buy/send 호출됨.');

  // 전화번호에 - 없으면 자동으로 넣어 반환됨
  const paramTel = req.body.tel || req.query.tel;        // 전화번호

  console.log(paramTel);
  let snapshot = await db.collection('Manager').doc(Id).collection('customer').where('tel', '==', paramTel).get();
  let member = { pw: "", point: 0 };
  if (!snapshot.empty) {
    //로그인 확인
    snapshot.forEach(doc => {
      member.pw = doc.data().pw,
      member.point = doc.data().point
    });
  }
  let a = { member: member }
  res.send(a);
})

// 상품 결재 시 고객 회원가입 처리, 전화번호 중복 X: true - 중복 O: false 반환
app.post("/buy/join", async (req, res) => {
  console.log('/buy/join 호출됨.');

  const paramId = req.body.id || req.query.id;            // 아이디
  const paramPw = req.body.pw || req.query.pw;            // 비밀번호
  const paramName = req.body.name || req.query.name;      // 이름    
  const paramTel = req.body.tel || req.query.tel;        // 전화번호
  const paramEmail = req.body.email || req.query.email;  // 이메일

  console.log(paramName, paramTel, paramEmail);

  const data1 = {
    email: paramEmail,
    id: paramId,
    name: paramName,
    point: 0,
    pw: paramPw,
    tel: paramTel,
    refund:0,
    exchange:0
  }
  await db.collection('Manager').doc(Id).collection('customer').doc(paramId).set(data1);
  // true flase 반환  
  let bool = true;
  let a = { bool: bool }
  res.send(a);
})

// 회원가입 아이디 중복 확인
app.post("/buy/join/overlap", async (req, res) => {
  console.log('/buy/join/overlap 호출됨.');
  const Id = req.body.id || req.query.id;      // 아이디    
  console.log(Id);
  // true flase 반환  
  let bool = true;
  const cityRef = db.collection('Manager').doc(Id).collection('customer').doc(Id);
  const doc = await cityRef.get();
  if (!doc.exists) {
    bool = false
  }
  let a = { bool: bool }

  res.send(a);
})

// 회원가입 비밀번호 중복 확인
app.post("/buy/join/overlap2",async (req, res) => {
  console.log('/buy/join/overlap2 호출됨.');
  
  const paramTel = req.body.tel || req.query.tel;     
  // true flase 반환  
  let bool = true;
  const snapshot1 = await db.collection('Manager').doc(Id).collection("customer").where('tel', '==',paramTel).get();
  console.log(snapshot1);
  if (!snapshot1.empty) {
    bool = false;
  }
  let a = {bool:bool} 
  res.send(a);
})

// logout 결과 반환 
app.post("/logout", async(req, res) => {
  console.log('/logout 호출됨.');
  const paramPw = req.body.user_pw || req.query.user_pw;      // 비밀번호 확인  

  console.log(paramPw);
  Id = null;
  // true flase 반환  
  let bool = (paramPw == 1);
  let a = { bool: bool }

  res.send(a);
})
  

server.listen(3008, () => {
  console.log('server is running on 5000')
});