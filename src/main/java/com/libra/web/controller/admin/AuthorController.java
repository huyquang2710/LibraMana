package com.libra.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public class AuthorController {
	
	@GetMapping
	public String authorPage(Model model) {
		model.addAttribute("title", "Tác Giả");
		return "admin/author/authorPage";
	}
}
