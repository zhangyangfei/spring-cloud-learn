package com.zyf.appserver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class ToTestController {
	
	//http://localhost:8001/test/testindex
	@RequestMapping("/testindex")
	public String init() {
		return "test/testindex";
	}
}
