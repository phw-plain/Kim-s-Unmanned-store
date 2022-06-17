import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ListGroup,  Modal, Button } from "react-bootstrap";
import axios from 'axios';

import '.././css/Buy.css';

const Buy = () => {
    // 데이터 가져오기
    // 상품 목록 데이터, 카트 데이터 : 상품 목록 데이터를 바탕으로 카트 데이터 생성
    // 카트 + 데이터 (구매 갯수, 총액)
    const [itemCode, setItemCode] = useState(0);
    const [products, setProducts] = useState([]);
    const [cart, setCarts] = useState([]);
    const [purchase, setPurchase] = useState({cnt:0, price: 0});
    
    useEffect(() => {
        axios.post('/products')
        .then(res => setProducts(res.data))
        .catch();
    }, []);

    useEffect(() => {
        let timerId = setInterval(() => {
            test();
        }, 500);
    }, [products]);

    const test = async() => {
        await axios.post('/buy')
        .then(res => cartModify(res.data.code))
        .catch();
    }

    const cartModify = (itemCode) => {
        let index = -1;
        cart.map((item, idx) => {
            if(+item.code === itemCode) {
                index = idx;
            }
        })
        if(index === -1) {
            let newCart = [...cart]
            products.map((product, idx) => {
                if((+product.code === itemCode) && products[idx].stock != 0) {
                    purchase.cnt++;
                    purchase.price = +purchase.price + +product.price;

                    let newProduct = product;
                    newProduct.cnt = "1";
                    newProduct.sum = product.price;

                    newCart.push(newProduct)
                    cart.push(newProduct)
                    setCarts(newCart)
                }
            })
        } else {
            let newCart = [...cart]
            cart.map((product, idx) => {
                if((+product.code === itemCode)  && (+product.cnt < +product.stock)) {
                    newCart[idx].cnt++;
                    newCart[idx].sum = +product.sum + +product.price;

                    purchase.cnt++;
                    purchase.price = +purchase.price + +product.price;
                    
                    setCarts(newCart)
                }
            })
        }
        setItemCode(0)
    }

    // 목록 제거
    const itemDelete = code => {
        let newCart = [...cart]
        cart.map((product, idx) => {
            if(product.code === code  && product.cnt < product.stock) {
                purchase.cnt -= product.cnt;
                purchase.price = +purchase.price - (+product.price) * product.cnt;
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
                                            itemDelete(text.code);
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
                                    <p>{text.price * text.cnt}원</p>
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
                            <Modal.Header closeButton style={{borderBottom:"none",fontSize:"1.5vh", marginBottom:"7vh"}}>
                                <Modal.Title  style={{fontSize:"2vh"}}>계산을 취소 하시겠습니까?</Modal.Title>
                            </Modal.Header>
                            <Modal.Footer style={{borderTop:"none"}}>
                                <Button variant="secondary" onClick={handleClose}  style={{fontSize:"1.5vh", marginRight:"0.5vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                취소
                                </Button>
                                <Link to="/main">
                                    <Button variant="primary" style={{fontSize:"1.5vh", marginRight:"0.5vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
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