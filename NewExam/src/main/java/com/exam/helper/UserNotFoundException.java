package com.exam.helper;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException()
	{
		super("User not found with this username and password");
	}
	
	public UserNotFoundException(String message)
	{
		super(message);
	}
}
