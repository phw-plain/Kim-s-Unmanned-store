import React from "react";

export function Receipt(props) {     
    return (
        <div className="Receipt">
            <div className="r_list">
                {props.cart.map((item, idx) => (
                    <div className="receipt_list" key={idx}>
                        <div>{item.name}</div>
                        <div className="receipt_list2">
                            <div>{item.cnt}</div>
                            <div>{item.sum}원</div>
                        </div>
                    </div>
                ))} 
            </div>
            <div className="total">
                <div>총 {props.purchase.cnt}</div>
                <div>{props.purchase.price}원</div>
            </div>
        </div>
    );
}

export default Receipt;
