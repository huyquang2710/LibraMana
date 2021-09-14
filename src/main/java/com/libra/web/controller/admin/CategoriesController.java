package com.libra.web.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.core.entities.Category;
import com.libra.core.services.ICategoriesService;
import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceAlreadyExistsException;
import com.libra.exception.ResourceNotFoundException;
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
	public String categoryNew(@Valid @ModelAttribute("category") Category category,BindingResult bindingResult, Model model, HttpSession session) {
		
		try {
			if(bindingResult.hasErrors()) {
				System.out.println("Author: " + bindingResult.toString());
				return "admin/categories/categoryNew";
			}
			categoriesService.save(category);
			
			model.addAttribute("category", category);
			model.addAttribute("title", "Thêm Thể Loại");
			session.setAttribute("message", new MessageResponse("Thêm Thể Loại thành công!!", "success"));
			return "redirect:/admin/category";
		} catch (BadResourceException | ResourceAlreadyExistsException e) {
			System.out.println("Thêm Thể Loại thất bại!!");
			session.setAttribute("message", new MessageResponse("Thêm Thể Loại thất bại!!, vui lòng thử lại!", "danger"));
			String errorMessage = e.getMessage();
			LOGGER.error(errorMessage);
			return "admin/categories/categoryNew";
		}
	}
	
	// findById
	@GetMapping("/findById/{id}")
	public String findById(@PathVariable("id") Integer id, Model model, HttpSession session) {
		try {
			if(id != null) {
				Optional<Category> categoryOtp = categoriesService.findById(id);
				Category category = categoryOtp.get();
				
				model.addAttribute("category", category);
				return "admin/categories/categoryEdit";		
			}
			session.setAttribute("message", new MessageResponse("Không tìm thấy Thể Loại!!, vui lòng thử lại!", "danger"));
			return "admin/categories/categoryPage";
			
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		return "admin/categories/categoryEdit";		
	}
	
	// update
	@PostMapping("/update")
	public String categoryUpdate(@Valid @ModelAttribute("category") Category category,BindingResult bindingResult, Model model, HttpSession session) {
		
		try {
			if(bindingResult.hasErrors()) {
				System.out.println("Author: " + bindingResult.toString());
				return "admin/categories/categoryNew";
			}
			categoriesService.update(category);
			
			model.addAttribute("category", category);
			model.addAttribute("title", "Cập Nhật Thể Loại");
			session.setAttribute("message", new MessageResponse("Cập Nhật Thể Loại thành công!!", "success"));
			return "redirect:/admin/category";
		} catch (BadResourceException | ResourceNotFoundException e) {
			System.out.println("Thêm Thể Loại thất bại!!");
			session.setAttribute("message", new MessageResponse("Cập Nhật Thể Loại thất bại!!, vui lòng thử lại!", "danger"));
			String errorMessage = e.getMessage();
			LOGGER.error(errorMessage);
			return "admin/categories/categoryNew";
		}
	}
	// delete
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer id, HttpSession session) {
		try {
			categoriesService.delete(id);
			
			session.setAttribute("message", new MessageResponse("Xóa Thành Công!!,!", "success"));
			return "redirect:/admin/category";
		} catch (ResourceNotFoundException e) {
			String errorMessage = e.getMessage();
	        LOGGER.error(errorMessage);
	        session.setAttribute("message", new MessageResponse("Xóa Thất Bại!!,!", "danger"));
	        return "admin/categories/categoryPage";
		}
	}
}
