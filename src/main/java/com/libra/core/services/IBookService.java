package com.libra.core.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.libra.core.entities.Book;

public interface IBookService extends GenericService<Book>{

	List<Book> searchBookByNameLike(String value);

	Page<Book> findByPageableBookHome(int pageNo);
	
	List<Book> findBookByCategory(String category);

}
