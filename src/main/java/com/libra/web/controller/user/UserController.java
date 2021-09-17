package com.libra.web.controller.user;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.core.entities.User;
import com.libra.core.services.IUserService;
import com.libra.core.utils.GetPrincipal;
import com.libra.exception.ResourceNotFoundException;
import com.libra.web.dto.UserDTO;
import com.libra.web.message.MessageResponse;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	//info form
	@GetMapping("/info")
	public String userInfo(Model model) {
		System.out.println(GetPrincipal.getPrincipal());
		User user = userService.getUsernameByUsername(GetPrincipal.getPrincipal());
		
		// entity to dto
		UserDTO dto = modelMapper.map(user, UserDTO.class);
		System.out.println("DTO: " + dto);
		
		model.addAttribute("user", dto);
		model.addAttribute("title", "Thông Tin Cá Nhân");
		return "user/userInfo/userInfo";
	}
	
	// update info form findById
	@GetMapping("/info/findById/{id}")
	public String userUpdate(@PathVariable("id") Integer id, Model model, HttpSession session) throws ResourceNotFoundException {
		
		if(id != null) {
			Optional<User> userOtp = this.userService.findById(id);
			User user = userOtp.get();
			
			model.addAttribute("user", user);
			return "user/userInfo/userInfoEdit";
		}
		
		session.setAttribute("message", new MessageResponse("Không tìm thấy Tài Khoản!!, vui lòng thử lại!", "danger"));
		model.addAttribute("title", "Thông Tin Cá Nhân");
		return "user/userInfo/userInfo";
	}
}
