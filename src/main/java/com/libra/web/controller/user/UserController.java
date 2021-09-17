package com.libra.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@GetMapping("/info")
	public String userInfo(Model model) {
		
		model.addAttribute("title", "Thông Tin Cá Nhân");
		return "user/userInfo";
	}
	
	// upda
}
