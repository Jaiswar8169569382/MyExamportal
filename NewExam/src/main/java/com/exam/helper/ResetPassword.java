package com.exam.helper;

public class ResetPassword {

	private String oldpassword;
	private String newpassword;
	public ResetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResetPassword(String oldpassword, String newpassword) {
		super();
		this.oldpassword = oldpassword;
		this.newpassword = newpassword;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
	
}
