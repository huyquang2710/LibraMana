package com.libra.web.controller.admin;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.core.entities.User;
import com.libra.core.services.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IUserService userService;
	
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
	/*
	 * @GetMapping public String admin(Model model, Principal principal) {
	 * 
	 * System.out.println(getPrincipal());
	 * 
	 * User user = userService.getUsernameByUsername(getPrincipal());
	 * 
	 * System.out.println("Name: " + user); model.addAttribute("title",
	 * "Trang Chủ Admin"); model.addAttribute("user", user); return "admin/base"; }
	 */
}
