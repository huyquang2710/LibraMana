package com.libra.web.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.core.entities.Category;
import com.libra.core.services.ICategoriesService;
import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceAlreadyExistsException;
import com.libra.web.message.MessageResponse;

@Controller
@RequestMapping("/admin/category")
public class CategoriesController {
	@Autowired
	private ICategoriesService categoriesService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	// category form
	@GetMapping
	public String getAll(Model model) {
		List<Category> categoryList = categoriesService.findAll();
		
		model.addAttribute("category", categoryList);
		model.addAttribute("title", "Thể Loại Sách");
		return "admin/categories/categoryPage";
	}
	//category add form
	@GetMapping("/new")
	public String categoryForm(Model model, @ModelAttribute("category") Category category) {
		
		model.addAttribute("category", category);
		model.addAttribute("title", "Thêm Thể Loại");
		return "admin/categories/categoryNew";
	}
	// category new action
	@PostMapping("/new")
	public String categoryNew(Model model, @ModelAttribute("category") Category category, HttpSession session) {
		
		try {
			categoriesService.save(category);
			
			model.addAttribute("category", category);
			model.addAttribute("title", "Thêm Thể Loại");
			return "redirect:/admin/category";
		} catch (BadResourceException | ResourceAlreadyExistsException e) {
			System.out.println("Thêm Thể Loại thất bại!!");
			session.setAttribute("message", new MessageResponse("Thêm Thể Loại thất bại!!, vui lòng thử lại!", "danger"));
			String errorMessage = e.getMessage();
			LOGGER.error(errorMessage);
			return "admin/category/categoryNew";
		}
		
		
	}
}
