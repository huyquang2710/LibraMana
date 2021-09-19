package com.libra.core.repositoies;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libra.core.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	//search
	public List<Book> findByNameContaining(String name);
	
	List<Book> findByNameContainingIgnoreCase(String name);
	
	@Query(value = "SELECT b.* FROM book b INNER JOIN category c on b.category = c.id WHERE c.name = ?1", nativeQuery = true)
	List<Book> customQueryToFindListOfBooks(String category);
}
   