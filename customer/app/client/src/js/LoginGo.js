import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from "react-bootstrap";

import { BsPersonXFill } from 'react-icons/bs'


function isLogin() {

  return(
    <div className='main'>
      <h1 className="p5 maintitle"> 박리다매 무인가게 </h1>
      <div className='board'>
        <h1 className='maintitle' style={{ marginTop:"5vh" }}>해당 서비스는 로그인 후<br/> 
이용하실 수 있습니다.</h1>
        <BsPersonXFill style={{ fontSize:"20vh", marginTop:"4vh" }}/><br/>
        <Link to="/">
          <Button variant='secondary' style={{ fontSize: "2.5vh", marginTop:"5vh", paddingLeft:"1.5vh", paddingRight:"1.5vh" }}>로그인</Button>
        </Link>
      </div>
      
    </div>
  )
}

export default isLogin;