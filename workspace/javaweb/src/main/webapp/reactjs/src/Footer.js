import React from 'react'; 
import { Link } from 'react-router-dom';
import './css/Footer.css'

function Footer(props) { 
    const cnt = 0;
    const price = 0;
    return ( 
        <div class="footer"> 
            <div class="top">
                <div>총 내역</div>
                <div><div class="red">{ cnt }</div>개</div>
                <div><div class="red">{ price }</div>원</div>
            </div>
            <div class="center">
                <div class="list">
                    <li class="item">구매 1</li>
                    <li class="item">구매 2</li>
                    <li class="item">구매 3</li>
                    <li class="item">구매 4</li>
                    <li class="item">구매 5</li>
                </div>
            </div>
            <div class="bottom">
                <div class="left">
                    <button class="icon">▲</button>
                </div>
                <div class="right">
                    <Link to="/">
                        <button class="button">취소하기</button>
                    </Link>
                    <button class="button">결재하기</button>
                </div>
            </div>
        </div> 
    );
} 

export default Footer;