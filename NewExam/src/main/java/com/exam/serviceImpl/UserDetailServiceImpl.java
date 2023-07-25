package com.exam.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.entity.User;
import com.exam.service.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=this.userService.getUserByUsername(username);
		if(user==null)
		{
			System.out.println("User not found exceptions");
			throw new UsernameNotFoundException("User not found exceptionsss");
		}
		
		return user;
	}

	
}
