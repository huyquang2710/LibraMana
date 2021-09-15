package com.libra.core.services;

import java.util.List;

import com.libra.core.entities.Author;

public interface IAuthorService extends GenericService<Author> {

	List<Author> searchAuthorByNameLike(String value);
	
}
