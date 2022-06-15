import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Carousel, Button } from "react-bootstrap";
import { HiHome } from 'react-icons/hi'
import axios from 'axios';

import { CategorySlider } from './CategorySlider.jsx'
import { ProductList } from './ProductList.jsx'

export const Products = () => {
  const [text, setText] = useState("");
  const [select, setSelect] = useState([]);
  const [products, setProducts] = useState([]); 

  useEffect(() => {
    axios.post('/products')
    .then(res => setProducts(res.data))
    .catch();

    axios.post('/products')
    .then(res => setSelect(res.data))
    .catch();
  }, [])

  useEffect(() => {
    // 카테고리 값 변경에 따른 상품 목록 표시 데이터 변경
    let newProducts = [];

    products.map((product, idx) => {
        if(text === "" || text === product.category) {
          newProducts.push(product);
        }
    })

    setSelect(newProducts);
  }, [text])

  const getText = (text) => {
    setText(text);
  } 

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
      <CategorySlider getText={getText} />
      <div className="tools">
        <div>
          <Link to="/main">
            <HiHome  style={{ fontSize:"3.6vh", color:"lightgray"}}/>
          </Link>
        </div>
        <div>
          <Link to="/products/search">
            <Button variant="secondary" style={{ fontSize:"1.7vh"}}>상품 검색</Button>
          </Link>
        </div>
      </div>
      <ProductList products={select} />
  </div>
  )
}