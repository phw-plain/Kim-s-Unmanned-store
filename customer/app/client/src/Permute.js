import React, { useEffect, useState } from 'react'; 
import { Link } from 'react-router-dom';
import { Carousel, Col, Row, Button, Nav  } from "react-bootstrap";

import  './css/Permute.css';

const Permute = () => { 
  
    return ( 
        <div className="Permute"> 
            <div className="navigation">
                <h1>박리다매 무인가게</h1>
            </div>
            <div className='per_menus'>
                <Link to="refund">
                    <div className='shadow per_menu' style={{ marginTop: "15vh" }}>
                        <div className='left per_title'>환불하기</div>
                        <img className='per_img' src='https://cdn-icons-png.flaticon.com/512/2250/2250295.png'></img>
                    </div>
                </Link>
                <Link to="exchange">
                    <div className='shadow per_menu'>
                        <div className='left per_title'>교환하기</div>
                        <img className='per_img' src='https://cdn-icons-png.flaticon.com/512/2250/2250295.png'></img>
                    </div>
                </Link>
            </div>
            <div className="per_bottom">
                <div className="right">
                    <button className='button'>
                        <a href="/" style={{ textDecorationLine:"none", color:"black" }}>취소하기</a>
                    </button>
                </div>
            </div>
        </div>
    ); 
}

export default Permute;