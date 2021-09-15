package com.libra.core.repositoies;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.core.entities.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	//search
	public List<Author> findByNameContaining(String name);
	
	List<Author> findByNameContainingIgnoreCase(String name);
}
