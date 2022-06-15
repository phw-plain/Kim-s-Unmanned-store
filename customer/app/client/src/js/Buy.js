import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ListGroup,  Modal, Button } from "react-bootstrap";
import axios from 'axios';

import '.././css/Buy.css';

const Buy = () => {
    // 데이터 가져오기
    // 상품 목록 데이터, 카트 데이터 : 상품 목록 데이터를 바탕으로 카트 데이터 생성
    // 카트 + 데이터 (구매 갯수, 총액)
    const [cart, setCarts] = useState([]);
    const [purchase, setPurchase] = useState(JSON.parse(localStorage.getItem('purchase')));

    useEffect(() => {
        let data = [
            { 
                code : "0",
                name : "토종 햇 당근",
                text : "상품 설명1",
                cnt : "1",
                price : "1000",
                sum : "0",
                stock : "10",
                img : "https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__340.jpg"
            }, { 
                code : "1",
                name : "야이셔 레몬",
                text : "상품 설명2",
                cnt : "1",
                price : "2000",
                sum : "0",
                stock : "10",
                img : "https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830__340.jpg"
            }
        ];
        setCarts(data);
    }, []);
    
    // 목록 제거
    const handleDelete = code => {
        let newCart = [...cart]
        cart.map((product, idx) => {
            if(product.code === code  && product.cnt < product.stock) {
                purchase.cnt -= product.cnt;
                purchase.price = +purchase.price - (+product.price) * product.cnt;
                console.log(idx)
                newCart.splice(idx, 1)
            }
        })
        setCarts(newCart);
    };

    // 구매 갯수 Up&Down
    const handleAdd = code => {
        let newCart = [...cart]
        cart.map((product, idx) => {
            if(product.code === code  && product.cnt < product.stock) {
                newCart[idx].cnt++;
                newCart[idx].sum = +product.sum + +product.price;
                purchase.cnt++;
                purchase.price = +purchase.price + +product.price;
            }
        })
       setCarts(newCart);
    };
    const handleMinus = code => {
        let newCart = [...cart]
        cart.map((product, idx) => {
            if(product.code === code  && product.cnt > 1) {
                newCart[idx].cnt--;
                newCart[idx].sum = +product.sum - +product.price;
                purchase.cnt--;
                purchase.price = +purchase.price - +product.price;
            }
        })
        setCarts(newCart)
    };

    // cancle 창
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return ( 
        <div className='Shopping'>
            <div className="sh_center">
                <ListGroup  style={{ overflowY:"auto", height:"100%"}}>
                    {cart.map((text,idx)  =>
                        <ListGroup.Item  key={idx} className="cart_item">
                            <img className="shopimg" src={ text.img } alt="이미지 불러오기 실패" />
                                <div className='shoptext'>
                                    <p className='item_name'>{text.name}</p>
                                    <button className="cancelBtn"
                                        onClick={() => {
                                            handleDelete(text.code);
                                        }}>
                                        X
                                    </button>
                                </div>
                                <div className='shoptext'>
                                    <div className="count_buy">
                                        <button className="cntBtn"
                                            onClick={() => {
                                                handleMinus(text.code);
                                            }}
                                        >-</button>
                                        <p className='item_cnt'>{text.cnt}개</p>
                                        <button className="cntBtn"
                                            onClick={() => {
                                                handleAdd(text.code);
                                            }}
                                        >+</button>
                                    </div>
                                    <p>{text.price}원</p>
                                </div>
                        </ListGroup.Item>
                    )}
                </ListGroup>
            </div>
            <div className="sh_footer">
                <div className='footer_text'>
                    <div className='inline'>
                        <div className='rm'>총 갯수</div>
                        <div><div className="red">{ purchase.cnt }</div>개</div>
                    </div>
                    <div className='inline'>
                        <div className='rm'>총 결재금액</div>
                        <div><div className="red">{  purchase.price }</div>원</div>
                    </div>
                </div>
                <div className='footer_btns'>
                    <div className="right">
                        <button className='button' onClick={handleShow} >
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
                                <Link to="/main">
                                    <Button variant="primary">
                                        확인
                                    </Button>
                                </Link>
                            </Modal.Footer>
                        </Modal>
                        <button className='button'>결재하기</button>
                    </div>
                </div>
            </div>
        </div> 
    );
}


export default Buy;