import React, { useEffect, useState } from 'react'; 
import { Link } from 'react-router-dom';
import { Carousel, Col, Row, Modal, Button} from "react-bootstrap";

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

    const ProductName = "비틀린 새우깡"
    const ProductText = "한정판 새우깡 160g"
    const ProductPrice = 1000
    const ProductImg = "http://image.mujikorea.net/goods/31/13/25/24/4550182146691_N_N_400.jpg"

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    //const handleShow = () => setShow(true);

    function handleShow(index) {
        console.log('.. ' + index)
        setShow(true);
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
            <div className="category-box">
                <ui className="category">
                    <button className="scrollbtn"><a>◀</a></button>
                    <li>과자</li>
                    <li>캔디</li>
                    <li>음료</li>
                    <li>행사</li>
                    <button className="scrollbtn"><a>▶</a></button>
                </ui>
            </div>
            <div className="products-box">
                <Carousel fade indicators={false} interval={null}>
                    <Carousel.Item>
                        <Row>
                            {products.map((text, index) =>
                                <Col sm={5}>
                                   <Link to={`/product/${index}`}>
                                        <div className="products">
                                            <img style={{width:"22vh"}} src={ProductImg} />
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
            {/* <Modal 
                show={show} 
                onHide={handleClose}
                size="lg" 
                aria-labelledby="contained-modal-title-vcenter"
                centered
            >
                <Modal.Header closeButton style={{borderBottom:"none"}}>
                    <Modal.Title>제품 상세 보기</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div style={{ 
                        width: "30vh",  
                        height: "30vh",  
                        backgroundSize: "contain",
                        backgroundRepeat: "no-repeat",
                        backgroundImage: `url(${ProductImg})`
                    }}/>
                    <h1>제품명 : {ProductName}</h1>
                    <h1>제픔설명 : {ProductText}</h1>
                    <h1>가격 : {ProductPrice}원</h1>

                </Modal.Body>
                <Modal.Footer style={{borderTop:"none"}}>
                    <Button variant="primary" onClick={handleClose}>
                        장바구니 담기
                    </Button>
                </Modal.Footer>
            </Modal> */}
        </div>
    ); 
}

export default Product;