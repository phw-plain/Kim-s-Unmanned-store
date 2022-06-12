import React, { useState } from "react";

const Login = () => { 
    return ( 
        <div className="main">
            <h1 className="p5 f1 bold" style={{marginTop:"0px"}}> 박리다매 무인가게 </h1>
            <form style={{ display: 'flex', flexDirection: 'column', marginLeft: '20vh', marginRight: '20vh'}}
             action="/login" method="post">
                <label>Id</label>
                <input type="text" name="id" />
                <label>Password</label>
                <input type="password" name="password" />
                <br />
                <input type="submit" value="로그인"></input>
            </form>


        </div> 
    ); 
}; 

export default Login;