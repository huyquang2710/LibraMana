//package com.libra.core.repositoies;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.libra.core.enity.Author;
//
//@Repository
//public interface AuthorRepository extends JpaRepository<Author, Integer>{
//	// search
//	//List<Author> findByNameContaining(String name);
////	@Query("SELECT p FROM author p WHERE "
////			+ "CONCAT(p.id, ' ', p.name, ' ', p.address, ' ', p.image, ' ', p.year, ' ', p.email, ' ', p.createdat, ' ', p.modifiedat)"
////			+ " LIKE %?1%")
////	Page<Author> findAll(String keyword, Pageable pageable);
//}
