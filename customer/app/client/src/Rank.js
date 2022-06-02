import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { FaCrown } from 'react-icons/fa'

import './css/Rank.css'

function Rank() { 
    // 데이터 가져오기
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch("/rank")
            .then((response) => {
                return response.json();
            })
            .then(function(data) {
                setProducts(data);
            });
    }, []);
        
    return ( 
        <div>
            <div className='Rank_Header'>
                <p className='Rank_title'>주간 판매량 순위</p>
                <p className='Rank_subTitle'>5월 둘째 주</p>
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
                        <li>주간 판매량 순위</li>
                        <li>월간 판매량 순위</li>
                        <Link to='/'><li>홈으로 가기</li></Link>
                    </ul>
                </div>
            </div>
        </div>
    );
} 

export default Rank;