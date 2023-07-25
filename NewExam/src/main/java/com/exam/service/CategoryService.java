package com.exam.service;

import java.util.List;

import com.exam.entity.Category;

public interface CategoryService {

	public Category saveCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Category getCategoryById(Long id);
	
	public List<Category>  GetAllCategory();
	
	public void deleteCategory(Long id);
	
}
