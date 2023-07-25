package com.exam.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.exam.entity.Category;
import com.exam.entity.Quiz;

public interface QuizService {
	
	public Quiz saveQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	
	public Quiz getQuizById(Long id);
	
	public List<Quiz> getAllQuiz();
	
	public void deleteQuiz(Long id);
	
	public List<Quiz> getQuizByCategory(Category category);
	
	public List<Quiz> getAllActiveQuiz(Boolean active);
	
	public List<Quiz> getAllCategoryAndActive(Category category,Boolean active);

}
