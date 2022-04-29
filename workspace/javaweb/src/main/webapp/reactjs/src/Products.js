import React, { useEffect, useState } from 'react'; 
import { Link } from 'react-router-dom';
import { Carousel, Col, Row, Modal, Button} from "react-bootstrap";

import  './css/Products.css';

const Products = () => { 
  
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
        <div className="Products"> 
            <Carousel slide className="banner">
                <Carousel.Item>
                    <img
                    className="d-block w-100"
                    src="./img/banner1.png"
                    alt="First slide"
                    />
                </Carousel.Item>
                <Carousel.Item>
                    <img
                    className="d-block w-100"
                    src="./img/banner1.png"
                    alt="Second slide"
                    />
                </Carousel.Item>
            </Carousel>
            <div className="category-box">
                <div className="category">
                    <button className="scrollbtn">◀</button>
                    <div>과자</div>
                    <div>캔디</div>
                    <div>음료</div>
                    <div>행사</div>
                    <button className="scrollbtn">▶</button>
                </div>
            </div>
            <div className="products-box">
                <Carousel fade indicators={false} interval={null}  className="w-100">
                    <Carousel.Item>
                        <Row>
                            {products.map((text, index) =>
                                <Col md={6}>
                                   <Link to={`/product/${index}`}>
                                        <div className="products">
                                            <img className={"products_img"} src={text[4]} alt={"product-img"}/>
                                            <div className="products-text">
                                                <h1>{text[0]}</h1>
                                                <h2>
                                                    {text[1]}
                                                    <br/>
                                                    {text[3]}
                                                </h2>
                                            </div>
                                        </div>
                                    </Link> 
                                </Col>  
                            )}
                        </Row>
                    </Carousel.Item>
                </Carousel>
            </div>
        </div>
    ); 
}

export default Products;