package com.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.serviceImpl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	@Autowired
	private AuthenticationToken authenticationToken;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailService).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf()
		.disable()
		.cors()
		.disable()
		.authorizeRequests()
		.antMatchers("/user/","/token","/forget/email","/forget/changePassword","/verify","/normal","/user/test")
		 .permitAll()
		 .antMatchers(HttpMethod.OPTIONS)
		 .permitAll()
		 .anyRequest()
		 .authenticated()
		 .and()
		 .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)		 
		 .and()
		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		  http.addFilterBefore(authenticationToken, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public BCryptPasswordEncoder encoder()
	{
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticateBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
}
