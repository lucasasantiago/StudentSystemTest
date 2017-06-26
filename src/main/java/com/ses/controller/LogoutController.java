package com.ses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping(value="/logout")
	public String logout(){
		
		//logout methods should go here ... This is missing!
		return "index";
	}
}
