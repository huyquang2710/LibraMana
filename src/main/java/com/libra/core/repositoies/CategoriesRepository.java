package com.libra.core.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.core.entities.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer>{

}
