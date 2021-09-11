package com.libra.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.libra.core.services.IUserService;
import com.libra.web.dto.SignUpDTO;
import com.libra.web.message.MessageResponse;

@Controller
public class HomeController {
	@Autowired
	private IUserService userService;
	//home
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Library Management Admin ");
		return "home/home";
	}
	
	//admin
	@GetMapping("/admin")
	public String homeAdmin(Model model) {
		model.addAttribute("title", "Library Management Admin ");
		return "admin/home";
	}
	
	//authentication
	@GetMapping("/login")
	public String signin(Model model) {
		model.addAttribute("title", "Đăng Nhập");
		return "authentication/login";
	}
	@GetMapping("/register")
	public String signup(Model model) {
		model.addAttribute("title", "Đăng Ký");
		model.addAttribute("user", new SignUpDTO());
		return "authentication/signup";
	}
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") SignUpDTO signUpDTO, BindingResult bindingResult, HttpSession session, Model model) {
		try {
//			//Kiem tra usernamme
//			if(userService.userExists(signUpDTO.getUsername())) {
//				bindingResult.addError(new FieldError("signUpDTO", "username", "Tài khoản đã tồn tại"));
//			}
//			//Kiem tra usernamme
//			if(userService.emailExists(signUpDTO.getEmail())) {
//				bindingResult.addError(new FieldError("signUpDTO", "email", "Email đã tồn tại"));
//			}
			//Kiem tra sai cu phap
			if(bindingResult.hasErrors()) {
				return "authentication/login";
			}
			model.addAttribute("title", "Đăng Ký");
			model.addAttribute("user", new SignUpDTO());
			userService.register(signUpDTO);
		
			//message
			session.setAttribute("message", new MessageResponse("Đăng ký thành công!!", "alert-success"));
			
			System.out.println(signUpDTO.toString());
			return "redirect:/login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", signUpDTO);
			session.setAttribute("message", new MessageResponse("Aushiet!!. Có lỗi xảy ra" +e.getMessage(), "alert-danger"));
			return "authentication/signup";
		}
	}
	
}
