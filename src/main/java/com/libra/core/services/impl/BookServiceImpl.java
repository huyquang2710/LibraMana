package com.libra.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	@SuppressWarnings("deprecation")
	@Override
	public Book save(Book book) throws BadResourceException, ResourceAlreadyExistsException {
		//kiểm tra id + name chưa
		if(!StringUtils.isEmpty(book.getName())) {
			if(book.getId() !=  null && existsById(book.getId())) {
				throw new ResourceAlreadyExistsException("Sách với id: " + book.getId() + " đã tồn tại");
			}
			return bookRepository.save(book);
		} else {
			BadResourceException exc = new BadResourceException("Lỗi!!. Không thể lưu Sách");
			exc.addErrorMessage("Sách trống hoặc rỗng!!");
			throw exc;
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void update(Book book) throws BadResourceException, ResourceNotFoundException {
		if(!StringUtils.isEmpty(book.getName())) {
			if(!existsById(book.getId())) {
				throw new ResourceNotFoundException("Không tìm thấy id: " + book.getId());
			}
			bookRepository.save(book);
		} else {
			BadResourceException exc = new BadResourceException("Lỗi!!. Không thể lưu Tác Giả");
			exc.addErrorMessage("Tác giả trống hoặc rỗng!!");
			throw exc;
		}
		
	}

	@Override
	public void delete(Integer id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Không tìm thấy id: " + id);
		}
		bookRepository.deleteById(id);
		
	}

	@Override
	public Page<Book> findByPageable(int pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 5);
		return bookRepository.findAll(pageable);
	}

	@Override
	public List<Book> findByNameContaining(String name) {
		return bookRepository.findByNameContaining(name);
	}

	@Override
	public List<Book> searchAuthorByNameLike(String value) {
		
		return bookRepository.findByNameContainingIgnoreCase(value);
	}

}
