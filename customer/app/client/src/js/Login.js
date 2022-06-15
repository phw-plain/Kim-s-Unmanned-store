import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Login() {
  const [inputId, setInputId] = useState('')
  const [inputPw, setInputPw] = useState('')

  // 장바구니 로컬 스토리지 set
  let purchase = { 
    cnt : "2",
    price : "3000"
  }
  
  localStorage.setItem('purchase', JSON.stringify(purchase))

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

    console.log(localStorage.getItem('device')) 

    if(localStorage.getItem('device') !== null) {
      window.location.href="connect";
    } 
  }

  return(
    <div className='main'>
      <h1 className="p5 f1 bold" style={{marginTop:"0px"}}> 박리다매 무인가게 </h1>
      <form style={{ display: 'flex', flexDirection: 'column', marginLeft: '20vh', marginRight: '20vh'}}>

        <label htmlFor='input_id'>ID</label>
        <input type='text' name='input_id' value={inputId} onChange={handleInputId} />

        <label htmlFor='input_pw'>PW</label>
        <input type='password' name='input_pw' value={inputPw} onChange={handleInputPw} />
       
        <br/>
        <button type='button' onClick={()=>onClickLogin()}>Login</button>

      </form>
    </div>
  )
}

export default Login;