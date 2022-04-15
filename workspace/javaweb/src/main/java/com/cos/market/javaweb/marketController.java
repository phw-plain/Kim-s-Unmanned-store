package com.cos.market.javaweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class marketController {

	@GetMapping("/")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
	
}
