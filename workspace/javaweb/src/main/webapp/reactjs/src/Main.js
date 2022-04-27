import React from 'react'; 
import { Link } from 'react-router-dom';
import './css/Main.css';

const Main = () => { 
    const products = {
        "products": [
            {
                "id": "0", 
                "name": "사과", 
                "cnt": "1", 
                "stock": "10",
                "price": "1000",
            }
        ]
    }

    const purchase = {
        "cnt": "1", 
        "price": "1000", 
    }

    localStorage.setItem('products', JSON.stringify(products)); // 장바구니 목록
    localStorage.setItem('purchase', JSON.stringify(purchase)); // 장바구니 물품 갯수, 가격
    // console.log(JSON.parse(localStorage.getItem('products')));

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