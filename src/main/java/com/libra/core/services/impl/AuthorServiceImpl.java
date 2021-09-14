package com.libra.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.core.entities.Author;
import com.libra.core.repositoies.AuthorRepository;
import com.libra.core.services.IAuthorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements IAuthorService{
	
	@Autowired
	private AuthorRepository authorRepo;
	
	private boolean existsById(Integer id) {
		return authorRepo.existsById(id);
	}

	@Override
	public List<Author> findAll() {
		return authorRepo.findAll();
	}

	@Override
	public Optional<Author> findById(Integer id) {
		return authorRepo.findById(id);
	}

	@Override
	public Author save(Author author) {
		return authorRepo.save(author);
	}

	@Override
	public void delete(Integer id) {
		authorRepo.deleteById(id	);
	}

	
}
