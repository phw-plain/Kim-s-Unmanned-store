import React, { useState, useEffect } from 'react'; 
import { Link } from 'react-router-dom';
import { Button, Spinner, Form, Col, Row } from "react-bootstrap";
import { HiArrowRight } from 'react-icons/hi'
import axios from 'axios';

function Change() { 
    let a = window.location.href;
    let type = a.split("/")
   

    const [products, setProducts] = useState([]); 
    const [imgUrl, setImgUrl] = useState("");
    const [change, setChange] = useState("");

    useEffect(() => {
      axios.post('/products')
      .then(res => setProducts(res.data))
      .catch();

      if(type[type.length-2] === "refund") {
        setChange("환불")
      } else {
        setChange("교환")
      }
    }, [])

    useEffect(() => {
        if(products.length > 0)
            setImgUrl(products[0].img)
    }, [products])
    
    
    return ( 
        <div>
        <div className='Rank_Header between'>
           <div>
               <p className='Rank_title'>상품 {change}</p>
               <p className='Rank_subTitle'>개봉된 상품은 {change} 불가능 합니다.</p>
           </div>
           <Link to="../../permute">
               <HiArrowRight className='arrow'/>
           </Link>
       </div>
       <div style={{ margin: "5vh 10vh 5vh 10vh", fontSize: "2vh" }}>
            <img className='permute_img' src={imgUrl} alt="이미지 불러오기 실패"/>
           <Form style={{ textAlign:"left" }}>
               <Form.Group as={Row} className="mb-5 fg" controlId="formHorizontalEmail">
                   <Form.Label column sm={2} style={{width:"10vh"}}>
                   제품명
                   </Form.Label>
                   <Form.Control type="text" className='fc' />
               </Form.Group>
               <Form.Group as={Row} className="mb-5 fg" controlId="formHorizontalEmail">
                   <Form.Label column sm={2} style={{width:"10vh"}}>
                   전화 번호
                   </Form.Label>
                   <Form.Control type="text" className='fc' />
               </Form.Group>
               <Form.Group as={Row} className="mb-5 fg" controlId="formHorizontalPassword">
                   <Form.Label column sm={2} style={{width:"10vh"}}>
                   {change} 사유
                   </Form.Label>
                   <Form.Select size="sm" className='fc'>
                       <option>상품 결함</option>
                       <option>단순 변심</option>
                   </Form.Select>
               </Form.Group>
               <Form.Group as={Row} className="mb-5 fg" controlId="formHorizontalPassword">
                   <Form.Label column sm={2} style={{width:"10vh"}}/>
                   <Form.Control as="textarea" rows={3} className="fc" />
               </Form.Group>
               <Form.Group as={Row}  className="mt-10 center">
                   <Button style={{ fontSize: "2vh", width:"10vh" }}>{change} 신청</Button>
               </Form.Group>
           </Form>
       </div>
   </div>
    );
} 

export default Change;