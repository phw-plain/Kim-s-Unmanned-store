import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ListGroup } from "react-bootstrap";

import './css/Shopping.css';

const Shopping = () => {
    const [products, setProducts] = useState(JSON.parse(localStorage.getItem('products')));

    useEffect(() => {
        fetch("/products")
            .then((response) => {
                return response.json();
            })
            .then(function(data) {
                setProducts(data);
            });
    }, []);

    const [Cart, setCarts] = useState(JSON.parse(localStorage.getItem('products')));
    
    // 목록 제거
    const handleDelete = productId => {
        Cart.products.map( product => {
            if(productId == product.id){
                product.cnt = 0;
            }
        });
        setProducts({...Cart});
        setData();
    };

    // 구매 갯수 Up&Down
    const handleAdd = productId => {
        Cart.products.map( product => {
            if(productId == product.id && product.cnt < product.stock){
                product.cnt++;
                product.price*=2;
            }
        });
        setProducts({...Cart});
    };
    const handleMinus = productId => {
        Cart.products.map( product => {
            if(productId == product.id && product.cnt > 1){
                product.cnt--;
                product.price-=product.price;
            }
        });
        setProducts({...Cart});
    };

    // localstorage 데이터 저장
    function setData() {
        Cart.products.map((product, idx) => {
            if(product.cnt == 0){
                console.log(idx)
                Cart.products.splice(idx, idx+1);
            } 
        });
        setProducts({...Cart});
        localStorage.setItem('products', JSON.stringify(Cart));
        console.log(JSON.parse(localStorage.getItem('products')));
    }

    return ( 
        <div className='Shopping'>
            <div className="navigation">
                <Link to="/products">
                    <h1>박리다매 무인가게</h1>
                </Link>
            </div>
            <div className="sh_center">
                <h1>장바구니 물품</h1>
                <ListGroup  style={{ overflowY:"auto", height:"100%"}}>
                    {Cart.products.map((text,idx)  =>
                        <>{
                            
                        (text.cnt != 0)
                        ?
                        <ListGroup.Item>
                            <img className="shopimg" src="https://cdn.pixabay.com/photo/2016/11/18/13/47/apple-1834639_960_720.jpg" />
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

            </div>
        </div> 
    );
}


export default Shopping;