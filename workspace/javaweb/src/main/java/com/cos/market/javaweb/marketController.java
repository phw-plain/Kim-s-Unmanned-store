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
    	List<String> img = new ArrayList<>();
    	
    	name.add("토종 햇 당근");
    	name.add("야이셔 레몬");
    	name.add("바나나는 길어");
    	name.add("사과는 맛있어");
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

    	img.add("https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__340.jpg");
    	img.add("https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830__340.jpg");
    	img.add("https://cdn.pixabay.com/photo/2016/09/03/20/48/bananas-1642706__340.jpg");
    	img.add("https://cdn.pixabay.com/photo/2016/11/18/13/47/apple-1834639_960_720.jpg");
    	img.add("http://image.mujikorea.net/goods/31/13/25/24/4550182146691_N_N_400.jpg");
    	img.add("http://image.mujikorea.net/goods/31/13/25/24/4550182146691_N_N_400.jpg");
    	img.add("http://image.mujikorea.net/goods/31/13/25/24/4550182146691_N_N_400.jpg");
    	img.add("http://image.mujikorea.net/goods/31/13/25/24/4550182146691_N_N_400.jpg");
    	
    	for(int i=0; i<name.size(); i++) {
        	list = new ArrayList<>();
        	list.add(name.get(i));
        	list.add(text.get(i));
        	list.add(Integer.toString(price.get(i)));
        	list.add(img.get(i));
        	lists.add(list);
    	}

        return lists;
    }
    
}