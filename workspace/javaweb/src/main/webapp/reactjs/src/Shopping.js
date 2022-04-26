import React, { useEffect, useState } from 'react'; 
import { Link } from 'react-router-dom';
import { ListGroup } from "react-bootstrap";

import cartData from './data/cartData.json'
import './css/Shopping.css'

const Shopping = () => {
    const [products, setProducts] = useState(cartData);

    const handleAdd = productId => {
        const addQty = products.products.map( product => {
            if(productId == product.id && product.cnt < 10) {
                product.cnt++;
                console.log(product.cnt);
                return { product };
            } else return product;
        });
        //products.setState(addQty);
    };
    const handleMinus = productId => {
        const minusQty = products.products.map( product => {
            if(productId === product.id && product.quantity > 1) {
                return { ...product, quantity: product.quantity - 1 };
            } else return product;
        });
       setProducts(minusQty);
    };

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
                    {products.products.map((text,idx)  =>
                        <ListGroup.Item>
                            <img  className="shopimg" src="https://cdn.pixabay.com/photo/2016/11/18/13/47/apple-1834639_960_720.jpg" />
                                <div className='shoptext'>
                                    <p>{text.id}</p>
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
                     )}
                </ListGroup>
            </div>
            <div className="sh_footer">

            </div>
        </div> 
    );
} 

export default Shopping;