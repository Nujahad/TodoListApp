package com.todolist.springboot.main;

import com.todolist.rest.RestConfig;
import com.todolist.service.ServiceConfig;
import com.todolist.springboot.main.security.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.todolist.repository")
@EntityScan("com.todolist.model")
@EnableJpaAuditing
@ComponentScan(basePackages = "com.todolist")
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class,args);
    }
}
