package com.cos.market.javaweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class marketController {

    @GetMapping("/home")
    public String hello() {
        return "Hello World!";
    }
 
    @GetMapping("/products")
    public List<List> products() {
    	List<List> lists = new ArrayList<>();
    	List<String> list = new ArrayList<>();
    	
    	List<String> name = new ArrayList<>();
    	List<String> text = new ArrayList<>();
    	List<Integer> price = new ArrayList<>();
    	
    	name.add("상품명1");
    	name.add("상품명2");
    	name.add("상품명3");
    	name.add("상품명4");
    	name.add("상품명5");
    	name.add("상품명6");
    	name.add("상품명7");
    	name.add("상품명8");
    	
    	text.add("상품설명1");
    	text.add("상품설명2");
    	text.add("상품설명3");
    	text.add("상품설명4");
    	text.add("상품설명5");
    	text.add("상품설명6");
    	text.add("상품설명7");
    	text.add("상품설명8");

    	price.add(1000);
    	price.add(2000);
    	price.add(3000);
    	price.add(4000);
    	price.add(5000);
    	price.add(6000);
    	price.add(7000);
    	price.add(8000);
    	
    	for(int i=0; i<name.size(); i++) {
        	list = new ArrayList<>();
        	list.add(name.get(i));
        	list.add(text.get(i));
        	list.add(Integer.toString(price.get(i)));
        	lists.add(list);
    	}

        return lists;
    }
    
}