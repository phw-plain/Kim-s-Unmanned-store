import { useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import styled from "styled-components";
import Pagination from "./Pagination";
import axios from 'axios';

export function Products() {
  const [products, setProducts] = useState([]);
  const [limit, setLimit] = useState(4);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;
  
  useEffect(() => {
    axios.post('/products')
    .then(res => setProducts(res.data))
    .catch()
  }, [])

  return (
    <div>
      <div className="products-box">
        {products.slice(offset, offset + limit).map((item, idx) => (
          <div className="products" key={idx}>
            <div className='prod_background'>
              <Link to={`/product/${idx}`} >
              <img className="products_img" src={item.img} alt={"product-img"}/></Link>
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
        ))}
      </div>

      <footer>
        <Pagination
          total={products.length}
          limit={limit}
          page={page}
          setPage={setPage}
        />
      </footer>
    </div>
  );
}

export default Products;
