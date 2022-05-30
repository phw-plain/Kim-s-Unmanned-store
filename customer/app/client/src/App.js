import './App.css';
import axios from 'axios';
import React,{useState,useEffect} from 'react';

import Main from './Main'; 

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
    <div className="App">      
      <Main/>
    </div>
  );
}

export default App;