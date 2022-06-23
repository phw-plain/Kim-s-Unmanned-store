import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { FaCrown } from 'react-icons/fa'
import { HiHome } from 'react-icons/hi'
import axios from 'axios';

import '.././css/Rank.css'
import isLogin from '../components/isLogin.jsx'

function Rank() { 
  isLogin()
  
    // 데이터 가져오기
    const { select } = useParams();
    const [products, setProducts] = useState([]);
    const [purchases, setPurchases] = useState([]);
    const [rank, setRank] = useState([]);
    const [date, setDate] = useState(new Date());

    useEffect(() => {
        axios.post('/products')
        .then(res => setProducts(res.data))
        .catch()
    }, [])

    useEffect(() => {
        axios.post('/rank')
        .then(res => setPurchases(res.data))
        .catch()
    }, [])

    useEffect(() => {
        let newRank = (rank.length !== 0 ) ? {...rank} : [];

        if(purchases.length !== 0) {
            if(+select === 0) {
                let check = -2;
                purchases.map((item, idx) => {
                    if(item.day.length > 7){
                        let d = new Date(item.day);
                        if(date.getMonth() === d.getMonth() && date.getDate() === d.getDate()) {
                            newRank.map((i, id) => {
                                if(+item.code === +i.code){
                                    // 기존의 rank 상품이라면 rank의 값을 더하고 state에 추가 하기
                                    check = id;
                                }
                            })
                            if(check < 0) check = -1;
                        }

                        if(check >= 0) {
                            newRank[check].cnt = +newRank[check].cnt + +item.cnt; // (판매량) 갯수 증가

                            setRank(newRank);
                        } else if(check === -1) {
                            // 새로운 rank 상품이라면 rank state에 추가만 하기
                            products.map((product, idx) => {
                                if((+product.code === +item.code)) {

                                    let newProduct = product;
                                    newProduct.cnt = +item.cnt;

                                    newRank.push(newProduct)
                                    setRank(newRank)
                                }
                            })
                        }
                        check = -2
                    }
                })
            } else {
                let check = -2;
                purchases.map((item, idx) => {
                    let d = new Date(item.day);
                    if(date.getMonth() === d.getMonth()) {
                        newRank.map((i, id) => {
                            if(+item.code === +i.code){
                                // 기존의 rank 상품이라면 rank의 값을 더하고 state에 추가 하기
                                check = id;
                            }
                        })
                        if(check < 0) check = -1;
                    }

                    if(check >= 0) {
                        newRank[check].cnt = +newRank[check].cnt + +item.cnt; // (판매량) 갯수 증가

                        setRank(newRank);
                    } else if(check === -1) {
                        // 새로운 rank 상품이라면 rank state에 추가만 하기
                        products.map((product, idx) => {
                            if((+product.code === +item.code)) {

                                let newProduct = product;
                                newProduct.cnt = +item.cnt;

                                newRank.push(newProduct)
                                setRank(newRank)
                            }
                        })
                    }
                    check = -2
                })
            }
        }      
    }, [purchases])

    const handleToday = () => {
        window.location.href = "../rank/0"
    }

    const handleMonth = () => {
        window.location.href = "../rank/1"
    }

    return ( 
        <div className='Rank_Body'>
            <div className='Rank_Header between'>
                <div>
                    <p className='Rank_title'>{(+select === 0) ? '오늘' : '이달'} 판매량 순위</p>
                    <p className='Rank_subTitle'>Today {date.getMonth()+1}월 {date.getDate()}일</p>
                </div>
                <Link to="/main">
                    <HiHome className='arrow'/>
                </Link>
            </div>
            <div className='Rank_Main'>
            {(rank.length === 0 ) ? <h1>상품 집계중입니다!!</h1> 
                : rank.sort(function (a, b) {
                    return  b.cnt - a.cnt;
                }).map((item, index) =>
                <div key={index} className="item">
                    <div>
                        <h1>{(index < 3)
                            ? <FaCrown className={"crown"+index}/>
                            : (index+1)+"." }
                        </h1>
                        <Link to={`/product/${item.code}`} >
                            <img className="item_img" src={"/uploadfile/"+item.img} alt={"product-img"}/>
                        </Link>
                        <div>
                            <div className='item_text1'>{item.name}</div>
                            <div className='item_text2'>
                                {item.standard}
                                <br/>
                                {item.price}
                            </div>
                        </div>
                    </div>
                </div>  
            )}
            </div>
            <div className='Rank_tap'>
                <div className='tapCtrl'>◀</div>
                <div className='tapMain'>
                    <ul>
                        <div style={{ marginTop: "15%" }} onClick={handleToday} >
                            <li>오늘 판매량 순위</li>
                        </div>
                        <div style={{ marginTop: "5%" }} onClick={handleMonth}>
                            <li>월간 판매량 순위</li>
                        </div>
                    </ul>
                </div>
            </div>
        </div>

    );
}

export default Rank;