package com.libra.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Trang Chủ");
		return "user/home";
	}
	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("title", "Trang Chủ Admin");
		return "admin/admin";
	}
	@GetMapping("/user")
	public String user(Model model) {
		model.addAttribute("title", "Người Dùng");
		return "user/user";
	}
}
