package com.zyf.appserver.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ToHtmlController {
	
	//http://localhost:9001/user/userindex
	@RequestMapping("/userindex")
	public String init() {
		return "user/userindex";
	}
}
