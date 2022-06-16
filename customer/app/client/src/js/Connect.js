import React, { useState, useEffect } from "react";
import { Modal, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import JsBarcode from 'jsbarcode'
import axios from 'axios';

import '.././css/Connect.css';

const Connect = () => { 
    const [device, setDevice] = useState(JSON.parse(localStorage.getItem('device')));
    const [show, setShow] = useState(false);
    const [show2, setShow2] = useState(false);
    const [imageUrl, setImageUrl] = useState("");
    const [barcodeNumber, setBarcodeNumber] = useState(null);
    const [barcodeNumber2, setBarcodeNumber2] = useState(null);

    useEffect(()=>{
        if(barcodeNumber !==null) {
            const canvas = document.createElement('canvas')
            JsBarcode(canvas, barcodeNumber, { width: 3, height: 60, displayValue: true })
            setImageUrl(canvas.toDataURL('image/png'))

            setShow(true)
        }
    }, [barcodeNumber]) 

    useEffect(()=>{
        if(barcodeNumber2 !==null) {
            const canvas = document.createElement('canvas')
            JsBarcode(canvas, barcodeNumber2, { width: 3, height: 60, displayValue: true })
            setImageUrl(canvas.toDataURL('image/png'))

            setShow2(true)
        }
    }, [barcodeNumber2]) 

    const handleClose = () => setShow(false);
    const handleClose2 = () => setShow2(false);

    const checkConnect = () => {
        // 기기 연동 여부 확인
        axios.post('/connect', null, {
            params: {
                'barcode': barcodeNumber
            }
        })
        .then(res => console.log(res.data))
        .catch();

        if(false) {
            console.log('기기 연동 실패!!');
        } else {
            console.log('기기 연동 성공!!');
            window.location.href = "/main";
        }
    }

    const addConnect = () => {
        // 기기 추가 DB저장 확인
        axios.post('/connect/new', null, {
            params: {
                'barcode': barcodeNumber2
            }
        })
        .then(res => setShow2(false))
        .catch();

        console.log('기기 추가!!')

        
    }

    const newDisplay = () => {
        return  Math.floor((Math.random()*100001) + 100000);
    }

    return ( 
        <div className="main">
            <div className="connect_header">
                <h1 className="f1 bold" style={{marginTop:"0px"}}>바코드 리더 연동</h1>
                <Button className="addBtn" style={{fontSize:'1.4vh'}} onClick={() => setBarcodeNumber2(newDisplay)}>기기 추가</Button>
            </div>
            
            <div className="connect_body">
                {device.map((item, index) =>
                    <div className="device" key={index} onClick={() => setBarcodeNumber(item.id)}>
                        <img src="img/phone.png" className="phone"/>
                        <div className="dis_text">
                            <p className="text1">{item.display}</p>
                            <p className="text2">고유 번호 : {item.id}</p>
                            <p className="text2">마지막 연동 시각 : {item.time}</p>
                        </div>
                    </div> 
                )}
            </div>

            <div className="connect_footer"/>
            
            <Modal show={show} onHide={handleClose}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
            >
                <Modal.Header closeButton>
                    <Modal.Title style={{fontSize:"2vh"}}>바코드 리더 연동</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div style={{fontSize:"1.5vh", marginBottom:"7vh"}}>아래 바코드를 스캔해주세요.</div>
                    <div style={{textAlign:"center"}}>{imageUrl && <img src={imageUrl} style={{width:"30vh", height:"13vh", marginBottom:"5vh"}} />}</div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" style={{fontSize:"1.5vh"}} onClick={handleClose}>
                    Close
                    </Button>
                    <Button variant="success" style={{fontSize:"1.5vh", marginRight:"0.5vh"}} onClick={() => checkConnect()}>
                    Connect
                    </Button>
                </Modal.Footer>
            </Modal>
            <Modal show={show2} onHide={handleClose2}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
            >
            <Modal.Header closeButton>
                <Modal.Title style={{fontSize:"2vh"}}>기기 추가</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div style={{fontSize:"1.5vh", marginBottom:"7vh"}}>아래 바코드를 스캔해주세요.</div>
                <div style={{textAlign:"center"}}>{imageUrl && <img src={imageUrl} style={{width:"30vh", height:"13vh", marginBottom:"5vh"}} />}</div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" style={{fontSize:"1.5vh"}} onClick={handleClose2}>
                Close
                </Button>
                <Button variant="success" style={{fontSize:"1.5vh", marginRight:"0.5vh"}} onClick={() => addConnect()}>
                Connect
                </Button>
            </Modal.Footer>
        </Modal>
        </div> 
    ); 
}; 

export default Connect;