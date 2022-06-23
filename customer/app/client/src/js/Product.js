import React, { useEffect, useState }  from 'react'; 
import { Link, useParams, useNavigate } from 'react-router-dom';
import { Button  } from "react-bootstrap";
import axios from 'axios';

import  '.././css/Product.css';
import isLogin from '../components/isLogin.jsx'

const Product = () => {
    isLogin()

    const navigate = useNavigate();

    // 데이터 가져오기
    const { productId } = useParams();
    const [products, setProducts] = useState([]);
    
    useEffect(() => {
        axios.post('/products')
        .then(res => setProducts(res.data))
        .catch();
    }, [])
    
    function colorCheck(category) {
        if(category === "채소")
            return 'rgb(76,187,23)'
        else if(category === "과일")
            return "rgb(255,232,124)"
        else 
            return "rgb(241, 166, 175)"
    }
        
    return ( 
        <div>
            {products.map((item, index) =>
                <div className="product" style={{ 
                    backgroundColor: (colorCheck(item.category))
                }}  key={index}> 
                    {
                        (index === +productId)
                        ? <div>
                            <div className="product_img left" style={{  
                                backgroundImage:`url(${"/uploadfile/"+item.img})`
                            }}></div>
                            <div className='product_main'>
                                <div className='product_title'>{item.name}</div>
                                <div className='product_cate' style={{ 
                                        backgroundColor: (colorCheck(item.category))
                                }}>
                                    <p>{item.category}</p>
                                </div>
                                <div className='product_text'>
                                    <div>
                                        가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;격<br/>
                                        용&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;량<br/>
                                        재&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;고<br/>
                                        상품설명<br/>
                                    </div>
                                    <div className='product_text_R'>
                                        {item.price}<br/>
                                        {item.standard}<br/>
                                        {item.stock}개<br/>
                                        {item.text}<br/>
                                    </div>
                                </div>
                            </div>
                            
                            <Button variant="secondary" onClick={() => navigate(-1)} style={{ fontSize:"1.7vh", marginTop:"2.5vh", marginBottom:"15vh"}}>이전으로</Button>
                        </div>
                        : null
                    }
                </div>
            )}
        </div>
    ); 
}

export default Product;