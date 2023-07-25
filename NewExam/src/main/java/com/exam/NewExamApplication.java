package com.exam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewExamApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(NewExamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Project Running start...");
	}

	
}
