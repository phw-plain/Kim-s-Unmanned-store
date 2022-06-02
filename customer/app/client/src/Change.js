import React, { useState, useEffect } from 'react'; 
import { Link } from 'react-router-dom';
import {Button } from "react-bootstrap";

function Change() { 
    let a = window.location.href;
    let type = a.split("/")

    return ( 
       <div>
           { (type[type.length-1] == "refund") ? <Refund/> : <Exchange/> }
       </div>
    );
} 

function Refund() {
    return (
        <div>
            <h1>환불하기</h1>
            <img src="https://en.pimg.jp/050/610/791/1/50610791.jpg"/>
            <p>바코드를 스캔하여 주세요.</p>
            <p>개봉된 상품은 환불&교환이 불가능 합니다.</p>
            <Link to="../../permute">
                <Button>취소하기</Button>
            </Link>
        </div>
    );
}

function Exchange() {
    return (
        <div>
            <h1>교환하기</h1>
            <img src="https://en.pimg.jp/050/610/791/1/50610791.jpg"/>
            <p>바코드를 스캔하여 주세요.</p>
            <p>개봉된 상품은 환불&교환이 불가능 합니다.</p>
            <Link to="../../permute">
                <Button>취소하기</Button>
            </Link>
        </div>
    );

}

export default Change;