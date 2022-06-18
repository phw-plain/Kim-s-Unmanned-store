import React, { useEffect, useState } from 'react'; 
import { Link } from 'react-router-dom';
import { Carousel, Col, Row, Button, Nav  } from "react-bootstrap";

import  '.././css/Permute.css';

const Permute = () => { 
  
    return ( 
        <div className="main"> 
            <h1 className="p5 f1 bold"> 박리다매 무인가게 </h1>
            <div className='per_menus'>
                <Link to="../permute/exchange">
                    <div className='shadow per_menu' style={{ marginTop: "1.9vh" }}>
                        <div className='left per_title'>교환하기</div>
                        <img className='per_img' src='https://cdn-icons-png.flaticon.com/512/2250/2250295.png'></img>
                    </div>
                </Link>
                <Link to="../permute/refund">
                    <div className='shadow per_menu'>
                        <div className='left per_title'>환불하기</div>
                        <img className='per_img' src='https://cdn-icons-png.flaticon.com/512/2250/2250295.png'></img>
                    </div>
                </Link>
            </div> 
            <Link to={"/main"}>
                <Button variant="secondary" style={{ fontSize:"2vh", marginTop:"8vh"}}>메인으로</Button>
            </Link>
        </div>
    ); 
}

export default Permute;