import React, { useState, useEffect } from 'react'; 
import { Link, useParams } from 'react-router-dom';
import { Button, Spinner, Form, Col, Row } from "react-bootstrap";
import { HiArrowRight } from 'react-icons/hi'
import axios from 'axios';

function Change() { 
    const {productId} = useParams();
    const {permuteId} = useParams();
    const [products, setProducts] = useState([]);   // products 데이터가 아닌 바코드 스캔 정보를 바탕으로 객체 하나만 가지고오기
    const [imgUrl, setImgUrl] = useState("");
    const [name, setName] = useState("");
    const [stock, setStock] = useState(0);
    const [change, setChange] = useState("");
    const [tel, setTel] = useState();
    const [permute, setPermute] = useState({ cnt:"", res:"", gro:"" });
    const [apply, setApply] = useState();


    useEffect(() => {
        axios.post('/products')
        .then(res => setProducts(res.data))
        .catch();

        let newPermute = {...permute}

        axios.post('/permute/tel/get')
        .then(res => setTel(res.data.tel))
        .catch();

        console.log(tel)

        if(permuteId === "refund") {
            setChange("환불")
        } else {
            setChange("교환")
        }
    }, [])

    useEffect(() => {
        if(products.length > 0) {
            setImgUrl(products[0].img)
            setName(products[0].name)
            setStock(products[0].stock)
        }
    }, [products])

    useEffect(() => {
        if(apply !== undefined){
            if(apply) {
                alert(change + "신청 완료!")
                window.location.href = "/main";
            } else {
                alert("사용자의 정보와 일치하는 구매내역이 없습니다!")
            }
        }
    }, [apply])
    
    const handleInputCnt = (e) => {
        let newPermute = {...permute}
        newPermute.cnt = e.target.value
        setPermute(newPermute)
    }
    const handleInputRes = (e) => {   
        let newPermute = {...permute}
        newPermute.res = e.target.value
        setPermute(newPermute)    
    }
    const handleInputGro = (e) => {
        let newPermute = {...permute}
        newPermute.gro = e.target.value
        setPermute(newPermute)
    }

    const Apply = async() => {
        var regExp = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/
        var regExp2 = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/

        if(permute.cnt === "" || isNaN(permute.cnt) || +permute.cnt === 0 || +permute.cnt > stock) {
            alert('수량 입력 오류! 다시 확인 해주세요.');
        } else if (permute.gro === ""){
            alert(change + '사유를 입력해주세요.')
        } else {
            // 신청 사항 저장하기
            // if(regExp2.test(permute.tel)) { 전화번호 하이픈 없을때 넣기  } 
            // permute.tel.replace(/-/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3')

            await axios.post('/permute/apply', null, {
                params: {
                    'code': productId,
                    'name': name,
                    'cnt': permute.cnt,
                    'tel': tel,
                    'res': permute.res,
                    'gro': permute.gro
                }
            }).then(res =>  setApply(res.data.bool))
            .catch();
        }
    }

    return ( 
        <div>
        <div className='Rank_Header between'>
           <div>
               <p className='Rank_title'>상품 {change}</p>
               <p className='Rank_subTitle'>개봉된 상품은 {change} 불가능 합니다.</p>
           </div>
           <Link to="../../permute">
               <HiArrowRight className='arrow'/>
           </Link>
       </div>
       <div style={{ margin: "5vh 10vh 5vh 10vh", fontSize: "2vh" }}>
            <img className='permute_img' src={imgUrl} alt="이미지 불러오기 실패"/>
           <Form style={{ textAlign:"left" }}>
               <Form.Group as={Row} className="mb-5 fg" controlId="formHorizontalEmail">
                   <Form.Label column sm={2} style={{width:"10vh"}}>
                   제품명
                   </Form.Label>
                   <Form.Label type="text" className='fc'>{name}</Form.Label>
               </Form.Group>
               <Form.Group as={Row} className="mb-5 fg">
                   <Form.Label column sm={2} style={{width:"10vh"}}>
                   수량
                   </Form.Label>
                   <Form.Control type="text" className='fc' onChange={handleInputCnt} />
               </Form.Group>
               <Form.Group as={Row} className="mb-5 fg">
                   <Form.Label column sm={2} style={{width:"10vh"}}>
                   전화 번호
                   </Form.Label>
                   <Form.Label column sm={2} className='fc'>
                   {tel}
                   </Form.Label>
               </Form.Group>
               <Form.Group as={Row} className="mb-5 fg">
                   <Form.Label column sm={2} style={{width:"10vh"}}>
                   {change} 사유
                   </Form.Label>
                   <Form.Select size="sm" className='fc' onMouseOut={handleInputRes}>
                       <option>상품 결함</option>
                       <option>단순 변심</option>
                   </Form.Select>
               </Form.Group>
               <Form.Group as={Row} className="mb-5 fg">
                   <Form.Label column sm={2} style={{width:"10vh"}}/>
                   <Form.Control as="textarea" rows={3} className="fc" onChange={handleInputGro} />
               </Form.Group>
               <Form.Group as={Row}  className="mt-10 center">
                   <Button style={{ fontSize: "2vh", width:"10vh" }} onClick={() => Apply()}>{change} 신청</Button>
               </Form.Group>
           </Form>
       </div>
   </div>
    );
} 

export default Change;