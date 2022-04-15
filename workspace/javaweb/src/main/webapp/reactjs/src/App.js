import React, { useState, useEffect } from 'react';
import logo from './logo.svg';
import './App.css';
import { Navbar,Nav,NavDropdown,Button,Jumbotron } from 'react-bootstrap';

function App() {

  return(
      // <div className="App">
      // <header className="App-header">
      //   <h1>Hello React!!</h1>
      // </header>
      // </div>
      <div>
        <Jumbotron class="background"></Jumbotron>
        <Container class="background"></Container>  
      </div>
  );

  function Jumbotron() {
    return (
    <div class="jumbotron">
      <h1>박리다매 무인가게</h1>
      <p>
        환영합니다.
      </p>
      <Button variant='primary'>계산하기</Button>
    </div>
    );
  }

  function Container() {
    return (
      <div className='container'>
      <div className='row'>
        <div className='col-md-4'>
          <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
          <h4>상품명</h4>
          <p>상품설명&가격</p>
        </div>
        <div className='col-md-4'>
          <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
          <h4>상품명</h4>
          <p>상품설명&가격</p>
        </div>
        <div className='col-md-4'>
          <img src="https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201812022340" width="100%"/>
          <h4>상품명</h4>
          <p>상품설명&가격</p>
        </div>
      </div>
    </div>        
    );
  }
 


}

export default App;
