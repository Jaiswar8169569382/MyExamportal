package com.exam.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exam.helper.UserFoundException;
import com.exam.helper.UserNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<Map<String, Object>> userFoundException(UserFoundException ex)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("content", ex.getMessage());
		
		
		return ResponseEntity.ok(map);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String, Object>> userNotFoundException(UserNotFoundException ex)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("content", ex.getMessage());
		
		
		return ResponseEntity.ok(map);
	}
}
