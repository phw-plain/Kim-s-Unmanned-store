import React from 'react'; 
import { Link } from 'react-router-dom';
import './css/Main.css';

const Main = () => { 
    const products = {
        "products": [
        ]
    }
    const purchase = {
        "cnt": "0", 
        "price": "0", 
    }

    localStorage.setItem('products', JSON.stringify(products)); // 장바구니 목록
    localStorage.setItem('purchase', JSON.stringify(purchase)); // 장바구니 물품 

    return ( 
        <div className="main">
            <h1 style={ {
                paddingTop:'80%'
            }}> 안녕하세요. 메인페이지 입니다.</h1>
            <ul> 
                <Link to="/products"><button>계산하러가기</button></Link> 
            </ul>
        </div> 
    ); 
}; 

export default Main;