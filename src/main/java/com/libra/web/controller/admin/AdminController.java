 package com.libra.web.controller.admin;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.core.entities.User;
import com.libra.core.services.IUserService;
import com.libra.exception.ResourceNotFoundException;
import com.libra.web.dto.AdminDTO;
import com.libra.web.message.MessageResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {
		
	@Autowired
	private IUserService userService;
	@Autowired
	private ModelMapper mapper;
	
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
		
		User account = userService.getUsernameByUsername(username);
		
		//chueyẻn entity thanh dto
		AdminDTO adminDTO = mapper.map(account, AdminDTO.class);
		model.addAttribute("title", "Thông Tin Tài Khoản");
		model.addAttribute("account", adminDTO);
		
		return "admin/accountPage";
	}
	
	@GetMapping("/account/findById/{id}")
	public String accountEdit(@PathVariable("id") Integer id ,Model model, HttpSession session) throws ResourceNotFoundException {
		if(id != null) {
			Optional<User> userOtp = this.userService.findById(id);
			User user = userOtp.get();
			
			model.addAttribute("account", user);
			model.addAttribute("title", "Chỉnh Sửa Tài Khoản");
			return "admin/accountEdit";
		}
		session.setAttribute("message", new MessageResponse("Không tìm thấy Tài Khoản!!, vui lòng thử lại!", "danger"));
		return "admin/accountPage";
	}
}
