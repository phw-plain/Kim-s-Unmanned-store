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
                "price": "1000",
                "stock": "10"
            }, {
                "id": "1", 
                "name": "오렌지", 
                "cnt": "1", 
                "price": "2000",
                "stock": "10"
            }, {
                "id": "2",
                "name": "오렌지", 
                "cnt": "1", 
                "price": "2000",
                "stock": "10"
            }, {
                "id": "3",
                "name": "오렌지", 
                "cnt": "1", 
                "price": "3000",
                "stock": "10"
            }, {
                "id": "4",
                "name": "오렌지", 
                "cnt": "1", 
                "price": "4000",
                "stock": "10"
            }, {
                "id": "5",
                "name": "오렌지", 
                "cnt": "1", 
                "price": "5000",
                "stock": "10"
            }
        ]
    }

    localStorage.setItem('products', JSON.stringify(products));
    console.log(JSON.parse(localStorage.getItem('products')));

    return ( 
        <div className="main">
            <h1 style={ {
                paddingTop:'80%'
            }}> 안녕하세요. 메인페이지 입니다.</h1>
            <ul> 
                <Link to="/products"><button>계산하러가기</button></Link> 
                {/* <Link to="/product/2"><li>2번상품</li></Link>  */}
            </ul>
        </div> 
    ); 
}; 

export default Main;