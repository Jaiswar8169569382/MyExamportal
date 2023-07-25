package com.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exam.entity.Category;
import com.exam.entity.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired
	private QuizRepository quizRepository;
	
	@Override
	public Quiz saveQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz getQuizById(Long id) {
		// TODO Auto-generated method stub
		return this.quizRepository.findById(id).get();
	}

	@Override
	public List<Quiz> getAllQuiz() {
		// TODO Auto-generated method stub
		return this.quizRepository.findAll();
	}

	@Override
	public void deleteQuiz(Long id) {
		// TODO Auto-generated method stub
		this.quizRepository.deleteById(id);
	}

	@Override
	public List<Quiz> getQuizByCategory(Category category) {
		// TODO Auto-generated method stub
		return this.quizRepository.getAllQuizByCategory(category);
	}

	@Override
	public List<Quiz> getAllActiveQuiz(Boolean active) {
		
		
		return this.quizRepository.getAllQuizByActive(active);
	}

	@Override
	public List<Quiz> getAllCategoryAndActive(Category category, Boolean active) {
		// TODO Auto-generated method stub
		return this.quizRepository.getAllQuizByCategoryAndActive(category, active);
	}

	
}
