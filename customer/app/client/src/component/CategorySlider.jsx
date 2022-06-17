import React, { useState } from 'react';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

export const CategorySlider = ({ getText }) => {
  const [index, setIndex] = useState(null);
  const [isSelect, setSelect] = useState([false, false, false, false, false, false, false]);
  const [category, setCategory] = useState([ "과자", "사탕", "음료", "젤리", "냉장", "과일", "채소" ]);

  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1
  };

  const btnClick = (idx) => {
    
    if(index !== null && index !== idx) {
      // 다른 클릭된 버튼이 있음: 현재 클릭된 버튼 해제, 새로운 버튼 이벤트 적용

      let newSelect = {...isSelect};
      newSelect[index] = false;
      newSelect[idx] = true;

      setSelect(newSelect);
      setIndex(idx)
      getText(category[idx]);

    } else if(index !== null && index === idx) {
      // 자신 중복 클릭: 자신 이벤트 제거

      let newSelect = {...isSelect};
      newSelect[index] = false;
      
      setSelect(newSelect);
      setIndex(null)
      getText("");
      
    } else {
      // 중복 버튼 없음: 이벤트 적용

      let newSelect = {...isSelect};
      newSelect[idx] = true;
      
      setSelect(newSelect);
      setIndex(idx)
      getText(category[idx]);
    }
  }

  return (
    <div style={{marginTop:"3%", display:"inline-block", width:"37vh"}}>
      <Slider {...settings} >
        <div>
            <button className={isSelect[0] ? 'category_over' : 'category'} 
              value={category[0]} 
              onClick={() => {
                btnClick(0);
            }}>{category[0]}</button>
        </div>
        <div>
            <button className={isSelect[1] ? 'category_over' : 'category'} 
              value={category[1]} 
              onClick={() => {
                btnClick(1);
            }}>{category[1]}</button>
        </div>
        <div>
            <button className={isSelect[2] ? 'category_over' : 'category'} 
              value={category[2]} 
              onClick={() => {
                btnClick(2);
            }}>{category[2]}</button>
        </div>
        <div>
            <button className={isSelect[3] ? 'category_over' : 'category'} 
              value={category[3]} 
              onClick={() => {
                btnClick(3);
            }}>{category[3]}</button>
        </div>
        <div>
            <button className={isSelect[4] ? 'category_over' : 'category'} 
              value={category[4]} 
              onClick={() => {
                btnClick(4);
            }}>{category[4]}</button>
        </div>
        <div>
            <button className={isSelect[5] ? 'category_over' : 'category'} 
              value={category[5]} 
              onClick={() => {
                btnClick(5);
            }}>{category[5]}</button>
        </div><div>
            <button className={isSelect[6] ? 'category_over' : 'category'} 
              value={category[6]} 
              onClick={() => {
                
                btnClick(6);
            }}>{category[6]}</button>
        </div>
      </Slider>
    </div>
  );
}