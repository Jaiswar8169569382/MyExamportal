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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cid;
	private String title;
	@Column(length = 1000)
	private String description;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "category")
	private List<Quiz> quiz= new ArrayList<>();
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(Long cid, String title, String description, List<Quiz> quiz) {
		super();
		this.cid = cid;
		this.title = title;
		this.description = description;
		this.quiz = quiz;
	}
	public Category(String title, String description, List<Quiz> quiz) {
		super();
		this.title = title;
		this.description = description;
		this.quiz = quiz;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
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
	public List<Quiz> getQuiz() {
		return quiz;
	}
	public void setQuiz(List<Quiz> quiz) {
		this.quiz = quiz;
	}
	
	
	
	
}
