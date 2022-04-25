import React, { useState } from 'react'; 
import { Link } from 'react-router-dom';
import { Modal, Button } from "react-bootstrap";

import './css/Footer.css'

function Footer(props) { 
    const cnt = 0;
    const price = 0;

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const Close = () => {
        setShow(false);
        window.location = '/';
    }

    return ( 
        <div className="footer"> 
            <div className="top">
                <div>총 내역</div>
                <div><div className="red">{ cnt }</div>개</div>
                <div><div className="red">{ price }</div>원</div>
            </div>
            <div className="center">
                <div className="list">
                    <li className="item">구매 1</li>
                    <li className="item">구매 2</li>
                    <li className="item">구매 3</li>
                    <li className="item">구매 4</li>
                    <li className="item">구매 5</li>
                </div>
            </div>
            <div className="bottom">
                <div className="left">
                    <button className="icon">▲</button>
                </div>
                <div className="right">
                    <button className="button" variant="primary" onClick={handleShow}>
                        취소하기
                    </button>

                    <Modal 
                        show={show} 
                        onHide={handleClose} 
                        size="lg" 
                        aria-labelledby="contained-modal-title-vcenter"
                        centered
                    >
                        <Modal.Header closeButton style={{borderBottom:"none"}}>
                            <Modal.Title>계산을 취소 하시겠습니까?</Modal.Title>
                        </Modal.Header>
                        <Modal.Footer style={{borderTop:"none"}}>
                            <Button variant="secondary" onClick={handleClose}>
                                취소
                            </Button>
                            <Button variant="primary" onClick={Close}>
                                확인
                            </Button>
                        </Modal.Footer>
                    </Modal>
                    <button className="button">결재하기</button>
                </div>
            </div>
        </div> 
    );
} 

export default Footer;