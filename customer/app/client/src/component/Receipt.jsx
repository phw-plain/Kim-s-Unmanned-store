import React from "react";

export function Receipt(props) {     
    return (
        <div className="Receipt">
            {props.cart.map((item, idx) => (
                <div className="receipt_list">
                    <div>{item.name}</div>
                    <div className="receipt_list2">
                        <div>{item.cnt}</div>
                        <div>{item.sum}원</div>
                    </div>
                </div>
            ))} 
        </div>
    );
}

export default Receipt;
