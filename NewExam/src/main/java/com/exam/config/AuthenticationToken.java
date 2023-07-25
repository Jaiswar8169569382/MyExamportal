package com.exam.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.helper.JwtUtil;
import com.exam.serviceImpl.UserDetailServiceImpl;

@Component
public class AuthenticationToken extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailServiceImpl userDetail;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String authToken=request.getHeader("Authorization");
		
		String token=null;
		String username=null;
		
		if(authToken!=null && authToken.startsWith("Bearer"))
		{
			token=authToken.substring(7);
			
			try
			{
				username=jwtUtil.getUsernameFromToken(token);
			}
			catch(Exception e)
			{
				
			}
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails userDetails=userDetail.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken( userDetails,null,userDetails.getAuthorities());
			     usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.println("User not found");
			}
		}
		else
		{
			System.out.println("User not found");
		}
		
		filterChain.doFilter(request, response);
	}

	
}
