package com.exam.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {

	@Id
	private Long rId;
	private String rName;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
	private List<UserRole> userRole= new ArrayList<>();

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Long rId, String rName, List<UserRole> userRole) {
		super();
		this.rId = rId;
		this.rName = rName;
		this.userRole = userRole;
	}

	public Role(String rName, List<UserRole> userRole) {
		super();
		this.rName = rName;
		this.userRole = userRole;
	}

	public Long getrId() {
		return rId;
	}

	public void setrId(Long rId) {
		this.rId = rId;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	
}
