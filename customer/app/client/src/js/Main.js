import React, { useState, useEffect } from 'react'; 
import axios from 'axios';
import { Link } from 'react-router-dom';
import { IoIosPower } from 'react-icons/io'

import '.././css/Main.css';

const Main = () => { 
    const [isTrue, setIsTrue] = useState(0);

    useEffect(()=>{
        if(isTrue !== 0) {
            if(isTrue) {
                alert('로그아웃 성공! 좋은 하루 되세요.')
                window.location.href = "/"
            }
        }
    }, [isTrue]) 

    const logout = async() => {
        let pw = prompt('관리자 확인. 비밀번호를 입력해주세요.', '')

        await axios.post('/logout', null, {
            params: {
            'user_pw': pw
            }
        })
        .then(res => setIsTrue(res.data.bool))
        .catch();
    }

    return ( 
        <div className="main">
            <h1 className="p5 maintitle"> 박리다매 무인가게 </h1>
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
                <Link to="/permute" state={{ telephone: undefined}}>
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
            <IoIosPower onClick={() => logout()} style={{fontSize:"6vh", float: "right", margin:"9vh 3vh 5vh 0vh"}} />
        </div> 
    ); 
}; 

export default Main;