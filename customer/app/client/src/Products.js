import React, { useEffect, useState } from 'react'; 

import  './css/Products.css';
import { ProductList } from './component/ProductList.jsx'

const Products = () => { 
  
  return ( 
    <div className="Products">
      <ProductList />
    </div>
  ); 

}

export default Products;