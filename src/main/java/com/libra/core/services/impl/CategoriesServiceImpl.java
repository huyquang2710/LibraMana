package com.libra.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.libra.core.entities.Category;
import com.libra.core.repositoies.CategoriesRepository;
import com.libra.core.services.ICategoriesService;
import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceAlreadyExistsException;
import com.libra.exception.ResourceNotFoundException;

@Service
public class CategoriesServiceImpl implements ICategoriesService{
	
	@Autowired
	private CategoriesRepository categoryRepo;
	
	@Override
	public List<Category> findAll() {
		
		return categoryRepo.findAll();
	}

	@Override
	public Optional<Category> findById(Integer id) throws ResourceNotFoundException {
		
		return categoryRepo.findById(id);
	}

	@Override
	public Category save(Category category) throws BadResourceException, ResourceAlreadyExistsException {
	
		return categoryRepo.save(category);
	}

	@Override
	public void update(Category category) throws BadResourceException, ResourceNotFoundException {
		categoryRepo.save(category);
	}

	@Override
	public void delete(Integer id) throws ResourceNotFoundException {
		categoryRepo.deleteById(id);
	}

	@Override
	public Page<Category> findByPageable(int pageNo) {
		
		return null;
	}

	@Override
	public List<Category> findByNameContaining(String name) {
		
		return null;
	}

}
