package com.exam.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.exam.entity.Request;
import com.exam.entity.Response;
import com.exam.helper.JwtUtil;
import com.exam.helper.UserFoundException;
import com.exam.serviceImpl.UserDetailServiceImpl;
import com.exam.serviceImpl.UserServiceImpl;

@RestController
@CrossOrigin("*")
public class TokenGeneration {

	@Autowired
	private AuthenticationManager authenticateManager;
	
	@Autowired
	private UserDetailServiceImpl userDetail;
	@Autowired
	 private JwtUtil jwtutil;
	
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody Request request)
	{
	try
	{
		
		authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
	}
	
	catch(UserFoundException e)
	{
		throw new UserFoundException("User not found with register username and password");
	}
	catch(DisabledException e)
	{
		throw new DisabledException("User Disabled");
	}
	
    catch(BadCredentialsException e)
	{
		throw new BadCredentialsException("Bad credential exception");
	}
	
	UserDetails userDetail=this.userDetail.loadUserByUsername(request.getUsername());
	String token=this.jwtutil.generateToken(userDetail);
	System.out.println("Token generated successfully : "+token);
		return ResponseEntity.ok(new Response(token));
	}
		
	@GetMapping("/verify")
	@Transactional
	public ModelAndView verifyAccount(@Param("code") String code , Model model)
	{
		boolean verify=this.userService.verifyAccount(code);
		String pageTitle=verify?"User Verification Success":"User Already Verified";
		model.addAttribute("title",pageTitle);
		 ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName(verify?"success.html":"failed.html");
		return modelAndView;
		
	
	}
	}

