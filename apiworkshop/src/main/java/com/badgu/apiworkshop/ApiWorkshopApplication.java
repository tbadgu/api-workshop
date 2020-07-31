package com.badgu.apiworkshop;

import com.badgu.apiworkshop.model.Todo;
import com.badgu.apiworkshop.repository.TodoRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
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

    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Todo Appication")
                        .version("0.0.1-SNAPSHOT")
                        .description("Sample Application to demonstrate Spring Boot + Open API fetures")
                        .contact(new Contact()
                                .name("Tushar Badgu")
                                .email("demo@email.com")
                                .url("https://github.com/tbadgu"))
                );
    }

}
