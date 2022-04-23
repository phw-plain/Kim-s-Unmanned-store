import React, { useEffect, useState } from 'react'; 
import { Link } from 'react-router-dom';
import { Carousel, Col, Row} from "react-bootstrap";

import  './css/Products.css';

const Product = () => { 

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
        <div class="Products"> 
            <Carousel slide class="banner">
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
            <div class="category-box">
                <ui class="category">
                    <button class="scrollbtn"><a>◀</a></button>
                    <li>과자</li>
                    <li>캔디</li>
                    <li>음료</li>
                    <li>행사</li>
                    <button class="scrollbtn"><a>▶</a></button>
                </ui>
            </div>
            <div class="products-box">
                <Carousel fade indicators={false} interval={null}>
                    <Carousel.Item>
                        <Row>
                            {products.map((text, index) =>
                                <Col sm={5}>
                                    <Link to={`/product/${index}`}>
                                        <div class="products">
                                            <img style={{width:"22vh"}} src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" />
                                            <h4>{text[0]}</h4>
                                            <p>
                                                {text[1]}
                                                <br/>
                                                {text[2]}
                                            </p>
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

export default Product;