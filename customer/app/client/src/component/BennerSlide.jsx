import React, { useState } from 'react';
import { Carousel } from "react-bootstrap";

export const BennerSlide = () => {

  return (
    <Carousel slide className="banner">
      <Carousel.Item>
        <img
        className="d-block w-100"
        src="./img/benner1.png"
        alt="First slide"
        />
      </Carousel.Item>
      <Carousel.Item>
        <img
        className="d-block w-100"
        src="./img/benner2.png"
        alt="Second slide"
        />
      </Carousel.Item>
      <Carousel.Item>
        <img
        className="d-block w-100"
        src="./img/benner3.png"
        alt="First slide"
        />
      </Carousel.Item>
    </Carousel>
  )

}