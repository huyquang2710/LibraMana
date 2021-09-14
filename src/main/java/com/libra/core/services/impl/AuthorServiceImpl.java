package com.libra.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.libra.core.entities.Author;
import com.libra.core.repositoies.AuthorRepository;
import com.libra.core.services.IAuthorService;
import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceAlreadyExistsException;

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

	@Override
	public void delete(Integer id) {
		authorRepo.deleteById(id	);
	}

	
}
