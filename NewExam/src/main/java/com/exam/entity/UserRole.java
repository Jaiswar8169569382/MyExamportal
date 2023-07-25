package com.exam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long urId;
	@ManyToOne
	private User user;
	@ManyToOne
	private Role role;
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRole(Long urId, User user, Role role) {
		super();
		this.urId = urId;
		this.user = user;
		this.role = role;
	}
	public UserRole(User user, Role role) {
		super();
		this.user = user;
		this.role = role;
	}
	public Long getUrId() {
		return urId;
	}
	public void setUrId(Long urId) {
		this.urId = urId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
