package com.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.Category;
import com.exam.repo.CategoryRepo;
import com.exam.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepository;
	
	@Override
	public Category saveCategory(Category category) {
		// TODO Auto-generated method stub
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return this.categoryRepository.save(category);
	}

	@Override
	public Category getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return this.categoryRepository.findById(id).get();
	}

	@Override
	public List<Category> GetAllCategory() {
		// TODO Auto-generated method stub
		return this.categoryRepository.findAll();
	}

	@Override
	public void deleteCategory(Long id) {
		// TODO Auto-generated method stub
		this.categoryRepository.deleteById(id);
	}

	
}
