package com.libra.core.services;

import java.util.List;
import java.util.Optional;

import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceAlreadyExistsException;
import com.libra.exception.ResourceNotFoundException;

public interface GenericService<T> {
	
	List<T> findAll();

	Optional<T> findById(Integer id) throws ResourceNotFoundException;
	
	T save(T t) throws BadResourceException, ResourceAlreadyExistsException;
	
	void update(T t) throws BadResourceException, ResourceNotFoundException;
	
	void delete(Integer id);

}
