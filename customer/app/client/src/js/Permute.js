import React, { useEffect, useState } from 'react'; 
import { Link } from 'react-router-dom';
import { Carousel, Col, Row, Button, Nav  } from "react-bootstrap";

import  '.././css/Permute.css';

const Permute = () => { 
  
    return ( 
        <div className="Permute"> 
            <div className='per_menus'>
                <Link to="refund">
                    <div className='shadow per_menu' style={{ marginTop: "18vh" }}>
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
            <Link to={"/"}>
                <Button variant="secondary" style={{ fontSize:"1.7vh", marginTop:"15vh"}}>메인으로</Button>
            </Link>
        </div>
    ); 
}

export default Permute;