import React, { useEffect, useState, useRef } from 'react';
import { Link } from 'react-router-dom';
import { ListGroup,  Modal, Button, Form, Row } from "react-bootstrap";
import axios from 'axios';

import { Receipt } from '../components/Receipt.jsx'
import isLogin from '../components/isLogin.jsx'
import '.././css/Buy.css';

const Buy = () => {
    isLogin()
    
    const [itemCode, setItemCode] = useState(0);                      // 바코드 스캔한 데이터
    const [isStop, setIsStop] = useState(false)                             // 바코드 리더 스캔 실시간 서버 연동 여부
    
    const [products, setProducts] = useState([]);                            // 총 상품 목록
    const [cart, setCarts] = useState([]);                                          // 구매 상품 목록
    const [purchase, setPurchase] = useState({cnt:0, price: 0});      // 구매 갯수, 구매 총액
    const [apply, setApply] = useState();                                        // 구매 완료 체크

    const [tel, setTel] = useState("");                                             // 고객 전화번호    (회원 전용)
    const [member, setMember] = useState({pw:"", point:""});    // 고객 포인트, Pw  (회원 전용)
    const [usePoint, setUsePoint] = useState();                            // 사용할 포인트    (회원 전용)
    const [getPoint, setGetPoint] = useState();                            // 사용할 포인트    (회원 전용)
    const [usePw, setUsePw] = useState();                                   // 고객 비밀번호    (회원 전용)

    const [join, setJoin] = useState({id:"", pw:"", name:"", tel:"", email:""});      // 고객 회원가입
    const [joinId, setJoinId] = useState();                                                         // 아이디 중복 체크
    const [joinApply, setJoinApply] = useState();                                             // 회원가입 성공 체크

    useEffect(() => {
        axios.post('/products')
        .then(res => setProducts(res.data))
        .catch();
    }, []);

    let loop = useInterval(() => {
        if(!isStop) {
            cartAdd();
        }
    }, 1000);

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
            if(apply){
                handleShow4()
            } else {
                alert("전화번호 입력 오류!")
                setApply(undefined)
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

    useEffect(() => {
        if(joinId !== undefined) {
            if(joinId) {
                alert('사용 가능한 아이디 입니다!')
            } else {
                alert('사용 불가능한 아이디 입니다!')
            }
        }
    }, [joinId])

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

    // 실시간 데이터 저장
    const eventBuy = () => {
        setIsStop(true)
        handleShow2()
    }
    const handleInputTel = (e) => {
        setTel(e.target.value)
    }
    const handleJoinId = (e) => {
        let newJoin = {...join}
        newJoin.id = e.target.value
        setJoin(newJoin)
    }
    const handleJoinPw = (e) => {
        let newJoin = {...join}
        newJoin.pw = e.target.value
        setJoin(newJoin)
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
    }
    const handleJoinEmail = (e) => {
        let newJoin = {...join}
        newJoin.email = e.target.value
        setJoin(newJoin)
    }
    const handleUsePoint = (e) => {
        setUsePoint(e.target.value);
    }
    const handleUsePw = (e) => {
        setUsePw(e.target.value);
    }

    const overlap = async() => {
        if(join.id !== ""){
            await axios.post('/buy/join/overlap', null, {
                params: {
                    'id':join.id
                }
            }).then(res => setJoinId(res.data.bool))
            .catch();
        }
    }

    const sendBuy = (isMember) => {
        if (isMember && !isTelephone(tel)){
            alert('전화번호 입력 오류! 다시 확인 해주세요.');
        } else {
            if(isMember) {
                var regExp2 = /^01(?:0|[6-9])(?:\d{4}|\d{4})\d{4}$/

                let newTel = tel;

                if(regExp2.test(newTel)) { 
                    newTel = newTel.replace(/-/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3') 
                    setTel(newTel)
                }

                axios.post('/buy/send/member', null, {
                    params: {
                        'tel': newTel
                    }
                }).then(res =>  setMember(res.data.member))
                .catch();

                setGetPoint(purchase.price * 0.05);

                handleShow5()
            } else {
                axios.post('/buy/send', null, {
                    params: {
                        'cart': cart,
                        'tel': "-1"
                    }
                }).then(res =>  setApply(res.data.bool))
                .catch();
            }

            
        }
    }

    const memberBuy = (bool) => {
        if(bool) {
            if(+usePoint > +member.point) {
                alert('포인트 잔액을 초과하는 금액은 사용할 수 없습니다.')
            } else if (+usePoint < 100 || +usePoint%100 !== 0) {
                alert('포인트는 100단위로 사용하실 수 있습니다.')
            } else if (usePw !== member.pw) {
                alert('비밀번호 입력 오류! 다시 확인해주세요.')
            } else {
                axios.post('/buy/send', null, {
                    params: {
                        'cart': cart,
                        'tel': tel,
                        'usePoint': usePoint,
                        'getPoint': getPoint
                    }
                }).then(res =>  setApply(res.data.bool))
                .catch();
            }
        } else {
            axios.post('/buy/send', null, {
                params: {
                    'cart': cart,
                    'tel': tel,
                    'usePoint': undefined,
                    'getPoint': getPoint
                }
            }).then(res =>  setApply(res.data.bool))
            .catch();
        }
    }

    const eventJoin = async() => {
        var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
        var regExp2 = /^01(?:0|[8])(?:\d{3}|\d{4})\d{4}$/


        if(join.id.length === 0) {
            alert('아이디를 작성해주세요!');
        } else if(join.pw.length === 0) {
            alert('비밀번호 입력 오류! 다시 확인 해주세요.');
        } else if(join.name.length === 0) {
            alert('이름 입력 오류! 다시 확인 해주세요.');
        } else if(!isTelephone(join.tel)){
            alert('전화번호 입력 오류! 다시 확인 해주세요.');
        } else if(!regEmail.test(join.email)) {
            alert('이메일 입력 오류! 다시 확인 해주세요.');
        } else {

            let newTel = join.tel;

            if(regExp2.test(newTel)) { 
                newTel = newTel.replace(/-/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3') 
                setTel(newTel)
            }

            await axios.post('/buy/join', null, {
                params: {
                    'id': join.id,
                    'pw': join.pw,
                    'name': join.name,
                    'tel': newTel,
                    'email': join.email
                }
            }).then(res =>  setJoinApply(res.data.bool))
            .catch();
        }
    }

    function isTelephone(str) {
        var regExp = /^01(?:0|[6-9])-(?:\d{4}|\d{4})-\d{4}$/
        var regExp2 = /^01(?:0|[6-9])(?:\d{4}|\d{4})\d{4}$/

        if(str === "" || !regExp.test(str) &&  !regExp2.test(str)){
            return false;
        }
        return true;
    }

    // cancle 창
    const [show, setShow] = useState(false);
    const [show2, setShow2] = useState(false);
    const [show3, setShow3] = useState(false);
    const [show4, setShow4] = useState(false);
    const [show5, setShow5] = useState(false);
    const handleShow = () => setShow(true);
    const handleClose = () => setShow(false);
    const handleShow2 = () => {
        if(cart.length !== 0){
            setShow2(true);
        } else {
            alert('카트에 담긴 상품이 없습니다!!')
            setIsStop(false)
        }
    }
    const handleClose2 = () => {
        setShow2(false)
        setIsStop(false)
    };
    const handleShow3 = () => setShow3(true);
    const handleClose3 = () => {
        setShow3(false)
    };
    const handleShow4 = () => setShow4(true);
    const handleClose4 = () => {
        window.location.href = "/main";
    };
    const handleShow5 = () => setShow5(true);
    const handleClose5 = () => {
        setShow5(false);
    };

    const home = () => window.location.href = "/main"

    return ( 
        <div className='Shopping'>
            <div className="sh_center">
                <ListGroup  style={{ overflowY:"auto", height:"100%"}}>
                    {cart.map((text,idx)  =>
                        <ListGroup.Item  key={idx} className="cart_item">
                            <img className="shopimg" src={ "/uploadfile/"+text.img } alt="이미지 불러오기 실패" />
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
                                    <Button variant="secondary" onClick={() => sendBuy(false)}  style={{fontSize:"2vh", marginRight:"0.5vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                    비회원
                                    </Button>
                                    <Button variant="success" onClick={() => sendBuy(true)}style={{fontSize:"2vh", marginRight:"0.5vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
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
                                        아이디
                                        </Form.Label>
                                        <Form.Control type="text" className='fcb' onChange={handleJoinId} onMouseOut={overlap} />
                                    </Form.Group>
                                    <Form.Group as={Row} className="mb-5">
                                        <Form.Label column sm={2} className="flb">
                                        비밀번호
                                        </Form.Label>
                                        <Form.Control type="text" className='fcb' onChange={handleJoinPw} />
                                    </Form.Group>
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
                        <Modal 
                            show={show4} 
                            onHide={handleClose4} 
                            size="lg" 
                            aria-labelledby="contained-modal-title-vcenter"
                            centered className='modal_lg'
                        >
                            <Modal.Header closeButton style={{ borderBottom:"none",fontSize:"2vh", margin:"2vh 2vh 2vh 2vh"}}>
                                <Modal.Title  style={{fontSize:"3vh"}}>영수증</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                <Receipt cart={cart} purchase={purchase} />
                            </Modal.Body>
                            <Modal.Footer style={{borderTop:"none", marginTop:"2vh"}}>
                                <Button variant="success" onClick={() => handleClose4()} style={{fontSize:"2vh", margin:"2vh 2vh 2vh 2vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                확인
                                </Button>
                            </Modal.Footer>
                        </Modal>
                        <Modal 
                            show={show5} 
                            onHide={() => memberBuy(false)} 
                            size="lg" 
                            aria-labelledby="contained-modal-title-vcenter"
                            centered className='modal_lg'
                        >
                            <Modal.Header closeButton style={{ borderBottom:"none",fontSize:"2vh", margin:"2vh 2vh 4vh 2vh"}}>
                                <Modal.Title  style={{fontSize:"3vh"}}>포인트 사용</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                <Form.Group as={Row} className="mb-5">
                                    <Form.Label column sm={2} className="flb">
                                    포인트 잔액
                                    </Form.Label>
                                    <Form.Label column sm={2} className="fcb">
                                    {member.point}
                                    </Form.Label>
                                </Form.Group>
                                <Form.Group as={Row} className="mb-5">
                                    <Form.Label column sm={2} className="flb">
                                    적립 예정
                                    </Form.Label>
                                    <Form.Label column sm={2} className="fcb">
                                    {getPoint}
                                    </Form.Label>
                                </Form.Group>
                                <Form.Group as={Row} className="mb-5">
                                    <Form.Label column sm={2} className="flb">
                                    사용 포인트
                                    </Form.Label>
                                    <Form.Control type="text" className='fcb' onChange={handleUsePoint} />
                                </Form.Group>
                                <Form.Group as={Row} className="mb-5">
                                    <Form.Label column sm={2} className="flb">
                                    비밀번호
                                    </Form.Label>
                                    <Form.Control type="text" className='fcb' onChange={handleUsePw} />
                                </Form.Group>
                            </Modal.Body>
                            <Modal.Footer style={{borderTop:"none", marginTop:"2vh"}}>
                                <Button variant="success" onClick={() =>  memberBuy(true)} style={{fontSize:"2vh", margin:"2vh 1vh 2vh 2vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                확인
                                </Button>
                                <Button onClick={() => memberBuy(false)} style={{fontSize:"2vh", margin:"2vh 2vh 2vh 0vh", paddingLeft: "1vh", paddingRight: "1vh"}}>
                                취소
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