package com.libra.core.repositoies;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.core.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	//search
	public List<Book> findByNameContaining(String name);
}
