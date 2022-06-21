import React, { useEffect, useState } from 'react'; 
import { Link } from 'react-router-dom';
import { useLocation } from 'react-router';
import { Row, Button, Modal, Form } from "react-bootstrap";
import axios from 'axios';

import  '.././css/Permute.css';
import isLogin from '../components/isLogin.jsx'

const Permute = () => { 
    isLogin()
    
    const [tel, setTel] = useState(useLocation().state.telephone);
    const [isMember, setIsMember] = useState();
    const [show, setShow] = useState((useLocation().state.telephone === undefined) ? true : false);

    useEffect(() => {
        if(isMember !== undefined) {
            if(isMember) {
                setShow(false);
            } else {
                alert('존재하지 않는 고객 정보입니다.')
            }
        }
    }, [isMember]);

    const handleShow = () => setShow(true);
    const handleClose = () => {
        var regExp = /^01(?:0|[6-9])-(?:\d{4}|\d{4})-\d{4}$/
        var regExp2 = /^01(?:0|[6-9])(?:\d{4}|\d{4})\d{4}$/

        if(tel === "" || !regExp.test(tel) &&  !regExp2.test(tel)){
            alert("전화번호 입력 오류! 다시 확인해주세요.",tel)
        } else {
            let newTel = tel;

            if(regExp2.test(tel)) { 
                newTel = tel.replace(/-/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3') 
                setTel(newTel)
            }
            
            axios.post('/permute/tel', null, {
                params: {
                    'tel': newTel
                }
            }).then(res =>  setIsMember(res.data.bool))
            .catch();
        }
    }
    
    const handleInputTel = (e) => {
        setTel(e.target.value)
    }

    return ( 
        <div className="main"> 
            <h1 className="p5 f1 bold"> 박리다매 무인가게 </h1>
            {
                (!show) ? 
                <div>
                    <div className='per_menus'>
                        <Link to="../permute/exchange" state={{ telephone: tel }}>
                            <div className='shadow per_menu' style={{ marginTop: "1.9vh" }}>
                                <div className='left per_title'>교환하기</div>
                                <img className='per_img' src='https://cdn-icons-png.flaticon.com/512/2250/2250295.png'></img>
                            </div>
                        </Link>
                        <Link to="../permute/refund" state={{ telephone: tel }}>
                            <div className='shadow per_menu'>
                                <div className='left per_title'>환불하기</div>
                                <img className='per_img' src='https://cdn-icons-png.flaticon.com/512/2250/2250295.png'></img>
                            </div>
                        </Link>
                    </div>
                    <Link to={"/main"}>
                        <Button variant="secondary" style={{ fontSize:"2vh", marginTop:"8vh"}}>메인으로</Button>
                    </Link>
                </div> : null
            }
            <Modal 
                show={show} 
                size="lg" 
                aria-labelledby="contained-modal-title-vcenter"
                centered className='modal_lg'
            >
                <Modal.Header style={{ borderBottom:"none",fontSize:"2vh", margin:"2vh 2vh 10vh 2vh"}}>
                    <Modal.Title  style={{fontSize:"3vh"}}>회원 정보</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group as={Row} className="mb-5">
                            <Form.Label column sm={2} className="flb">
                            전화 번호
                            </Form.Label>
                            <Form.Control type="text" className='fcb' onChange={handleInputTel} />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer style={{borderTop:"none", marginTop:"10vh"}}>
                    <Link to="/main">
                        <Button variant="secondary" style={{fontSize:"2vh", margin:"2vh 0.1vh 2vh 2vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                        취소
                        </Button>
                    </Link>
                    <Button variant="primary" onClick={() => handleClose()}style={{fontSize:"2vh", margin:"2vh 2vh 2vh 1vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                     확인
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    ); 
}

export default Permute;