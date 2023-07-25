package com.exam.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.Question;
import com.exam.entity.Quiz;

public interface QuestionRepo extends JpaRepository<Question, Long>{

	public List<Question> getQuestionByQuiz(Quiz quiz);
	
	
}
