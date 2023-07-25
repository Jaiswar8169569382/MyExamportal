package com.exam.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qId;
	
	private String title;
	
	@Column(length = 1000)
	private String description;
	
	private String noOfQuestion;
	
	private String maxMark;
	
	private boolean active=true;
	
	private String image;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "quiz")
	private List<Question> question= new ArrayList<>();

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quiz(Long qId, String title, String description, String noOfQuestion, String maxMark, boolean active,
			String image, Category category, List<Question> question) {
		super();
		this.qId = qId;
		this.title = title;
		this.description = description;
		this.noOfQuestion = noOfQuestion;
		this.maxMark = maxMark;
		this.active = active;
		this.image = image;
		this.category = category;
		this.question = question;
	}

	public Quiz(String title, String description, String noOfQuestion, String maxMark, boolean active, String image,
			Category category, List<Question> question) {
		super();
		this.title = title;
		this.description = description;
		this.noOfQuestion = noOfQuestion;
		this.maxMark = maxMark;
		this.active = active;
		this.image = image;
		this.category = category;
		this.question = question;
	}

	public Long getqId() {
		return qId;
	}

	public void setqId(Long qId) {
		this.qId = qId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNoOfQuestion() {
		return noOfQuestion;
	}

	public void setNoOfQuestion(String noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}

	public String getMaxMark() {
		return maxMark;
	}

	public void setMaxMark(String maxMark) {
		this.maxMark = maxMark;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	
}
