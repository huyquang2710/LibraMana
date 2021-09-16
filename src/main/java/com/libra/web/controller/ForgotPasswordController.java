package com.libra.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
	@GetMapping("/forgot_password")
	public String showForgotPasswordForm(Model model) {
		
		model.addAttribute("title", "Quên Mật Khẩu");
		return "forgot_password_form";
	}
	@PostMapping("/forgot_password")
	public String processForgotPasswordForm(Model model, HttpServletRequest request) {
		String email = request.getParameter("email");
		String token = RandomString.make(45); // sinh ra mã token 45 chữ ngẫu nhiên
		
		
		System.out.println("email: " + email);
		System.out.println("token: " + token);
		return "forgot_password_form";
	}
}
