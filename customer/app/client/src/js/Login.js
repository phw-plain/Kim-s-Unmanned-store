import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Login() {
  const [inputId, setInputId] = useState('')
  const [inputPw, setInputPw] = useState('')

  localStorage.removeItem('device')

  // input data 의 변화가 있을 때마다 value 값을 변경해서 useState 해준다
  const handleInputId = (e) => {
    setInputId(e.target.value)
  }

  const handleInputPw = (e) => {
    setInputPw(e.target.value)
  }

  // login 버튼 클릭 이벤트
  const onClickLogin = () => {
    axios.post('/login', null, {
      params: {
      'user_id': inputId,
      'user_pw': inputPw
      }
    })
    .then(res => localStorage.setItem('device', JSON.stringify(res.data)))
    .catch();

    window.location.href="/connect"
  }

  // 페이지 렌더링 후 가장 처음 호출되는 함수
  useEffect(() => {
    axios.get('/login')
    .then()
    .catch()
  }, [])

  return(
    <div className='main'>
      <h1 className="p5 f1 bold" style={{marginTop:"0px"}}> 박리다매 무인가게 </h1>
      <form style={{ display: 'flex', flexDirection: 'column', marginLeft: '20vh', marginRight: '20vh'}}>

        <label htmlFor='input_id'>ID</label>
        <input type='text' name='input_id' value={inputId} onChange={handleInputId} />

        <label htmlFor='input_pw'>PW</label>
        <input type='password' name='input_pw' value={inputPw} onChange={handleInputPw} />
       
        <br/>
        <button type='button' onClick={onClickLogin}>Login</button>

      </form>
    </div>
  )
}

export default Login;