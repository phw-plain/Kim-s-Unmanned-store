import React from 'react'; 
import { Link } from 'react-router-dom';
import '.././css/Main.css';

const Main = () => { 
    // 장바구니 로컬 스토리지 set [프론트엔드 테스트용]
    let purchase = { 
        cnt : "0",
        price : "0"
    }
    
    localStorage.setItem('purchase', JSON.stringify(purchase))

    return ( 
        <div className="main">
            <h1 className="p5 f1 bold" style={{marginBottom:"1.6vh"}}> 박리다매 무인가게 </h1>
            <div>
                <Link to="/products">
                    <button className="menu m1 p3 shadow">
                        <div className="f3 left orange">01</div>
                        <div className="f2 left">상품 검색</div>
                        <img className="menu_img" src='/img/search.png'></img>
                    </button>
                </Link>
                <Link to="/buy">
                    <button className="menu m1 p3 shadow">
                        <div className="f3 left green">02</div>
                        <div className="f2 left">계산 하기</div>
                        <img className="menu_img" src='/img/buy.png'></img>
                    </button>
                </Link> 
            </div>
            <div>
                <Link to="/permute">
                    <button className="menu m1 p3 shadow">
                        <div className="f3 left green">03</div>
                        <div className="f2 left">환불&교환</div>
                        <img className="menu_img" src='/img/permute.png'></img>
                    </button>
                </Link>     
                <Link to={`/rank/${0}`}>
                    <button className="menu m1 p3 shadow">
                        <div className="f3 left orange">04</div>
                        <div className="f2 left">인기 순위</div>
                        <img className="menu_img" src='/img/rank.png'></img>
                    </button>
                </Link>    
            </div>
        </div> 
    ); 
}; 

export default Main;