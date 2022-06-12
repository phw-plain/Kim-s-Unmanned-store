import React, { useState, useEffect } from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

import '.././css/Connect.css';

const Login = () => { 
    const [display, setDisplay] = useState([]); 

    useEffect(() => {
        fetch("/connect")
          .then((response) => {
              return response.json();
          })
          .then(function(data) {
            setDisplay(data);
          });
      }, []);

    return ( 
        <div className="main">
            <div className="connect_header">
                <h1 className="f1 bold" style={{marginTop:"0px"}}>바코드 리더 연동</h1>
                <Link to="/connect/new">
                    <Button className="addBtn" style={{fontSize:'1.4vh'}}>기기 추가</Button>
                </Link>
            </div>
            
            <div className="connect_body">
                {display.map((item, index) =>
                    <div className="display">
                        <img src="img/phone.png" className="phone"/>
                        <div className="dis_text">
                            <p className="text1">{item.display}</p>
                            <p className="text2">고유 번호 : {item.id}</p>
                            <p className="text2">마지막 연동 시각 : {item.time}</p>
                        </div>
                    </div> 
                )}
            </div>

            <div className="connect_footer"/>
        </div> 
    ); 
}; 

export default Login;