package com.ses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(
				@RequestParam(value="username") String username,
				@RequestParam(value="password") String password,
				Model model
			){

		if(username.equals("admin") && password.equals("admin")){
			return "admin";
		}
		else{
			model.addAttribute("invalidCredentials", "Invalid Credentials!");
			return "index";
		}		
	}
}
