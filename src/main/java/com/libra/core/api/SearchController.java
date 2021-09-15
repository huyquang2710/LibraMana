package com.libra.core.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.libra.core.entities.Author;
import com.libra.core.entities.Book;
import com.libra.core.entities.Publisher;
import com.libra.core.services.IAuthorService;
import com.libra.core.services.IBookService;
import com.libra.core.services.IPublisherService;

@RestController
public class SearchController {
	@Autowired
	private IAuthorService authorService;
	@Autowired
	private IPublisherService publisherService;
	@Autowired
	private IBookService bookService;
//	// author
//	@GetMapping("/search/author/{query}")
//	public ResponseEntity<?> searchAuthor(@PathVariable("query") String query) {
//		System.out.println(query);
//		
//		List<Author> authors = this.authorService.findByNameContaining(query);
//		return ResponseEntity.ok(authors);
//	}
//	//publisher
//	@GetMapping("/search/publisher/{query}")
//	public ResponseEntity<?> searchPublisher(@PathVariable("query") String query) {
//		System.out.println(query);
//		
//		List<Publisher> publisher = this.publisherService.findByNameContaining(query);
//		return ResponseEntity.ok(publisher);
//	}
//	//book
//	@GetMapping("/search/book/{query}")
//	public ResponseEntity<?> searchBook(@PathVariable("query") String query) {
//		System.out.println(query);
//		
//		List<Book> book = this.bookService.findByNameContaining(query);
//		return ResponseEntity.ok(book);
//		}
	
	// author
	@RequestMapping(value = "/author/search", method = RequestMethod.GET)
	public ModelAndView searchAuthor(@RequestParam("value") String value) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/author/searchAuthor");
		List<Author> authors = authorService.searchAuthorByNameLike(value);
		mv.addObject("author", authors);
		return mv;
	}
}
