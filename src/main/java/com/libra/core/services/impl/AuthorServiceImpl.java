package com.libra.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.libra.core.entities.Author;
import com.libra.core.repositoies.AuthorRepository;
import com.libra.core.services.IAuthorService;
import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceAlreadyExistsException;
import com.libra.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements IAuthorService{
	
	@Autowired
	private AuthorRepository authorRepo;
	
	private boolean existsById(Integer id) {
		return authorRepo.existsById(id);
	}
	//find all
	@Override
	public List<Author> findAll() {
		return authorRepo.findAll();
	}
	// pagination	
	@Override
	public Page<Author> findByPageable(int pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 5);
		return authorRepo.findAll(pageable);
	}


	@Override
	public Optional<Author> findById(Integer id) throws ResourceNotFoundException {
		Author author = authorRepo.findById(id).get();
		if(author == null) {
			throw new ResourceNotFoundException("Không tìm thấy id: " + id);
		}
		Optional<Author> authorOpt = Optional.ofNullable(author);
		return authorOpt;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Author save(Author author) throws BadResourceException, ResourceAlreadyExistsException{
		
		// kiểm tra id + name tồn tại
		if(!StringUtils.isEmpty(author.getName())) {
			if(author.getId() != null && existsById(author.getId())) {
				throw new ResourceAlreadyExistsException("Tác giả với id: " + author.getId() + " đã tồn tại");
			}
			return authorRepo.save(author);
		} else {
			BadResourceException exc = new BadResourceException("Lỗi!!. Không thể lưu Tác Giả");
			exc.addErrorMessage("Tác giả trống hoặc rỗng!!");
			throw exc;
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void update(Author author) throws BadResourceException, ResourceNotFoundException {
		if(!StringUtils.isEmpty(author.getName())) {
			if(!existsById(author.getId())) {
				throw new ResourceNotFoundException("Không tìm thấy id: " + author.getId());
			}
			authorRepo.save(author);
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
		authorRepo.deleteById(id);
	}



	
}
