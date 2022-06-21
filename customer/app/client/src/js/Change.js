import React, { useState, useEffect } from 'react'; 
import { Link, useParams } from 'react-router-dom';
import { useLocation } from 'react-router';
import axios from 'axios';
import { Spinner } from "react-bootstrap";
import { HiArrowRight } from 'react-icons/hi'

import isLogin from '../components/isLogin.jsx'

function Change() { 
    isLogin()

    const [productId, setProductId] = useState();
    const {permuteId}= useParams();


    const location = useLocation();
    console.log(location);

    useEffect(() => {
        axios.post('/permute')
        .then(res => setProductId(res.data.code))
        .catch();
    }, []);

    useEffect(() => {
        if(productId !== undefined){
            window.location.href = permuteId+"/write/"+productId
        }
    }, [productId]);
    
    return ( 
       <div>
           { (permuteId == "refund") ? <Refund/> : <Exchange/> }
       </div>
    );
} 

function Exchange() {
    return (
        <div>
             <div className='Rank_Header between'>
                <div>
                    <p className='Rank_title'>상품 교환</p>
                    <p className='Rank_subTitle'>개봉된 상품은 교환 불가능 합니다.</p>
                </div>
                <Link to="../../permute" state={{ telephone: useLocation().state.telephone }}>
                    <HiArrowRight className='arrow'/>
                </Link>
            </div>
            <img src="../img/scan.png" alt="" style={{ width:"30vh", height:"30vh",marginTop:"5vh"}}/>
            <div style={{ fontSize:"1.7vh", marginTop:"5vh" }}>
                <p>바코드를 스캔해 주세요</p>
                <Spinner animation="grow" role="status" style={{ width:"20vh", height:"20vh", marginTop:"10vh", color:"rgb(3, 60, 89)"}}/>
                <p style={{marginTop:"12vh", color:"lightgray"}}>Loading ...</p>
            </div>
        </div>
    );
}

function Refund() {
    return (
        <div>
             <div className='Rank_Header between'>
                <div>
                    <p className='Rank_title'>상품 환불</p>
                    <p className='Rank_subTitle'>개봉된 상품은 환불 불가능 합니다.</p>
                </div>
                <Link to="../../permute" state={{ telephone: useLocation().state.telephone }}>
                    <HiArrowRight className='arrow'/>
                </Link>
            </div>
            <img src="../img/scan.png" alt="" style={{ width:"30vh", height:"30vh",marginTop:"5vh"}}/>
            <div style={{ fontSize:"1.7vh", marginTop:"5vh" }}>
                <p>바코드를 스캔해 주세요</p>
                <Spinner animation="grow" role="status" style={{ width:"10vh", height:"10vh", marginTop:"10vh", color:"lightgray"}}/>
                <p style={{marginTop:"2vh", color:"lightgray"}}>Loading ...</p>
            </div>
        </div>
    );
}

export default Change;