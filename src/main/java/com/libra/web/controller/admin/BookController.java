package com.libra.web.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libra.core.entities.Author;
import com.libra.core.entities.Book;
import com.libra.core.entities.Category;
import com.libra.core.entities.Publisher;
import com.libra.core.services.IAuthorService;
import com.libra.core.services.IBookService;
import com.libra.core.services.ICategoriesService;
import com.libra.core.services.IPublisherService;
import com.libra.exception.ResourceNotFoundException;
import com.libra.web.message.MessageResponse;

@Controller
@RequestMapping("/admin/book")
public class BookController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IBookService bookService;
	@Autowired
	private IAuthorService authorService;
	@Autowired
	private IPublisherService publisherService;
	@Autowired
	private ICategoriesService categoryService;
	@Autowired
	private ModelMapper modelMapper;
	
	// findAll
	@GetMapping
	public String getAllDetail(Model model, HttpSession session) {
		List<Book> bookList = bookService.findAll();
		
		//kiểm tra book có rỗng ko
		if(bookList.isEmpty()) {
			session.setAttribute("message", new MessageResponse("Chưa có cuốn sách nào","notify"));
			return "admin/book/bookPage";
		}
		model.addAttribute("book", bookList);
		model.addAttribute("title", "Sách");
		return "admin/book/bookPage";
	}
	//findById
	@GetMapping("/findById/{id}")
	public String findById(@PathVariable("id") Integer id, Model model, HttpSession session) throws ResourceNotFoundException {
		// kiểm tra id có giá trị ko
		System.out.println("id: " + id);
		
		//lấy danh sách tên tác giả
		List<Author> authorList = authorService.findAll();
		//lấy danh sách tên nhà xuất bản
		List<Publisher> publisherList = publisherService.findAll();
		//lấy danh sách tên thể loại
		List<Category> categoryList = categoryService.findAll();
		
		model.addAttribute("author", authorList);
		model.addAttribute("publisher", publisherList);
		model.addAttribute("category", categoryList);
		
		if(id != null) {
			Optional<Book> bookOtp = this.bookService.findById(id);
			Book book = bookOtp.get();
			
			model.addAttribute("book", book);
			model.addAttribute("title", "Chi Tiết Sách");
			return "admin/book/bookDetail";
		}
		session.setAttribute("message", new MessageResponse("Không tìm thấy Sách!!, vui lòng thử lại!", "danger"));
		return "admin/book/bookPage";
	}
	
}
