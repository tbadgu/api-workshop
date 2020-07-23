package com.badgu.apiworkshop;

import com.badgu.apiworkshop.model.Todo;
import com.badgu.apiworkshop.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiWorkshopApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup(TodoRepository todoRepository) {
        return args -> todoRepository.save(new Todo("Tushar Badgu", "Sample Todo"));
    }

}
