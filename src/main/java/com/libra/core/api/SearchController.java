package com.libra.core.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.libra.core.entities.Author;
import com.libra.core.services.IAuthorService;

@RestController
public class SearchController {
	@Autowired
	private IAuthorService authorService;
	
	@GetMapping("/search/{query}")
	public ResponseEntity<?> searchAuthor(@PathVariable("query") String query) {
		System.out.println(query);
		
		List<Author> authors = this.authorService.findByNameContaining(query);
		return ResponseEntity.ok(authors);
	}
}
