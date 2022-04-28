import React, { useEffect, useState }  from 'react'; 
import { Link, useParams } from 'react-router-dom';
import { ListGroup,  Modal, Button, Alert  } from "react-bootstrap";

import  './css/Product.css';
import * as footer from './Footer.js';

const Product = () => { 
    // 데이터 가져오기
    const { productId } = useParams();
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
    const Previous = () => {
        setShow(false);
        window.location = '/products';
    }
    
    function alertShow(id){
        document.getElementById(id).style.opacity = "1";
        setTimeout(function() {
            document.getElementById(id).style.opacity = "0";
        }, 1500);
    }

    const cartAdd = () => {
        var index  = -1;
        var stock = true;
        Cart.products.map( (product, idx) => {
            if(product.id === productId){
                index =  idx;
            }
        })
        if(index !== -1) {
            if(products[productId][2] > Cart.products[index].cnt){
                Cart.products[index].cnt++;
            } else {
                stock = false;
            }
        } else {
            var data = {
                "id": "0", 
                "name": "", 
                "cnt": "0", 
                "stock": "0",
                "price": "0",
                "img":""
            }
            data.id = productId;
            data.name = products[productId][0];
            data.cnt = 1;
            data.stock = products[productId][2];
            data.price = products[productId][3] * 1;
            data.img = products[productId][4];
            Cart.products.push(data);
        }
        if(stock) {
            purchase.cnt++;
            purchase.price = +purchase.price + +products[productId][3];
            setCarts({...Cart});
            localStorage.setItem('products', JSON.stringify(Cart));
            localStorage.setItem('purchase', JSON.stringify(purchase));
            alertShow('add');
        } else {
            alertShow('danger');
        }
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
    }
    
    return ( 
        <div className="product"> 
            <div className='header'>
                <div className="navigation">
                    <button onClick={Previous}>
                        <h1>박리다매 무인가게</h1>
                    </button>
                </div>
                {products.map((text, index) =>
                    <div>
                            {
                                (index == productId)
                                ? <div>
                                    
                                        <div className="product_img left" style={{  
                                            backgroundImage:`url(${text[4]})`
                                        }}></div>

                                        <div className='product_text'>
                                            <div>상품명 : {text[0]}</div>
                                            <div>상품설명 : {text[1]}</div>
                                            <div>가격 : {text[3]}</div>
                                        </div>
                                    
                                        <button className='product_btn' onClick={cartAdd}>장바구니 담기</button>
                                    </div>
                                : null
                            }
                    </div>
                )}
                <Alert id="add" variant="success"  className="alt">
                        상품이 장바구니에 추가되었습니다!
                </Alert>
                <Alert id="danger" variant="danger"  className="alt">
                        재고가 부족합니다!!
                </Alert>
            </div>
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
        </div>
    ); 
}

export default Product;