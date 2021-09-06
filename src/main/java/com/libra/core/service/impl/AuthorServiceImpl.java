package com.libra.core.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.libra.core.enity.Author;
import com.libra.core.repository.AuthorPagingAndSortingRepository;
import com.libra.core.repository.AuthorRepository;
import com.libra.core.service.IAuthorService;

@Service
public class AuthorServiceImpl implements IAuthorService{
	
	@Autowired
	private AuthorRepository authorRepo;
	@Autowired
	private AuthorPagingAndSortingRepository authorPaging;
	
	@Override
	public Iterable<Author> findAll() {
		return authorRepo.findAll();
	}
	//paging
//	@Override
//	public Page<Author> findAll(Pageable pageable) {
//		
//		return authorRepo.findAll(pageable);
//	}
//	
	// Sort
//	@Override
//	public List<Author> findAll(Sort sort) {
//		return authorRepo.findAll(sort);
//	}
//	//paging
//	@Override
//	public Page<Author> findAll(Pageable pageable) {
//		return authorRepo.findAll(pageable);
//	}

////	@Override
//	
	//pagenable
//	public Page<Author> findPaginted(int pageNo, int pageSize, String sortField, String sortDirection) {
//		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
//		
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//		return this.authorRepo.findAll(pageable);
//	}
	//pagenable and filter and search
	public Page<Author> findPaginted(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		if(keyword != null) {
			return authorPaging.findAll(keyword, pageable);
		}
		return this.authorRepo.findAll(pageable);
	}
	//pagenable
	public Page<Author> findPaginted(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.authorRepo.findAll(pageable);
	}
//	
	//findByid
	@Override
	public Optional<Author> findById(Integer id) {
		return authorRepo.findById(id);
	}
	//save
	@Override
	public Author save(Author author) {
		return authorRepo.save(author);
	}
	//delete
	@Override
	public void remove(Integer id) {
		
		authorRepo.deleteById(id);
	}
	//search by name
//	@Override
//	public List<Author> findByNameContaining(String name) {
//		
//		return authorRepo.findByNameContaining(name);
//	}
}
