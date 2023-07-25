package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

}
