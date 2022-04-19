import React from 'react'; 
import  './Products.css';
import { Link } from 'react-router-dom';

const Product = () => { 
    return ( 
        <div class="frame"> 
            <div class="benner"></div>
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
            <div class="product-box">
                <div class="products inline">
                    <Link to="/product/productname">
                        <button>
                            <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
                            <h4>상품명1</h4>
                            <p>상품설명&가격</p>
                        </button>
                    </Link>
                    <Link to="/product/productname">
                        <button>
                            <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
                            <h4>상품명1</h4>
                            <p>상품설명&가격</p>
                        </button>
                    </Link>
                </div>
                <div class="products inline">
                    <Link to="/product/productname">
                        <button>
                            <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
                            <h4>상품명1</h4>
                            <p>상품설명&가격</p>
                        </button>
                    </Link>
                    <Link to="/product/productname">
                        <button>
                            <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
                            <h4>상품명1</h4>
                            <p>상품설명&가격</p>
                        </button>
                    </Link>
                </div>
            </div>
            <h3>상품 구매 페이지입니다.</h3>
        </ div>
    ); 
}

export default Product;