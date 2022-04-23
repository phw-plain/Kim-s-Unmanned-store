import React from 'react'; 
import { Link } from 'react-router-dom';
import { Carousel } from "react-bootstrap";

import  './css/Products.css';

const Product = () => { 
    return ( 
        <div class="Products"> 
            <Carousel slide  class="banner">
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
            <div class="category-box">
                <ui class="category">
                    <button class="scrollbtn"><a>◀</a></button>
                    <li>과자</li>
                    <li>캔디</li>
                    <li>음료</li>
                    <li>행사</li>
                    <button class="scrollbtn"><a>▶</a></button>
                </ui>
            </div>
            <div class="products-box">
                <div class="products">
                    <Link to="/product/productId">
                        <button>
                            <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
                            <h4>상품명1</h4>
                            <p>상품설명&가격</p>
                        </button>
                    </Link>
                    <Link to="/product/productId">
                        <button>
                            <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
                            <h4>상품명1</h4>
                            <p>상품설명&가격</p>
                        </button>
                    </Link>
                </div>
                <div class="products">
                    <Link to="/product/productId">
                        <button>
                            <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
                            <h4>상품명1</h4>
                            <p>상품설명&가격</p>
                        </button>
                    </Link>
                    <Link to="/product/productId">
                        <button>
                            <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
                            <h4>상품명1</h4>
                            <p>상품설명&가격</p>
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    ); 
}

export default Product;