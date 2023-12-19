package com.brunostaine.apitodoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiTodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTodoListApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "SERVER ONLINE";
	}
}
