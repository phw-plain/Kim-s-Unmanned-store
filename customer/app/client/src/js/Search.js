import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { HiArrowRight } from 'react-icons/hi'

import '.././css/Search.css';
import isLogin from '../components/isLogin.jsx'

const Search = () => { 
    isLogin()

    const [products, setProducts] = useState([]); 
    const [search, setSearch] = useState("");
    const [cnt, setCnt] = useState(0);

    useEffect(() => {
        axios.post('/products')
        .then(res => setProducts(res.data))
        .catch();

        axios.post('/products/search')
        .then(res =>setCnt(res.data[0]) )
        .catch();
    }, []) 

    const onChangeSearch = (e) => {
        let newCnt = 0;
        products.map(item => {
            if(item.name.indexOf(e.target.value.trim()) >= 0) newCnt++
        })
        
        setCnt(newCnt)

        e.preventDefault(); 
        setSearch(e.target.value);
    };

    const onSearch = (e) => {
        e.preventDefault();
    } 

    return ( 
        <div>
            <div className='Rank_Header between'>
                <div>
                    <p className='Rank_title'>{search} 상품 검색 결과</p>
                    <p className='Rank_subTitle'>총 {cnt}건의 상품을 발견하였습니다</p>
                </div>
                <Link to="/products">
                    <HiArrowRight className='arrow'/>
                </Link>
            </div>
            <form  method='get' onSubmit={e => onSearch(e)}>
                <input type="text" className="tb_search" placeholder='Search' onChange={onChangeSearch}/>
            </form>
            <div className='Rank_Main'>
                {products.map((item, index) => (item.name.indexOf(search.trim()) >= 0) ?
                    <div key={index} className="item">
                        <div>
                            <Link to={`/product/${item.code}`} >
                                <img className="item_img" src={"/uploadfile/"+item.img} alt={"product-img"}/>
                            </Link>
                            <div>
                                <div className='item_text1'>{item.name}</div>
                                <div className='item_text2'>
                                    {item.standard}
                                    <br/>
                                    {item.price}원
                                </div>
                            </div>
                        </div>
                    </div>  :  null
                )}
            </div> 
        </div> 
    ); 
}; 

export default Search;