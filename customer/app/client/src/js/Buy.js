import React, { useEffect, useState, useRef } from 'react';
import { Link } from 'react-router-dom';
import { ListGroup,  Modal, Button, Form, Row } from "react-bootstrap";
import axios from 'axios';

import '.././css/Buy.css';

const Buy = () => {
    // 데이터 가져오기
    // 상품 목록 데이터, 카트 데이터 : 상품 목록 데이터를 바탕으로 카트 데이터 생성
    // 카트 + 데이터 (구매 갯수, 총액)
    const [itemCode, setItemCode] = useState(0);
    const [products, setProducts] = useState([]);
    const [cart, setCarts] = useState([]);
    const [purchase, setPurchase] = useState({cnt:0, price: 0});
    const [tel, setTel] = useState("");
    const [isStop, setIsStop] = useState(false)
    const [join, setJoin] = useState({name:"", tel:"", email:""});
    const [apply, setApply] = useState();
    const [joinApply, setJoinApply] = useState();

    useEffect(() => {
        axios.post('/products')
        .then(res => setProducts(res.data))
        .catch();
    }, []);

    let loop = useInterval(() => {
        if(!isStop) {
            cartAdd();
        }
    }, 500);

    function useInterval(callback, delay) {
        const savedCallback = useRef();

        // Remember the latest callback.
        useEffect(() => {
            savedCallback.current = callback;
        }, [callback]);
        
        // Set up the interval.
        useEffect(() => {
            function tick() {
                savedCallback.current();
            }
            if (delay !== null) {
                let id = setInterval(tick, delay);
                return () => clearInterval(id);
            }
        }, [delay]);
    }

    useEffect(() => {
        if(apply !== undefined){
            if(apply) {
                alert("계산 완료!")
                window.location.href = "/main";
            } else {
                alert("전화번호 입력 오류!")
            }
        }
    }, [apply])

    useEffect(() => {
        if(joinApply !== undefined){
            if(joinApply) {
                alert("회원가입 성공!")
                handleClose3()
            } else {
                alert("회원가입 실패 이전에 가입한 기록이 있습니다!")
            }
        }
    }, [joinApply])

    const cartAdd = async() => {
        await axios.post('/buy')
        .then(res => cartModify(res.data.code))
        .catch();
    }

    const cartModify = (itemCode) => {
        let index = -1;
        cart.map((item, idx) => {
            if(+item.code === itemCode) {
                index = idx;
            }
        })
        if(index === -1) {
            let newCart = [...cart]
            products.map((product, idx) => {
                if((+product.code === itemCode) && products[idx].stock != 0) {
                    purchase.cnt++;
                    purchase.price = +purchase.price + +product.price;

                    let newProduct = product;
                    newProduct.cnt = "1";
                    newProduct.sum = product.price;

                    newCart.push(newProduct)
                    cart.push(newProduct)
                    setCarts(newCart)
                }
            })
        } else {
            let newCart = [...cart]
            cart.map((product, idx) => {
                if((+product.code === itemCode)  && (+product.cnt < +product.stock)) {
                    newCart[idx].cnt++;
                    newCart[idx].sum = +product.sum + +product.price;

                    purchase.cnt++;
                    purchase.price = +purchase.price + +product.price;
                    
                    setCarts(newCart)
                }
            })
        }
        setItemCode(0)
    }

    // 목록 제거
    const itemDelete = code => {
        let newCart = [...cart]
        cart.map((product, idx) => {
            if(product.code === code  && product.cnt < product.stock) {
                purchase.cnt -= product.cnt;
                purchase.price = +purchase.price - (+product.price) * product.cnt;
                newCart.splice(idx, 1)
            }
        })
        setCarts(newCart);
    };

    // 구매 갯수 Up&Down
    const handleAdd = code => {
        let newCart = [...cart]
        cart.map((product, idx) => {
            if(product.code === code  && product.cnt < product.stock) {
                newCart[idx].cnt++;
                newCart[idx].sum = +product.sum + +product.price;
                purchase.cnt++;
                purchase.price = +purchase.price + +product.price;
            }
        })
       setCarts(newCart);
    };
    const handleMinus = code => {
        let newCart = [...cart]
        cart.map((product, idx) => {
            if(product.code === code  && product.cnt > 1) {
                newCart[idx].cnt--;
                newCart[idx].sum = +product.sum - +product.price;
                purchase.cnt--;
                purchase.price = +purchase.price - +product.price;
            }
        })
        setCarts(newCart)
    };

    const eventBuy = () => {
        setIsStop(true)
        handleShow2()
    }

    const handleInputTel = (e) => {
        setTel(e.target.value)
    }
    const handleJoinName = (e) => {
        let newJoin = {...join}
        newJoin.name = e.target.value
        setJoin(newJoin)
    }
    const handleJoinTel = (e) => {
        let newJoin = {...join}
        newJoin.tel = e.target.value
        setJoin(newJoin)
        console.log(e.target.value)
    }
    const handleJoinEmail = (e) => {
        let newJoin = {...join}
        newJoin.email = e.target.value
        setJoin(newJoin)
    }
    

    const sendBuy = async() => {
        if (isTelephone(tel)){
            alert('전화번호 입력 오류! 다시 확인 해주세요.');
        } else {
            // if(regExp2.test(permute.tel)) { 전화번호 하이픈 없을때 넣기  } 
            // permute.tel.replace(/-/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3')

            await axios.post('/buy/send', null, {
                params: {
                    'cart': cart,
                    'tel': tel
                }
            }).then(res =>  setApply(res.data.bool))
            .catch();
        }
    }

    const eventJoin = async() => {
        var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
        console.log(isTelephone(join.tel))
        if(join.name.length === 0) {
            alert('이름 입력 오류! 다시 확인 해주세요.');
        } else if(isTelephone(join.tel)){
            alert('전화번호 입력 오류! 다시 확인 해주세요.');
        } else if(!regEmail.test(join.email)) {
            alert('이메일 입력 오류! 다시 확인 해주세요.');
        } else {
            // if(regExp2.test(permute.tel)) { 전화번호 하이픈 없을때 넣기  } 
            // permute.tel.replace(/-/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3')

            await axios.post('/buy/join', null, {
                params: {
                    'name': join.name,
                    'tel': join.tel,
                    'email': join.email
                }
            }).then(res =>  setJoinApply(res.data.bool))
            .catch();
        }
    }

    function isTelephone(str) {
        var regExp = /^01(?:0|[6-9])-(?:\d{3}|\d{4})-\d{4}$/
        var regExp2 = /^01(?:0|[6-9])(?:\d{3}|\d{4})\d{4}$/

        if(str === "" || !regExp.test(str) &&  !regExp2.test(str)){
            return true;
        }
        return false;
    }

    // cancle 창
    const [show, setShow] = useState(false);
    const [show2, setShow2] = useState(false);
    const [show3, setShow3] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const handleClose2 = () => {
        setShow2(false)
        setIsStop(false)
    };
    const handleShow2 = () => setShow2(true);
    const handleShow3 = () => setShow3(true);
    const handleClose3 = () => {
        setShow3(false)
    };

    const home = () => window.location.href = "/main"

    return ( 
        <div className='Shopping'>
            <div className="sh_center">
                <ListGroup  style={{ overflowY:"auto", height:"100%"}}>
                    {cart.map((text,idx)  =>
                        <ListGroup.Item  key={idx} className="cart_item">
                            <img className="shopimg" src={ text.img } alt="이미지 불러오기 실패" />
                                <div className='shoptext'>
                                    <p className='item_name'>{text.name}</p>
                                    <button className="cancelBtn"
                                        onClick={() => {
                                            itemDelete(text.code);
                                        }}>
                                        X
                                    </button>
                                </div>
                                <div className='shoptext'>
                                    <div className="count_buy">
                                        <button className="cntBtn"
                                            onClick={() => {
                                                handleMinus(text.code);
                                            }}
                                        >-</button>
                                        <p className='item_cnt'>{text.cnt}개</p>
                                        <button className="cntBtn"
                                            onClick={() => {
                                                handleAdd(text.code);
                                            }}
                                        >+</button>
                                    </div>
                                    <p>{text.price * text.cnt}원</p>
                                </div>
                        </ListGroup.Item>
                    )}
                </ListGroup>
            </div>
            <div className="sh_footer">
                <div className='footer_text'>
                    <div className='inline'>
                        <div className='rm'>총 갯수</div>
                        <div><div className="red">{ purchase.cnt }</div>개</div>
                    </div>
                    <div className='inline'>
                        <div className='rm'>총 결재금액</div>
                        <div><div className="red">{  purchase.price }</div>원</div>
                    </div>
                </div>
                <div className='footer_btns'>
                    <div className="right">
                        <button className='button' onClick={handleShow} >
                            취소하기
                        </button>
                        <button className='button' onClick={() => eventBuy()}>
                            결재하기
                        </button>
                        <Modal 
                            show={show} 
                            onHide={handleClose} 
                            size="lg" 
                            aria-labelledby="contained-modal-title-vcenter"
                            centered 
                        >
                            <Modal.Header closeButton style={{borderBottom:"none",fontSize:"1.5vh", marginBottom:"7vh"}}>
                                <Modal.Title  style={{fontSize:"3vh"}}>계산을 취소 하시겠습니까?</Modal.Title>
                            </Modal.Header>
                            <Modal.Footer style={{borderTop:"none"}}>
                                <Button variant="secondary" onClick={handleClose}  style={{fontSize:"2vh", marginRight:"0.5vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                취소
                                </Button>
                                <Button variant="primary" onClick={home} style={{fontSize:"2vh", marginRight:"0.5vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                확인
                                </Button>
                            </Modal.Footer>
                        </Modal>
                        <Modal 
                            show={show2} 
                            onHide={handleClose2} 
                            size="lg" 
                            aria-labelledby="contained-modal-title-vcenter"
                            centered className='modal_lg'
                        >
                            <Modal.Header closeButton style={{borderBottom:"none",fontSize:"2vh", marginBottom:"7vh"}}>
                                <Modal.Title  style={{fontSize:"3vh"}}>결재 진행</Modal.Title>
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
                            <Modal.Footer style={{borderTop:"none", marginTop:"6vh", justifyContent:"space-between"}}>
                                <div className='buyBtnLeft'>
                                    <Button variant="primary" onClick={handleShow3} style={{fontSize:"2vh", marginLeft:"0.5vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                        회원가입
                                    </Button>
                                </div>
                                <div className='buyBtnRight'>
                                    <Button variant="secondary" onClick={handleClose2}  style={{fontSize:"2vh", marginRight:"0.5vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                    취소
                                    </Button>
                                    <Button variant="success" onClick={() => sendBuy()}style={{fontSize:"2vh", marginRight:"0.5vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                    확인
                                    </Button>
                                </div>
                            </Modal.Footer>
                        </Modal>
                        <Modal 
                            show={show3} 
                            onHide={handleClose3} 
                            size="lg" 
                            aria-labelledby="contained-modal-title-vcenter"
                            centered className='modal_lg'
                        >
                            <Modal.Header closeButton style={{borderBottom:"none",fontSize:"2vh", marginBottom:"7vh"}}>
                                <Modal.Title  style={{fontSize:"3vh"}}>회원 가입</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                <Form>
                                    <Form.Group as={Row} className="mb-5">
                                        <Form.Label column sm={2} className="flb">
                                        이름
                                        </Form.Label>
                                        <Form.Control type="text" className='fcb' onChange={handleJoinName} />
                                    </Form.Group>
                                    <Form.Group as={Row} className="mb-5">
                                        <Form.Label column sm={2} className="flb">
                                        전화 번호
                                        </Form.Label>
                                        <Form.Control type="text" className='fcb' onChange={handleJoinTel} />
                                    </Form.Group>
                                    <Form.Group as={Row} className="mb-5">
                                        <Form.Label column sm={2} className="flb">
                                        이메일
                                        </Form.Label>
                                        <Form.Control type="text" className='fcb' onChange={handleJoinEmail} />
                                    </Form.Group>
                                </Form>
                            </Modal.Body>
                            <Modal.Footer style={{borderTop:"none", marginTop:"6vh"}}>
                                <Button variant="success" onClick={() => eventJoin()} style={{fontSize:"2vh", marginRight:"0.5vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                확인
                                </Button>
                            </Modal.Footer>
                        </Modal>
                    </div>
                </div>
            </div>
        </div> 
    );
}


export default Buy;