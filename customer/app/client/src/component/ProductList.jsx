import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Carousel, Col, Row, Button, Nav  } from "react-bootstrap";
import axios from 'axios';

export const ProductList = () => {
  const [products, setProducts] = useState([]); 

  useEffect(() => {
    axios.post('/products')
    .then(res => setProducts(res.data))
    .catch();
  }, [])

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
        <div className="grid">
        <div className="controller">
            <button className="controller-b" onClick={moveScrollLeft}>◀</button>
        </div> 
        <div className="category-box" id="container">
            <button className='category'>과자</button>
            <button className='category'>사탕</button>
            <button className='category'>음료</button>
            <button className='category'>행사</button>
            <button className='category'>과일</button>
            <button className='category'>채소</button>
            <button className='category'>냉장</button>
            <button className='category' style={{marginRight:"0px"}}>정육</button>
        </div>
        <div className="controller">
            <button className="controller-a" onClick={moveScrollRight}>▶</button>
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
        <Link to="/products/search">
          <Button variant="success" style={{ fontSize:"1.7vh"}}>상품 검색</Button>
        </Link>
      </div>
      <div className="products-box">
        <Carousel fade indicators={false} interval={null}  className="w-100">
          <Carousel.Item>
            <Row>
              {products.map((item, index) =>
                <Col md={6} key={index}>
                  <Link to={`/product/${index}`}>
                    <div className="products">
                      <img className="products_img" src={item.img} alt={"product-img"}/>
                      <div className="products-text">
                        <h1>{item.name}</h1>
                        <h2>
                          {item.text}
                          <br/>
                          {item.price} 원
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
        <Link to="/main">
          <Button variant="secondary" style={{ fontSize:"1.7vh"}}>메인으로</Button>
        </Link>
      </div>
  </div>
  )
}