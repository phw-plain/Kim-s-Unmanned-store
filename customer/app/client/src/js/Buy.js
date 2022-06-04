import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ListGroup,  Modal, Button } from "react-bootstrap";

import '.././css/Buy.css';

const Buy = () => {
    // 데이터 가져오기
    const [products, setProducts] = useState([]);
    const [Cart, setCarts] = useState(JSON.parse(localStorage.getItem('products')));
    const [purchase, setPurchase] = useState(JSON.parse(localStorage.getItem('purchase')));

    useEffect(() => {
        fetch("/products")
            .then((response) => {
                return response.json();
            })
            .then(function(data) {
                setProducts(data);
            });
    }, []);

    
    // 목록 제거
    const handleDelete = productId => {
        Cart.products.map( product => {
            if(productId === product.id){
                purchase.cnt -= product.cnt;
                purchase.price = +purchase.price - (+products[productId][3]) * product.cnt;
                product.cnt = 0;
            }
        });
        setCarts({...Cart});
        setData();
    };

    // 구매 갯수 Up&Down
    const handleAdd = productId => {
        Cart.products.map( product => {
            if(productId === product.id && product.cnt < product.stock){
                product.cnt++;
                product.price = +product.price + +products[productId][3];
                purchase.cnt++;
                purchase.price = +purchase.price + +products[productId][3];
            }
        });
        setCarts({...Cart});
    };
    const handleMinus = productId => {
        Cart.products.map( product => {
            if(productId === product.id && product.cnt > 1){
                product.cnt--;
                product.price = +product.price - +products[productId][3];
                purchase.cnt--;
                purchase.price = +purchase.price - +products[productId][3];
            }
        });
        setCarts({...Cart});
    };

    // localstorage 데이터 저장
    function setData() {
        Cart.products.map((product, idx) => {
            if(product.cnt === 0){
                Cart.products.splice(idx, idx+1);
            } 
        });
        setCarts({...Cart});
        localStorage.setItem('products', JSON.stringify(Cart));
    }
    
    const setAll = () => {
        window.location = '/Products';
        localStorage.setItem('purchase', JSON.stringify(purchase));
        setData();
    }

    // cancle 창
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const Close = () => {
        setShow(false);
        setData();
        window.location = '/';
    }

    return ( 
        <div className='Shopping'>
            <div className="sh_center">
                <ListGroup  style={{ overflowY:"auto", height:"100%"}}>
                    {Cart.products.map((text,idx)  =>
                        <> {
                            (text.cnt !== 0)
                        ?
                        <ListGroup.Item>
                            <img className="shopimg" src={ text.img } alt="이미지 불러오기 실패" />
                                <div className='shoptext'>
                                    <p>{text.name}</p>
                                    <button
                                        onClick={() => {
                                            handleDelete(text.id);
                                        }}>
                                        X
                                    </button>
                                </div>
                                <div className='shoptext'>
                                    <div className="count">
                                        <button 
                                            onClick={() => {
                                                handleMinus(text.id);
                                            }}
                                        >-</button>
                                        <p>{text.cnt}개</p>
                                        <button 
                                            onClick={() => {
                                                handleAdd(text.id);
                                            }}
                                        >+</button>
                                    </div>
                                    <p>{text.price}원</p>
                                </div>
                        </ListGroup.Item>
                        :null
                    }</>
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
                                <Button variant="primary" onClick={Close}>
                                    확인
                                </Button>
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