import React, { useEffect, useState }  from 'react'; 
import { useParams } from 'react-router-dom';

import  './css/Product.css';

const Product = () => { 
    // 데이터 가져오기
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
        
    return ( 
        <div className="product"> 
            <div className='header'>
                <div className="navigation">
                    <a href='/products' style={{textDecorationLine:"none"}}>
                        <h1>박리다매 무인가게</h1>
                    </a>
                </div>
                {products.map((item, index) =>
                    <div key={index}>
                            {
                                (index == productId)
                                ? <div>
                                    <div className="product_img left" style={{  
                                        backgroundImage:`url(${item.img})`
                                    }}></div>
                                    <div className='product_text'>
                                        <div>상품명 : {item.name}</div>
                                        <div>상품설명 : {item.text}</div>
                                        <div>가격 : {item.price}</div>
                                    </div>
                                </div>
                                : null
                            }
                    </div>
                )}
            </div> 
        </div>
    ); 
}

export default Product;