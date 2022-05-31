import './App.css';
import axios from 'axios';
import React,{useState,useEffect} from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Main from './Main'; 
import Products from './Products'; 
import NotFound from './NotFound'; 

function App() {

  const sendRequest = async() => {
    const response = await axios.get('http://localhost:5000');
    console.log(response);
    console.log(response.data);
  };

  useEffect(()=>{
    sendRequest();    
  });

  return (
    <div className="App" style={{margin: "0px"}}>      
      <BrowserRouter>
        {/* <Header />  */}
        <Routes> 
            <Route path='/' element={<Main />}></Route>
            <Route path='/products' element={<Products />}></Route>
            <Route path="*" element={<NotFound />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;