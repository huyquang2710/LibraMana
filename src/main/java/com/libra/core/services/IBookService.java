package com.libra.core.services;

import java.util.List;

import com.libra.core.entities.Book;

public interface IBookService extends GenericService<Book>{

	List<Book> searchBookByNameLike(String value);

}
