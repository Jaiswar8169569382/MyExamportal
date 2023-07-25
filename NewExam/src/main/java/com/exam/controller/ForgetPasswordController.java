package com.exam.controller;


import java.security.Principal;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.exam.entity.User;
import com.exam.helper.GenerateOtp;
import com.exam.helper.MyEmail;
import com.exam.helper.MyTokens;
import com.exam.helper.Validate;
import com.exam.repo.UserRepo;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.util.JSONPObject;


@RestController
@RequestMapping("/forget")
@CrossOrigin("*")
public class ForgetPasswordController {

	@Autowired
	private BCryptPasswordEncoder encoder;
	HttpSession sessions=null;
	@Autowired
	private UserRepo userRepository;
	
	@PostMapping("/email")
	@ResponseBody
	public ResponseEntity<?> sendOtp(@RequestBody MyEmail myEmail,HttpSession session)
	{
		String email=myEmail.getEmail();
		Format  format=new DecimalFormat("000000");
		String otp=format.format(new Random().nextInt(999999));
		String message="Don't Share Your Otp with anyone Your Otp is : "+otp;
		String subject="My Quiz Portal Otp";
		String to=email;
		String from="mjtech8169569382@gmail.com";
		 sessions=session;
	   GenerateOtp.generateOtp(subject, message, to, from);
	  
	  session.setAttribute("emails", myEmail);
	 
		return ResponseEntity.ok(new MyTokens(otp));
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody String password)
	{
		MyEmail emails= (MyEmail) sessions.getAttribute("emails");
	String myemail=emails.getEmail();
	
//	
User user=this.userRepository.getUserByEmail(myemail);
		user.setPassword(encoder.encode(password));
		User users=this.userRepository.save(user);
		
		return ResponseEntity.ok(users);
	}
}
