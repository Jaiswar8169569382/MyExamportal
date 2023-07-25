 package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Category;
import com.exam.repo.CategoryRepo;
import com.exam.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService  categoryService;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@PostMapping("/")
	public Category saveCategory(@RequestBody Category category)
	{
		return this.categoryService.saveCategory(category);
	}
	
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category)
	{
		return this.categoryService.updateCategory(category);
	}
	
	@GetMapping("/{id}")
	public Category getCategoryByIds(@PathVariable("id") Long id)
	{
	 	return this.categoryService.getCategoryById(id);
	}
	
	@GetMapping("/")
	public List<Category> getAllCategory()
	{
		return this.categoryService.GetAllCategory();
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable("id") Long id)
	{
		this.categoryService.deleteCategory(id);
	}
	
	@GetMapping("/count")
	public ResponseEntity<?> getAllCount()
	{
		return ResponseEntity.ok(categoryRepo.count());
	}
}
