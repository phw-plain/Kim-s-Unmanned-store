import React from 'react'; 
import { Link } from 'react-router-dom';

const Main = () => { 
    return ( 
        <div style={ {
            backgroundImage:'url(./img/background.png)',
            backgroundSize:'cover', 
            width:'100%',
            height:'100vh'
        }}>
            <h1 style={ {
                paddingTop:'80%'
            }}> 안녕하세요. 메인페이지 입니다.</h1>
            <ul> 
                <Link to="/products"><button>계산하러가기</button></Link> 
                {/* <Link to="/product/2"><li>2번상품</li></Link>  */}
            </ul>
        </div> 
    ); 
}; 

export default Main;