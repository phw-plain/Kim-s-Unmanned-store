import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { FaCrown } from 'react-icons/fa'
import { HiHome } from 'react-icons/hi'
import axios from 'axios';

import '.././css/Rank.css'

function Rank() { 
    // 데이터 가져오기
    const [products, setProducts] = useState([]);

    useEffect(() => {
        axios.post('/products')
        .then(res => setProducts(res.data))
        .catch()
    }, [])
        
    return ( 
        <div className='Rank_Body'>
            <div className='Rank_Header between'>
                <div>
                    <p className='Rank_title'>주간 판매량 순위</p>
                    <p className='Rank_subTitle'>5월 둘째 주</p>
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
                        <li>주간 판매량 순위</li>
                        <li>월간 판매량 순위</li>
                    </ul>
                </div>
            </div>
        </div>

    );
} 

export default Rank;