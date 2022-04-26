import React, { useEffect, useState }  from 'react'; 
import { Link, useParams } from 'react-router-dom';

import  './css/Product.css';

const Product = () => { 
    const { productId } = useParams();

    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch("/products")
            .then((response) => {
                return response.json();
            })
            .then(function(data) {
                setProducts(data);
            });
    }, []);

    const url = "http://image.mujikorea.net/goods/31/13/25/24/4550182146691_N_N_400.jpg";
    
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
                                        backgroundImage:`url(${text[3]})`
                                    }}></div>

                                    <div className='product_text'>
                                        <div>상품명 : {text[0]}</div>
                                        <div>상품설명 : {text[1]}</div>
                                        <div>가격 : {text[2]}</div>
                                    </div>
                                
                                    <button className='product_btn'>장바구니 담기</button>
                                </div>
                            : null
                        }
                </div>
            )}
        </div>
    ); 
}

export default Product;