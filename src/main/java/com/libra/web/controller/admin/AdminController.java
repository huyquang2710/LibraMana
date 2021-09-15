 package com.libra.web.controller.admin;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	//  lấy được username của principal đã được xác thực
	public String getPrincipal() {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}
	
	@GetMapping
	public String admin(Model model, Principal principal) {	
		
		System.out.println(getPrincipal());
		
		model.addAttribute("title", "Trang Chủ Admin");
		return "admin/base";
	}
	
	//form account info
	
	@GetMapping("/account")
	public String accoundAdmin(Model model, Principal principal) {
		
		String username = principal.getName();
		System.out.println("username:" + username);
		
		model.addAttribute("title", "Thông Tin Tài Khoản");
		
		return "admin/accountPage";
	}
}
