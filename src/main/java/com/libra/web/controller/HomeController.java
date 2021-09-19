package com.libra.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.core.entities.Book;
import com.libra.core.services.IBookService;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private IBookService bookService;
	
	@GetMapping
	public String home(Model model) {
		model.addAttribute("title", "Trang Chủ");
		return "home/home";
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<Book> findBookByCategory(@PathVariable("category") String category, Model model) {
		Book bookByCategory = (Book) bookService.findBookByCategory(category);
		if(bookByCategory == null) {
			System.out.println("User with id " + bookByCategory + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(bookByCategory, HttpStatus.OK);
	}
	
}
