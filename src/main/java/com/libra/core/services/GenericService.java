package com.libra.core.services;

import java.util.Optional;

public interface GenericService<T> {
	Iterable<T> findAll();

	Optional<T> findById(Integer id);
	
	T save(T t);
	
	void remove(Integer id);

}
