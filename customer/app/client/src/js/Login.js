import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Form, Button } from "react-bootstrap";

function Login() {

  const [inputId, setInputId] = useState('')
  const [inputPw, setInputPw] = useState('')
  const [data, setData] = useState();

  localStorage.removeItem('device')
  localStorage.removeItem('accessToken')

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
    .then(res =>setData(res.data))
    .catch();

  }

  useEffect(() => {
    if(data !== undefined) {
      if(data.bool === false) {
        alert('아이디 또는 비밀번호 입력오류! 다시 확인해주세요.')
      } else {
        localStorage.setItem('device', JSON.stringify(data))
        localStorage.setItem('accessToken', JSON.stringify({id:inputId, pw:inputPw}))
        window.location.href = "/connect"
      }

    }
  }, [data])

  return(
    <div className='main'>
      <h1 className="p5 maintitle"> 박리다매 무인마켓 </h1>
      <Form className='loginForm'>
        <Form.Text style={{  fontSize:"2.5vh", marginBottom: "1.5vh", color:"black" }}>로그인</Form.Text>
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