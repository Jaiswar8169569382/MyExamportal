package com.exam.service;

import java.util.List;

import com.exam.entity.User;
import com.exam.entity.UserRole;

public interface UserService {

	public User saveUser(User user,List<UserRole> userRoles) throws Exception;
	
	public User updateuser(User user);
	
	public User getUserByUsername(String username);
	
	public List<User> getAlluser();
	
	public void delteUser(Long id);
}
