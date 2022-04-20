import React from 'react'; 
import { useParams } from 'react-router-dom';

import  './css/Product.css';

const Product = () => { 
    const { productId } = useParams();
    const url = "http://image.mujikorea.net/goods/31/13/25/24/4550182146691_N_N_400.jpg";
    
    return ( 
        <div class="product"> 
            <div class="itemimg" style={{  
                backgroundImage:`url(${url})`
                
            }}></div>
            <h3>{productId} 상품 페이지입니다.</h3>
        </div>
    ); 
}

export default Product;