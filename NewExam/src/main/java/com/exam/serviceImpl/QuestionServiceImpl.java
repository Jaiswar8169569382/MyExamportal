package com.exam.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.Question;
import com.exam.entity.Quiz;
import com.exam.repo.QuestionRepo;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepo questionRepository;
	
	
	
	@Override
	public Question saveQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(question);
	}

	@Override
	public Question getQuestionById(Long id) {
		// TODO Auto-generated method stub
		return this.questionRepository.findById(id).get();
	}

	@Override
	public List<Question> getAllQuiz() {
		// TODO Auto-generated method stub
		return this.questionRepository.findAll();
	}

	@Override
	public void deleteQuestion(Long id) {
		// TODO Auto-generated method stub
		this.questionRepository.deleteById(id);
	}

	@Override
	public List<Question> getQuestionByQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		
	List<Question> listQuestions= questionRepository.getQuestionByQuiz(quiz);
		
	   if(listQuestions.size()>Integer.parseInt(quiz.getNoOfQuestion()))
	   {
		  listQuestions=listQuestions.subList(0, Integer.parseInt(quiz.getNoOfQuestion()));
	   }
		
		Collections.shuffle(listQuestions);
		
		return listQuestions;
	}

	@Override
	public Question get(Long quesid) {
		Question question=this.questionRepository.getOne(quesid);
		
		return question;
	}

	
}
