import React, { useEffect, useState }  from 'react'; 
import { Link, useParams } from 'react-router-dom';
import { Button  } from "react-bootstrap";

import  './css/Product.css';

const Product = () => { 
    // 데이터 가져오기
    const { productId } = useParams();
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch("/products")
            .then((response) => {
                return response.json();
            })
            .then(function(data) {
                setProducts(data);
            });
    }, []);
        
    return ( 
        <div className="product"> 
            {products.map((item, index) =>
                <div key={index}>
                        {
                            (index == productId)
                            ? <div>
                                <div className="product_img left" style={{  
                                    backgroundImage:`url(${item.img})`
                                }}></div>
                                <div className='product_main'>
                                    <div className='product_title'>{item.name}</div>
                                    <div className='product_cate'><p>청과류</p></div>
                                    <div className='product_text'>
                                        <div>
                                            가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;격<br/>
                                            용&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;량<br/>
                                            상품설명<br/>
                                            위&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;치
                                        </div>
                                        <div className='product_text_R'>
                                            {item.text}<br/>
                                            150g<br/>
                                            {item.price}<br/>
                                            청과류 코너의 A열 4번째 칸
                                        </div>
                                    </div>
                                </div>
                            </div>
                            : null
                        }
                </div>
            )}
            <Link to={"/products"}>
                <Button variant="secondary" style={{ fontSize:"1.7vh", marginTop:"11.4vh"}}>이전으로</Button>
            </Link>
        </div>
    ); 
}

export default Product;