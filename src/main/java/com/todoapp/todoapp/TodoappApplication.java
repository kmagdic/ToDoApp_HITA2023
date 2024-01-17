package com.todoapp.todoapp;

import com.todoapp.todoapp.model.User;
import com.todoapp.todoapp.model.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoappApplication {



	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);

	}



}
