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
            <div className="product_img left" style={{  
                backgroundImage:`url(${url})`
            }}></div>
            <div className=" right">
                {products.map((text, index) =>
                    <div>
                        {
                            (index == productId)
                            ? <div>
                                <h2>{text[0]}</h2>
                                <h2>{text[1]}</h2>
                                <h2>{text[2]}</h2>
                            </div>
                            : null
                        }
                    </div>
                )}
            </div>
            <div>
                    <button>장바구니 담기</button>
            </div>
         
        </div>
    ); 
}

export default Product;