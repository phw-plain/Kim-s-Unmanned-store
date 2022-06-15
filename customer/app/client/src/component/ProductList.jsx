import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Carousel, Col, Row, Button, Nav  } from "react-bootstrap";
import axios from 'axios';

import { CategorySlider } from './CategorySlider.jsx'
import { Products } from '../component/Products.jsx'

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
 
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1
  };

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
      <CategorySlider/>
      <div className="tools">
        <div>
          <Link to="/main">
            <Button variant="dark" style={{ fontSize:"1.7vh"}}>메인으로</Button>
          </Link>
        </div>
        <div>
          <Link to="/products/search">
            <Button variant="dark" style={{ fontSize:"1.7vh"}}>상품 검색</Button>
          </Link>
        </div>
      </div>
      <Products/>
  </div>
  )
}