package com.libra.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.libra.core.entities.Book;
import com.libra.core.services.IBookService;

@Controller
public class BookUserController {
	@Autowired
	private IBookService bookService;
	
	@GetMapping("/book")
	public String bookHome(Model model) {
		
		List<Book> bookList = bookService.findAll();
		
		model.addAttribute("book", bookList);
		model.addAttribute("title", "Sách");
		return "book/bookList";
	}
}
