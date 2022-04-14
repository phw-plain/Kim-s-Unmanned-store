package com.cos.market.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//스프링이 com.cos.market 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것은 아니다.
// 특정 어노테이션에 붙어있는 클래스 파일들을 new해서(IOC) 스프링 컨테이너에 관리해준다.
@RestController	
public class MarketController {
	
	// http://localhost:80/test/hello
	@GetMapping("/")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
	
	
}