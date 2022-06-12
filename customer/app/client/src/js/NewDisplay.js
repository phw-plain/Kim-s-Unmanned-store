import React, { useState, useEffect } from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import JsBarcode from 'jsbarcode'

import '.././css/Connect.css';

const NewDisplay = () => {

    const [imageUrl, setImageUrl] = useState("");

    let barcodeNumber = Math.floor((Math.random()*10001) + 10000);
    // 10000 ~ 20000 난수 발생

    useEffect(() => {
        const canvas = document.createElement('canvas')
        JsBarcode(canvas, barcodeNumber, { width: 3, height: 60, displayValue: false })
        setImageUrl(canvas.toDataURL('image/png'))
        console.log(barcodeNumber)
    }, [])

    return ( 
        <div className="main">
            <div className="connect_header">
                <h1 className="f1 bold" style={{marginTop:"0px"}}>바코드 리더 연동</h1>
            </div>
            
            <div className="connect_body" style={{paddingTop:"15vh"}}>
                <div>{imageUrl && <img src={imageUrl} />}</div>
            </div>

            <div className="connect_footer"> 
                <Link to="../../connect">
                    <Button style={{fontSize:'1.4vh'}}>취소하기</Button>
                </Link>
            </div>
        </div> 
    ); 
}; 

export default NewDisplay;