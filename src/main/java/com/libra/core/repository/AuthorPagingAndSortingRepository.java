package com.libra.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.libra.core.enity.Author;

public interface AuthorPagingAndSortingRepository extends PagingAndSortingRepository<Author, Integer>{
	
	@Query("SELECT p FROM Author p WHERE CONCAT( p.id, ' ', p.name, ' ', p.address, ' ', p.year, ' ', p.phone) LIKE %?1%")
	public Page<Author> findAll(String keywork, Pageable pageable);
//	

}
