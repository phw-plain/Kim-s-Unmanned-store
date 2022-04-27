import React, { useEffect, useState }  from 'react'; 
import { Link, useParams } from 'react-router-dom';
import { ListGroup,  Modal, Button, Alert  } from "react-bootstrap";

import  './css/Product.css';

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

    const cartAdd = () => {
        var index  = -1;
        Cart.products.map( (product, idx) => {
            if(product.id === productId){
                index =  idx;
            }
        })

        if(index !== -1) {
            Cart.products[index].cnt++;
        } else {
            var data = {
                "id": "0", 
                "name": "", 
                "cnt": "0", 
                "stock": "0",
                "price": "0"
            }
            data.id = productId;
            data.name = products[productId][0];
            data.cnt = 1;
            data.stock = products[productId][2]
            data.price = products[productId][3] * 1
            Cart.products.push(data);
        }
        setCarts({...Cart});
        localStorage.setItem('products', JSON.stringify(Cart));
    }
    
    return ( 
        <div className="product"> 
            <div className="navigation">
                <Link to="/products">
                    <h1>박리다매 무인가게</h1>
                </Link>
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
                <Alert  variant="success" style={ { margin: "5%", fontSize:"2vh" }}>
                        상품이 장바구니에 추가되었습니다!
                </Alert>
        </div>
    ); 
}

export default Product;