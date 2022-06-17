import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { FaCrown } from 'react-icons/fa'
import { HiHome } from 'react-icons/hi'
import axios from 'axios';

import '.././css/Rank.css'

function Rank() { 
    // 데이터 가져오기
    const [select, setSelect] = useState(0);
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

    // useEffect(() => {
    //     console.log("!!!!", rank)
    // }, [rank])
        
    useEffect(() => {
        let newRank = {...rank};
        if(purchases.length !== 0) {
            if(select === 0) {
                let check = -1;
                purchases.map((item, idx) => {
                    let d = new Date(item.day);
                    if(date.getMonth() === d.getMonth() && date.getDate() === d.getDate()) {
                        rank.map((i, id) => {
                            if(+item.code === +i.code){
                                // 기존의 rank 상품이라면 rank의 값을 더하고 state에 추가 하기
                                newRank[id].cnt = +newRank[id].cnt + +item.cnt; // (판매량) 갯수 증가

                                setRank(newRank);
                            } else {
                                check = 1;
                                // 새로운 rank 상품이라면 rank state에 추가만 하기
                                console.log('상품 추가!!')

                                products.map((product, idx) => {
                                    if((+product.code === +item.code)) {

                                        let newProduct = product;
                                        newProduct.cnt = item.cnt;

                                        console.log(newProduct)
                                        newRank.push(newProduct)
                                        //rank.push(newProduct)
                                        setRank(newRank)
                                    }
                                })
                            }
                        })
                    } // check =1이면 상품 추가 여기서 하기 (정보 가지고 와서)
                })
            }
        }

        // Year int 값의 크기순으로 정렬
        // result = cars.sort(function (a, b) {
        //     return a.cnt - b.cnt;
        // });
        
    }, [purchases])

    return ( 
        <div className='Rank_Body'>
            <div className='Rank_Header between'>
                <div>
                    <p className='Rank_title'>{(select === 0) ? '오늘' : '이달'} 판매량 순위</p>
                    <p className='Rank_subTitle'>Today {date.getMonth()+1}월 {date.getDate()}일</p>
                </div>
                <Link to="/main">
                    <HiHome className='arrow'/>
                </Link>
            </div>
            <div className='Rank_Main'>
            {products.map((item, index) =>
                <div key={index} className="item">
                    <div>
                        <h1>{(index < 3)
                            ? <FaCrown className={"crown"+index}/>
                            : index+"." }
                        </h1>
                        <img className='item_img' src={item.img} alt={"product-img"}/>
                        <div>
                            <div className='item_text1'>{item.name}</div>
                            <div className='item_text2'>
                                {item.text}
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
                        <li>오늘 판매량 순위</li>
                        <li>월간 판매량 순위</li>
                    </ul>
                </div>
            </div>
        </div>

    );
}

export default Rank;