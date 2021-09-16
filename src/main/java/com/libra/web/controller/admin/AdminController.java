package com.libra.web.controller.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.libra.exception.ResourceNotFoundException;
import com.libra.web.dto.AdminDTO;
import com.libra.web.dto.SignUpDTO;
import com.libra.web.message.MessageResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserService userService;
	@Autowired
	private ModelMapper mapper;

	// lấy được username của principal đã được xác thực
	public String getPrincipal() {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
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

	// form account info

	@GetMapping("/account")
	public String accoundAdmin(Model model, Principal principal) {

		String username = principal.getName();
		System.out.println("username:" + username);

		User account = userService.getUsernameByUsername(username);

		// chueyẻn entity thanh dto
		AdminDTO adminDTO = mapper.map(account, AdminDTO.class);
		model.addAttribute("title", "Thông Tin Tài Khoản");
		model.addAttribute("account", adminDTO);

		return "admin/accountPage";
	}

	// find By Id
	@GetMapping("/account/findById/{id}")
	public String accountEdit(@PathVariable("id") Integer id, Model model, HttpSession session)
			throws ResourceNotFoundException {
		if (id != null) {
			Optional<User> userOtp = this.userService.findById(id);
			User user = userOtp.get();

			model.addAttribute("account", user);
			model.addAttribute("title", "Chỉnh Sửa Tài Khoản");
			return "admin/accountEdit";
		}
		session.setAttribute("message", new MessageResponse("Không tìm thấy Tài Khoản!!, vui lòng thử lại!", "danger"));
		return "admin/accountPage";
	}

//	// update account info
	@PostMapping("/account/update")
	public String accountUpdate(
		@Valid @ModelAttribute("user") User user,
		BindingResult bindingResult,
		@RequestParam("imageFile") MultipartFile imageFile,
		Model model,
		HttpSession session ) {
		try {
			if(bindingResult.hasErrors()) {
				System.out.println("Account: " + bindingResult.toString());
				//model.addAttribute("author", authorDTO);
				return "admin/account/accountEdit";
			}
		// lấy dữ liệu cũ
			User oldUser = userService.findById(user.getId()).get();
		
			if(imageFile.isEmpty()) {
				System.out.println("File ảnh tồn tại !!");
				// xoa anh cu di
				File deleteFile = new ClassPathResource("static/image/account").getFile();
				File deleteAction = new File(deleteFile, oldUser.getImage());
				deleteAction.delete();
				
				//thêm ảnh mới
				user.setImage(imageFile.getOriginalFilename());
				File saveFile = new ClassPathResource("static/image/account").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + imageFile.getOriginalFilename());
				Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				user.setImage(imageFile.getOriginalFilename());
			} else { 
				// dùng ảnh mặc định
				user.setImage(oldUser.getImage());
			}
			user.setEnabled(true);
			this.userService.update(user);
			System.out.println("Cập nhật thành công!!");
			
	
			model.addAttribute("account", user);
			session.setAttribute("message", new MessageResponse("Cập nhật Tài Khoản thành công!!", "success"));
			return "redirect:/admin/account";
				
			} catch (Exception e) {
				System.out.println("Cập nhật Tài Khoản thất bại!!");
				session.setAttribute("message", new MessageResponse("Cập nhật Tài Khoản thất bại!!, vui lòng thử lại!", "danger"));
				String errorMessage = e.getMessage();
				LOGGER.error(errorMessage);
				return "admin/accountEdit";
			}
	}
	
	// đăng ký admin
	@GetMapping("/register-admin")
	public String signup(Model model) {
		model.addAttribute("title", "Đăng Ký Admin");
		model.addAttribute("user", new SignUpDTO());
		return "/admin/signup-admin";
	}

}
