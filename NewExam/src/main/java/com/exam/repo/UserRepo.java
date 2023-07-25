package com.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;

public interface UserRepo extends JpaRepository<User, Long>{

	public User getUserByUsername(String username);
	
	@Query("select u from User u where u.email= :emails")
	public User getUserByEmail(@Param("emails") String emails);
	
	@Query("update User u set u.enable=true where u.id= :id")
	@Modifying
	public void enableUser(Long id);

	@Query("select u from User u where u.verificationcode= :code")
	public User getUserByVerifiation(String code);

	//@Query("SELECT u FROM User u JOIN u.roles r WHERE r.role=:rolename")
	//@Query("select u from User u join u.userRole r where r.role= :role")
	
	@Query("select count(u) from User u join u.userRole r where r.role.rName= :role")
	public long getNormalUser(String role);
	
}
