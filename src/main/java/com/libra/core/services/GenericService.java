package com.libra.core.services;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {
	
	List<T> findAll();

	Optional<T> findById(Integer id);
	
	T save(T t);
	
	void delete(Integer id);

}
