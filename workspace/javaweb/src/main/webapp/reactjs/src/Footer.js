import React, { useState, useEffect } from 'react'; 
import { Link } from 'react-router-dom';
import { Modal, Button } from "react-bootstrap";

import './css/Footer.css'

function Footer() { 
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

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const Close = () => {
        setShow(false);
        window.location = '/';
    }

    const setAll = () => {
        localStorage.setItem('purchase', JSON.stringify(purchase));
       Cart.products.map((product, idx) => {
            if(product.cnt === 0){
                Cart.products.splice(idx, idx+1);
            } 
        });
        setCarts({...Cart});
        localStorage.setItem('products', JSON.stringify(Cart));
    }

    const addItem = productId => {
        Cart.products.map( product => {
            if(productId == product.id && product.cnt < product.stock){
                product.cnt++;
                product.price = +product.price + +products[productId][3];
                purchase.cnt++;
                purchase.price = +purchase.price + +products[productId][3];
            }
        });
        setCarts({...Cart});
        setAll()
    }
    const MinusItem = productId => {
        Cart.products.map( product => {
            if(productId == product.id && product.cnt > 1){
                product.cnt--;
                product.price = +product.price - +products[productId][3];
                purchase.cnt--;
                purchase.price = +purchase.price - +products[productId][3];
            }
        });
        setCarts({...Cart});
        setAll()
    }

    return ( 
        <div className="footer"> 
            <div className="top">
                <div>총 내역</div>
                <div><div className="red">{ purchase.cnt }</div>개</div>
                <div><div className="red">{ purchase.price }</div>원</div>
            </div>
            <div className="center">
                <div className="list">
                    {Cart.products.map((text,idx)  =>
                        <li className="items">
                            <div className="item_left">
                                <div className='item_text'>{text.name}</div>
                                <div className='item_cnt'>
                                    <button className="roundBtn"  onClick={() => { MinusItem(idx) }}>-</button>
                                    {text.cnt}
                                    <button className="roundBtn"  onClick={() => { addItem(idx) }}>+</button>
                                </div>
                            </div>
                            <div className='item_price'>{text.price}원</div>
                        </li>
                    )}
                </div>
            </div>
            <div className="bottom">
                <div className="left">
                    <Link  to="/shopping">
                        <button className="icon">🛒</button>
                    </Link>
                </div>
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
    );
} 

export default Footer;