package com.exam.helper;

public class UserFoundException extends RuntimeException{

	public UserFoundException()
	{
		super("User  found in database");
	}
	
	public UserFoundException(String message)
	{
		super(message);
	}
}
