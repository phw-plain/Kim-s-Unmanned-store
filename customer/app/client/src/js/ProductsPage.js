import React, { useEffect, useState } from 'react'; 

import  '.././css/Products.css';
import { Products } from '../component/Products.jsx'

const ProductsPage = () => { 
  
  return ( 
    <div className="Products">
      <Products />
    </div>
  ); 

}

export default ProductsPage;