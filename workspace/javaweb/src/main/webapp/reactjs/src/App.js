import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Header from './Header'; 
import Main from './Main'; 
import Products from './Products';
import Product from './Product';
import NotFound from './NotFound';
import { useMediaQuery } from 'react-responsive';

function App() {

  // 디바이스 제한
  const Tablet = ({ children }) => {
    const isTabletWitdh = useMediaQuery({ minWidth: 1500, maxWidth: 2550 })
    const isTabletHeight = useMediaQuery({ minHeight: 1500, maxHeight: 2550 })
    return (isTabletWitdh) ? ( (isTabletHeight) ? children : null)  : null
  }
  const ExceptionMin = ({ children }) => {
    const isExceptiontWitdh = useMediaQuery({ minWidth: 0, maxWidth: 1499 })
    const isExceptiontHeight = useMediaQuery({ minHeight: 0, maxHeight: 2550 })
    return (isExceptiontWitdh) ? ( (isExceptiontHeight) ? children : null)  : null
  }
  const ExceptionMax = ({ children }) => {
    const isExceptiontWitdh = useMediaQuery({ minWidth: 2551 })
    const isExceptiontHeight = useMediaQuery({ minHeight: 2551})
    return (isExceptiontWitdh) ? ( (isExceptiontHeight) ? children : null)  : null
  }

  return ( 
  <div className='App'>
    <Tablet>
      <BrowserRouter>
        {/* <Header />  */}
        <Routes> 
            <Route path='/' element={<Main />}></Route>
            <Route path='/products' element={<Products />}></Route>
            <Route path='/product/:productId' element={<Product />}></Route>
            
            {/* 상단에 위치하는 라우트들의 규칙을 모두 확인, 일치하는 라우트가 없는경우 처리 */}
            <Route path="*" element={<NotFound />}></Route>
        </Routes>
      </BrowserRouter>
    </Tablet>

    {/* 다른 디바이스 예외 화면 */}
    <ExceptionMin>
      <BrowserRouter>
        <h1>테블릿 환경에서 사용해 주세요.</h1>
      </BrowserRouter>
    </ExceptionMin>
    <ExceptionMax>
      <BrowserRouter>
        <h1>테블릿 환경에서 사용해 주세요.</h1>
      </BrowserRouter>
    </ExceptionMax>
  </div> 
  );
}

export default App;