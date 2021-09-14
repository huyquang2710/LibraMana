package com.libra.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.libra.core.services.ICategoriesService;

@Controller
public class CategoriesController {
	@Autowired
	private ICategoriesService categoriesService;
	
	// category form
	public String getAll(Model model) {
		
		model.addAttribute("title", "Thể Loại Sácg");
		return "";
	}
}
