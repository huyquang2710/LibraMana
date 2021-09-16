package com.libra.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.libra.core.services.IUserService;
import com.libra.core.utils.Utility;
import com.libra.web.message.MessageResponse;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/forgot_password")
	public String showForgotPasswordForm(Model model) {
		
		model.addAttribute("title", "Quên Mật Khẩu");
		return "forgot_password_form";
	}
	@PostMapping("/forgot_password")
	public String processForgotPasswordForm(Model model, HttpServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		String token = RandomString.make(45); // sinh ra mã token 45 chữ ngẫu nhiên
		
		System.out.println("email: " + email);
		System.out.println("token: " + token);
		
		try {
			// database sẽ sinh được add 1 fotgot_pass_token
			userService.updateResetPassword(token, email);
			
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
			System.out.println(resetPasswordLink);
			
		} catch (UsernameNotFoundException e) {
			session.setAttribute("message", new MessageResponse(e.getMessage(), "alert-error"));
		}
		
		return "forgot_password_form";
	}
}
