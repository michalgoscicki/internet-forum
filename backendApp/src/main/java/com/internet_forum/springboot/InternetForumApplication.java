package com.internet_forum.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class InternetForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetForumApplication.class, args);
	}


}
