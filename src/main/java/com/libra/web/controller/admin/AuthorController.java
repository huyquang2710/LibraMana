package com.libra.web.controller.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.libra.core.entities.Author;
import com.libra.core.services.IAuthorService;
import com.libra.web.dto.AuthorDTO;
import com.libra.web.message.MessageResponse;

@Controller
@RequestMapping("/admin/author")
public class AuthorController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IAuthorService authorService;
	@Autowired
	private ModelMapper modelMapper;
	
	// get all
	@GetMapping
	public String authorPage(Model model) {
		List<Author> authorList = authorService.findAll();
		
		model.addAttribute("author", authorList);
		model.addAttribute("title", "Tác Giả");
		return "admin/author/authorPage";
	}
	
	// new form
	@GetMapping("/new")
	public String authorForm(@ModelAttribute AuthorDTO authorDTO , Model model) {
		
		model.addAttribute("author", authorDTO);
		model.addAttribute("title", "Thêm Tác Giả");
		return "admin/author/authorNew";
	}
	// new action
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newAuthor(
			@Valid @ModelAttribute("author") AuthorDTO authorDTO ,
		BindingResult bindingResult,
		@RequestParam("imageFile") MultipartFile fileImage,
		Model model,
		HttpSession session ) {
		
		try {
			// Kiểm tra dữ liệu hợp lệ
			if(bindingResult.hasErrors()) {
				System.out.println("Author: " + bindingResult.toString());
				//model.addAttribute("author", authorDTO);
				return "admin/author/authorNew";
			}
			if(fileImage.isEmpty()) {
				System.out.println("File ảnh trống !!");
				
				//set ảnh mặc định
				authorDTO.setImage("avatar.png");
			} else {
				//set anh
				authorDTO.setImage(fileImage.getOriginalFilename());
				File saveFile = new ClassPathResource("static/image/author").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileImage.getOriginalFilename());
				
				Files.copy(fileImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Thêm ảnh thành công!!");
			}
			Author author = new Author();
			//chuuyen kieu thanh entity
			modelMapper.map(authorDTO, author);
			this.authorService.save(author);
			
			System.out.println("Thêm Tác giả thành công!!");
			
			model.addAttribute("author", authorDTO);
			session.setAttribute("message", new MessageResponse("Thêm Tác Giả thành công!!", "success"));
			return "redirect:/admin/author";
		} catch (Exception e) {
			System.out.println("Thêm Tác giả thất bại!!");
			session.setAttribute("message", new MessageResponse("Thêm Tác Giả thất bại!!, vui lòng thử lại!", "danger"));
			String errorMessage = e.getMessage();
			LOGGER.error(errorMessage);
			return "admin/author/authorNew";
		}
	}
	
	
}
