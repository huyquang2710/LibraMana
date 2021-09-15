package com.libra.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.libra.core.entities.Book;
import com.libra.core.repositoies.BookRepository;
import com.libra.core.services.IBookService;
import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceAlreadyExistsException;
import com.libra.exception.ResourceNotFoundException;

@Service
public class BookServiceImpl implements IBookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	private boolean existsById(Integer id) {
		return bookRepository.existsById(id);
	}
	
	//findAll
	@Override
	public List<Book> findAll() {
		
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> findById(Integer id) throws ResourceNotFoundException {
		Book book = bookRepository.findById(id).get();
		if(book == null) {
			throw new ResourceNotFoundException("Không tìm thấy id: " + id);
		}
		Optional<Book> bookOpt = Optional.ofNullable(book);
		return bookOpt;
	}

	@Override
	public Book save(Book t) throws BadResourceException, ResourceAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Book t) throws BadResourceException, ResourceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Book> findByPageable(int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findByNameContaining(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
