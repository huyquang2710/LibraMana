package com.libra.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.core.entities.Category;
import com.libra.core.services.ICategoriesService;

@Controller
@RequestMapping("/category")
public class CategoryUserController {
	
	@Autowired
	private ICategoriesService categoriesService;
	
	@GetMapping
	public String getAllCategory(Model model) {
		List<Category> categoryList =  categoriesService.findAll();
		
		model.addAttribute("category", categoryList);
		return "home/category";
	}
}
