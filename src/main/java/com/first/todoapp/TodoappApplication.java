package com.first.todoapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoappApplication implements CommandLineRunner{
	
	private static Logger LOG =LoggerFactory.getLogger(TodoappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("hola desde consola");
		LOG.info("hola desde consola pepe");
		
	}

}
