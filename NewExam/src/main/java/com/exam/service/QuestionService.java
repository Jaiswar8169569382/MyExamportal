package com.exam.service;

import java.util.List;
import java.util.Set;

import com.exam.entity.Question;
import com.exam.entity.Quiz;

public interface QuestionService {

	public Question saveQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Question getQuestionById(Long id);
	
	public List<Question> getAllQuiz();
	
	public void deleteQuestion(Long id);
	
	public List<Question> getQuestionByQuiz(Quiz quiz);
	
	public Question get(Long quesid);
}
