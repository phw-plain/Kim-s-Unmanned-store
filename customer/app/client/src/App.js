import './css/App.css';
import axios from 'axios';
import React,{useState,useEffect} from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Login from './js/Login'; 
import Connect from './js/Connect'; 
import NewDisplay from './js/NewDisplay'; 
import Main from './js/Main'; 
import Products from './js/Products'; 
import Search from './js/Search'; 
import Product from './js/Product'; 
import Buy from './js/Buy';
import Permute from './js/Permute';
import Change  from './js/Change';
import Rank  from './js/Rank';
import NotFound from './js/NotFound'; 

function App() {

  return (
    <div className="App" style={{margin: "0px"}}>      
      <Router>
        {/* <Header />  */}
        <Routes> 
            <Route path='/' element={<Login />}></Route>
            <Route path='/connect' element={<Connect />}></Route>
            <Route path='/connect/new' element={<NewDisplay />}></Route>
            <Route path='/main' element={<Main />}></Route>
            <Route path='/products' element={<Products />}></Route>
            <Route path='/products/search' element={<Search />}></Route>
            <Route path='/product/:productId' element={<Product />}></Route>
            <Route path='/buy' element={<Buy />}></Route>
            <Route path='/permute' element={<Permute />}></Route>
            <Route exact path='/permute/:permuteId' element={<Change />}></Route>
            <Route path='/rank' element={<Rank />}></Route>
            {/* 상단에 위치하는 라우트들의 규칙을 모두 확인, 일치하는 라우트가 없는경우 처리 */}
            <Route path="*" element={<NotFound />}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;