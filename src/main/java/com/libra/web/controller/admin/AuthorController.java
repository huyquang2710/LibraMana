package com.libra.web.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.core.entities.Author;
import com.libra.core.services.IAuthorService;
import com.libra.web.dto.AuthorDTO;

@Controller
@RequestMapping("/admin/author")
public class AuthorController {
	
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
	public String authorForm(Model model) {
		
		model.addAttribute("title", "Thêm Tác Giả");
		return "admin/author/authorNew";
	}
	// new action

	
	
}
