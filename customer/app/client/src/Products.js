import React, { useEffect, useState } from 'react'; 
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Carousel, Col, Row, Button, Nav  } from "react-bootstrap";

import  './css/Products.css';

const Products = () => { 
   const [hotelName, setHotelName] = useState(null);
  
  const handleSearch = (e) => {
    setHotelName(e.value);
    
    fetchHotelName();
  };

  const fetchHotelName = async() => {
    const response = await axios('/products');
    console.log(response.data);
    // data속에 담겨져 나온다.
  };
    
    return ( 
        <div className="Products"> 
            <button onClick={ ()=>{ handleSearch() }}>보내기</button>
        </div>
    ); 
}

export default Products;