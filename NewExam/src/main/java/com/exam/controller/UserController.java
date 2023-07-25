package com.exam.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.helper.Message;
import com.exam.helper.MyUtility;
import com.exam.helper.ResetPassword;
import com.exam.repo.UserRepo;
import com.exam.service.UserService;
import com.exam.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserServiceImpl userImpl;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@PostMapping("/")
	public User saveUser(@RequestBody User user,HttpServletRequest request) throws Exception
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = new Role();
		role.setrId(17L);
		role.setrName("NORMAL");
		
		UserRole userRole= new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		List<UserRole> userRoles = new ArrayList<>();
		userRoles.add(userRole);
		
		String myurl=MyUtility.getUrl(request);
		
		User users=userService.saveUser(user, userRoles);
		this.userImpl.sendVerificationCode(user, myurl);
		return users;
	}
	
	@GetMapping("/test")
	public String test()
	{
		return "Welcome to backend api of examortal";
	}
	@PutMapping("/")
	public User updateUser(@RequestBody User user)
	{
		return this.userService.updateuser(user);
	}
	
	@GetMapping("/{username}")
	public User getUserByUsername(@PathVariable("username") String username)
	{
	   return this.userService.getUserByUsername(username);
	}
	
	@GetMapping("/all")
	public List<User> getAllUser()
	{
		return this.userService.getAlluser();
	}
	
	@DeleteMapping("/{id}")
	public void deleteUer(@PathVariable("id") Long id)
	{
		this.userService.delteUser(id);
	}
	
	@GetMapping("/currentuser")
	public User getCurrentUser(Principal principal)
	{
		String username=principal.getName();
		User user=userService.getUserByUsername(username);
		return user;
	}
	
	@GetMapping("/count")
	public ResponseEntity<?> getAllCount()
	{
		return ResponseEntity.ok(userRepo.count());
	}
	
	@PostMapping("/resetpassword")
	public ResponseEntity<Message> changePassword(@RequestBody ResetPassword resetPassword,Principal principal)
	{
		
		String username=principal.getName();
		User user=this.userRepo.getUserByUsername(username);
		if(this.passwordEncoder.matches(resetPassword.getOldpassword(), user.getPassword()))
		{
			user.setPassword(passwordEncoder.encode(resetPassword.getNewpassword()));
			userRepo.save(user);
			System.out.println("password change success");
			Message sms = new Message("success");
			return ResponseEntity.ok(sms);
		}
		else
		{
			Message sms = new Message("failed");
			return ResponseEntity.ok(sms);
		}
	}
	
	@GetMapping("/normal")
	public long getNormalUser()
	{
		
		long count=this.userRepo.getNormalUser("NORMAL");
		return count;
	}
}
