package com.libra.web.controller.user;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.libra.core.entities.User;
import com.libra.core.services.IUserService;
import com.libra.core.utils.FileUploadUtil;
import com.libra.core.utils.GetPrincipal;
import com.libra.exception.BadResourceException;
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
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
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
	//update account
	@PostMapping("/info")
	public String updateUser(
			@Valid @ModelAttribute("user") User user,
			BindingResult bindingResul,
			@RequestParam("imageFile") MultipartFile multipartFile,
			Model model,
			HttpSession session) {
		try {
			if(bindingResul.hasErrors()) {
				return "user/userInfo/userInfoEdit";
			}
			User oldUser = userService.findById(user.getId()).get();
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

			if(!multipartFile.isEmpty()) {
				//delete image
				String deleteFile = "avatar/author/" + user.getId();
				File file = new File(deleteFile, oldUser.getImage());
				file.delete();	
			
			//them anh
			user.setImage(fileName);
			String uploadDir = "avatar/account/" + user.getId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			}
			userService.update(user);
			
			model.addAttribute("user", user);
			session.setAttribute("message", new MessageResponse("Cập nhật thành công!!", "success"));
			return "redirect:/user/info";
		} catch (UsernameNotFoundException | BadResourceException | ResourceNotFoundException | IOException e) {
			String errorMess = e.getMessage();
			LOGGER.error(errorMess);
			session.setAttribute("message", new MessageResponse( e.getMessage(), "danger"));
			return "user/userInfo/userInfoEdit";
		}
	}
}
