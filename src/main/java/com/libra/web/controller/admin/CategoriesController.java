package com.libra.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.core.entities.Category;
import com.libra.core.services.ICategoriesService;

@Controller
@RequestMapping("/admin/category")
public class CategoriesController {
	@Autowired
	private ICategoriesService categoriesService;
	
	// category form
	@GetMapping
	public String getAll(Model model) {
		List<Category> categoryList = categoriesService.findAll();
		
		model.addAttribute("category", categoryList);
		model.addAttribute("title", "Thể Loại Sách");
		return "admin/categories/categoryPage";
	}
}
