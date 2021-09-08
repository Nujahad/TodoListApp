package com.todolist.springboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.todolist")
@EnableJpaRepositories("com.todolist.repository")
@EntityScan("com.todolist.model")
@EnableJpaAuditing
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class,args);
    }
}
