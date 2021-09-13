package com.libra.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.libra.core.services.IUserService;
import com.libra.web.dto.SignUpDTO;
import com.libra.web.message.MessageResponse;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {
	@Autowired
	private final IUserService userService;

	//authentication
	// login form
	@GetMapping("/login")
	public String signin(Model model) {
		model.addAttribute("title", "Đăng Nhập");
		model.addAttribute("user");
		return "/authentication/login";
	}
	// registration form
	@GetMapping("/register")
	public String signup(Model model) {
		model.addAttribute("title", "Đăng Ký");
		model.addAttribute("user", new SignUpDTO());
		return "/authentication/signup";
	}
	// error 403
	@GetMapping("/403")
	public String getAccessDenied() {
		return "/authentication/403page";
	}
	// registration method
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") SignUpDTO signUpDTO, BindingResult bindingResult, HttpSession session, Model model) {
		try {
			//Kiem tra usernamme
			if(userService.userExists(signUpDTO.getUsername())) {
				bindingResult.addError(new FieldError("user", "username", "Tài khoản đã tồn tại"));
			}
			//Kiem tra email
			if(userService.emailExists(signUpDTO.getEmail())) {
				bindingResult.addError(new FieldError("user", "email", "Email đã tồn tại"));
			}
			//Kiem tra sai cu phap
			if(bindingResult.hasErrors()) {
				System.out.println("Error: " + bindingResult.toString());
				model.addAttribute("user", signUpDTO);
				return "authentication/signup";
			}
			model.addAttribute("title", "Đăng Ký");
			model.addAttribute("user", new SignUpDTO());
			userService.register(signUpDTO);
		
			//message
			session.setAttribute("message", new MessageResponse("Đăng ký thành công!!", "alert-success"));
			
			System.out.println(signUpDTO.toString());
			return "authentication/signup";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", signUpDTO);
			session.setAttribute("message", new MessageResponse("Aushiet!!. Có lỗi xảy ra" +e.getMessage(), "alert-danger"));
			return "authentication/signup";
		}
	}
	
}
