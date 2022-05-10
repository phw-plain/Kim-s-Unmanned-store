import React, { useEffect, useState } from 'react'; 
import { Link } from 'react-router-dom';
import { Carousel, Col, Row, Button, Nav  } from "react-bootstrap";
import $ from "jquery";

import  './css/Products.css';

const Products = () => { 
  
    const [products, setProducts] = useState([]);
    const BUTTONS = ['기본'];

    useEffect(() => {
        fetch("/products")
            .then((response) => {
                return response.json();
            })
            .then(function(data) {
                setProducts(data);
            });
    }, []);

    const handleClose = () => {
        window.location = '/';
    }

    // function moveScrollRight() {
    //     var _scrollX = $('.container').scrollLeft();
    //     $('.container').scrollLeft(_scrollX + 50);
    // };
    const moveScrollRight = () => {
        let scrollX = document.getElementById('container').scrollLeft;
        document.getElementById('container').scrollLeft = scrollX + 170;
    }

    const moveScrollLeft = () =>  {
        let scrollX = document.getElementById('container').scrollLeft;
        document.getElementById('container').scrollLeft = scrollX - 170;
    };

    function Navigation() {
        return  (
            <div class="grid">
            <div class="controller">
                <button class="controller-b" onClick={moveScrollLeft}>◀</button>
            </div> 
            <div class="category-box" id="container">
                <button className='category'>과자</button>
                <button className='category'>사탕</button>
                <button className='category'>음료</button>
                <button className='category'>행사</button>
                <button className='category'>과일</button>
                <button className='category'>채소</button>
                <button className='category'>냉장</button>
                <button className='category'>정육</button>
            </div>
            <div class="controller">
                <button class="controller-a" onClick={moveScrollRight}>▶</button>
            </div>
        </div>
        );
    }
    
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
            <Navigation/>
            <div className="tools">
                <div>
                    <Button variant='success' style={{fontSize:"1.3vh"}}>바코드 검색</Button>
                </div>
                <div>
                    <form id="form" className="search" method='get'>
                        <input type="text" id="search" name="search" placeholder='Search'/>
                        <input type="submit" value="Search"/>
                    </form>
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
            <div className='nav_close'>
                <Button variant="secondary" onClick={handleClose} style={{ fontSize:"1.5vh"}}>메인으로</Button>
            </div>
        </div>
    ); 
}

export default Products;