package com.exam.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.exam.entity.Question;
import com.exam.entity.Quiz;
import com.exam.repo.QuestionRepo;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionRepo questionRepo;
	
	@Autowired
	private QuizService quizService;
	@PostMapping("/")
	public Question saveQuestion(@RequestBody Question question)
	{
		return this.questionService.saveQuestion(question);
	}
	
	@PutMapping("/")
	public Question updateQuestion(@RequestBody Question question)
	{
		return this.questionService.updateQuestion(question);
	}
	
	@GetMapping("/{id}")
	public Question getQuestionById(@PathVariable("id") Long id)
	{
		return this.questionService.getQuestionById(id);
	}
	
	@GetMapping("/")
	public List<Question> getAllQuestion()
	{
		return this.questionService.getAllQuiz();
	}
	
	
	@GetMapping("/quiz/{id}")
	public List<Question> getQuestionByQuiz(@PathVariable("id") Long id)
	{ 
		Quiz quiz=quizService.getQuizById(id);
		
		return this.questionService.getQuestionByQuiz(quiz);
	}
	
	@DeleteMapping("/{id}")
	public void deleteQuestion(@PathVariable("id") Long id)
	{
		this.questionService.deleteQuestion(id);
	}
	
	@GetMapping("/count")
	public ResponseEntity<?> getAllCount()
	{
		return ResponseEntity.ok(questionRepo.count());
	}
	
	@PostMapping("/evalquiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> question)
	{
		double singleMarks=0;
		double singleMarks2=0;
		double singleMark=0;
		int totalMark=0;
		Integer correctAnswer=0;
		double marksGot=0;
		double marksGot2=0;
		Integer attempt=0;
	   for(Question ques:question)
	   {
		  Question questions=this.questionService.getQuestionById(ques.getQuesId());
		
		 // Question questions=this.questionService.get(ques.getQuesId());
   if(questions.getAnswar().equals(ques.getGivenAnswer()))
   {
	   correctAnswer++;
	    singleMark=Double.parseDouble(question.get(0).getQuiz().getMaxMark())/question.size();
	   marksGot+=singleMark;
	    marksGot2 = (int)(Math.round(marksGot * 100))/100.0;
	  
   }
   
   if(ques.getGivenAnswer()!=null)
   {
	   attempt++;
   
   }	
     totalMark=Integer.parseInt(question.get(0).getQuiz().getMaxMark());
      singleMarks=Double.parseDouble(question.get(0).getQuiz().getMaxMark())/question.size();
	  
      singleMarks2 = (int)(Math.round(singleMarks * 100))/100.0;
	
	}
	 
	   
	   
	   Map<String,Object> map=Map.of("marksGot",marksGot,"correctAnswar",
			   correctAnswer,"attempted",attempt,"single",singleMarks2,"total",totalMark);
		return ResponseEntity.ok(map);
}
}