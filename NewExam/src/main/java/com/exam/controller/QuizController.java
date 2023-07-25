package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.exam.entity.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizservice;
	
	@Autowired
	private QuizRepository quizRepository;
	@PostMapping("/")
	public Quiz saveQuiz(@RequestBody Quiz quiz)
	{
		return this.quizservice.saveQuiz(quiz);
	}
	
	@PutMapping("/")
	public Quiz updateQuiz(@RequestBody Quiz quiz)
	{
		return this.quizservice.updateQuiz(quiz);
	}
	
	@GetMapping("/{id}")
	public Quiz getQuizById(@PathVariable("id") Long id)
	{
		
		return this.quizservice.getQuizById(id);
	}
	
	@GetMapping("/")
	public List<Quiz> getAllQuiz()
	{
		return this.quizservice.getAllQuiz();
	}
	
	@DeleteMapping("/{id}")
	public void deleteQuiz(@PathVariable("id") Long id)
	{
		this.quizservice.deleteQuiz(id);
	}
	
	@GetMapping("/categories/{id}")
	public List<Quiz> getQuizByCategory(@PathVariable("id") Long id)
	{
		Category category = new Category();
		category.setCid(id);
		
		
		List<Quiz> quiz=this.quizservice.getQuizByCategory(category);
	
		return quiz;
	}
	
	@GetMapping("/active")
	public List<Quiz> getAllActiveQuiz()
	{
		return this.quizservice.getAllActiveQuiz(true);
	}
	
	@GetMapping("/category/active/{id}")
	public List<Quiz> getAllActiveAndCategoryQuiz(@PathVariable("id") Long id)
	{
		Category category = new Category();
		category.setCid(id);
		
		return this.quizservice.getAllCategoryAndActive(category, true);
	}
	
	@GetMapping("/count")
	public ResponseEntity<?> getAllCount()
	{
		return ResponseEntity.ok(quizRepository.count());
	}
}
