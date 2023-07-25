package com.exam.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.Category;
import com.exam.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{

	public List<Quiz> getAllQuizByCategory(Category category);
	public List<Quiz> getAllQuizByActive(Boolean active);
	public List<Quiz> getAllQuizByCategoryAndActive(Category categoy,Boolean active);
}
