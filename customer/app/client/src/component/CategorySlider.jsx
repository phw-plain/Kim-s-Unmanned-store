import React, { useEffect, useState } from 'react';
import Slider from "react-slick";
import axios from 'axios';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

export const CategorySlider = () => {
  const [products, setProducts] = useState([]); 

  useEffect(() => {
    axios.post('/products')
    .then(res => setProducts(res.data))
    .catch();
  }, [])

  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1
  };

  return (
    <div style={{marginTop:"3%", display:"inline-block", width:"37vh"}}>
      <Slider {...settings} >
        <div>
            <button className='category'>과자</button>
        </div>
        <div>
            <button className='category'>사탕</button>
        </div>
        <div>
            <button className='category'>음료</button>
        </div>
        <div>
            <button className='category'>행사</button>
        </div>
        <div>
            <button className='category'>음료</button>
        </div>
        <div>
            <button className='category'>과일</button>
        </div>
        <div>
            <button className='category'>채소</button>
        </div>
        <div>
            <button className='category'>냉장</button>
        </div>
      </Slider>
    </div>
  );
}