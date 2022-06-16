import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Form, Button } from "react-bootstrap";

function Login() {
  const [inputId, setInputId] = useState('')
  const [inputPw, setInputPw] = useState('')

  // 장바구니 로컬 스토리지 set [프론트엔드 테스트용]
  let purchase = { 
    cnt : "2",
    price : "3000"
  }
  
  localStorage.setItem('purchase', JSON.stringify(purchase))

  // [프론트엔드 테스트용]
  let cart = [
    { 
      code : "0",
      name : "토종 햇 당근",
      text : "상품 설명1",
      cnt : "10",
      price : "1000",
      sum : "0",
      stock : "10",
      img : "https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__340.jpg"
    }, { 
      code : "1",
      name : "야이셔 레몬",
      text : "상품 설명2",
      cnt : "10",
      price : "2000",
      sum : "0",
      stock : "10",
      img : "https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830__340.jpg"
    }
  ]
  localStorage.setItem('purchase', JSON.stringify(purchase))
  localStorage.setItem('cart', JSON.stringify(cart))


  localStorage.removeItem('device')

  // input data 의 변화가 있을 때마다 value 값을 변경해서 useState 해준다
  const handleInputId = (e) => {
    setInputId(e.target.value)
  }

  const handleInputPw = (e) => {
    setInputPw(e.target.value)
  }

  // login 버튼 클릭 이벤트
  const onClickLogin = async() => {

    await axios.post('/login', null, {
      params: {
      'user_id': inputId,
      'user_pw': inputPw
      }
    })
    .then(res => localStorage.setItem('device', JSON.stringify(res.data)))
    .catch();

    if(localStorage.getItem('device') !== null) {
      window.location.href="connect";
    } 
  }

  return(
    <div className='main'>
      <h1 className="p5 f1 bold"> 박리다매 무인가게 </h1>
      <Form className='loginForm'>
        <Form.Text style={{  fontSize:"2.5vh", marginBottom: "1.5vh"  }}>로그인</Form.Text>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Control type="email" placeholder="ID"  onChange={handleInputId} style={{  fontSize:"2vh" }} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Control type="password" placeholder="Password" onChange={handleInputPw} style={{  fontSize:"2vh" }}  />
        </Form.Group>
        <Button variant="primary" onClick={()=>onClickLogin()}  style={{  fontSize:"2vh" }}>Login</Button>
      </Form>
  </div>
  )
}

export default Login;