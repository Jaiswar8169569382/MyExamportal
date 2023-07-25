package com.exam.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.helper.GenerateOtp;
import com.exam.helper.UserFoundException;
import com.exam.repo.RoleRepo;
import com.exam.repo.UserRepo;
import com.exam.service.UserService;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private RoleRepo roleRepository;
	@Override
	public User saveUser(User user,List<UserRole> userRoles) throws Exception {
		
		String varificationcode=RandomString.make(64);
		user.setVerificationcode(varificationcode);
		user.setEnable(false);
		// TODO Auto-generated method stub
		User oldUser=userRepository.getUserByUsername(user.getUsername());
		if(oldUser!=null)
		{
			System.out.println("User Already Registered");
			throw new UserFoundException("User Already register with same user name");
		}
		else
		{
			for(UserRole ur:userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRole().addAll(userRoles);
			oldUser=userRepository.save(user);
		}
		return oldUser;
	}

	@Override
	public User updateuser(User user) {
		// TODO Auto-generated method stub
		return this.userRepository.save(user);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.getUserByUsername(username);
	}

	@Override
	public List<User> getAlluser() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Override
	public void delteUser(Long id) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(id);
	}
	
	public void sendVerificationCode(User user,String getUrl)
	{
		System.out.println(user.getVerificationcode());
		String subject="Please Verify Your Registration";
		String content= "<p>Dear "+user.getFirstname() +" "+user.getLastname() + ", </p>";
		content+="<b>Welcme to MyQuizPortal</b>";
		content+="<p>Please Verify your account to click below link</p>";
		String verifylink= getUrl+"/verify?code="+user.getVerificationcode();
		content+="<h3><a href=\"" + verifylink + "\">Verify</a></h3>";
		
				GenerateOtp.generateOtp(subject, content,user.getEmail() , "mjtech8169569382@gmail.com");
		
	}
	
	@Transactional
	public boolean verifyAccount(String code)
	{
		User user=this.userRepository.getUserByVerifiation(code);
		if(user==null || user.isEnabled())
		{
			return false;
		}
		else
		{
			this.userRepository.enableUser(user.getId());
			return true;
		}
	}
	
}
