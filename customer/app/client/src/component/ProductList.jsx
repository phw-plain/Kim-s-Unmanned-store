import { useState } from "react";
import { Link } from 'react-router-dom';
import Pagination from "./Pagination";

export function ProductList(props) {
  const [limit, setLimit] = useState(4);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;
  
  return (
    <div>
      <div className="products-box">
        {props.products.slice(offset, offset + limit).map((item, idx) => (
          <div className="products" key={idx}>
            <div className='prod_background'>
              <Link to={`/product/${item.code}`} >
              <img className="products_img" src={"/uploadfile/"+item.img} alt={"product-img"}/></Link>
              <div>
                <p className='prod_title'>{item.name}</p>
                <div className="prod_texts">
                  <p className='prod_cate'>#{item.category}</p>
                  <p className='prod_text'>
                    {item.text}<br/>
                    {item.price} 원
                  </p>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>

      <footer>
        <Pagination
          total={props.products.length}
          limit={limit}
          page={page}
          setPage={setPage}
        />
      </footer>
    </div>
  );
}

export default ProductList;
