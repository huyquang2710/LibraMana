package com.libra.core.repositoies;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.core.entities.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
	//search
	public List<Publisher> findByNameContaining(String name);
	
	List<Publisher> findByNameContainingIgnoreCase(String name);
}
