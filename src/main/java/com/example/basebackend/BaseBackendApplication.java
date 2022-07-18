package com.example.basebackend;

import com.example.basebackend.entities.TestEntity;
import com.example.basebackend.repository.TestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
//@ComponentScan("com.example.basebackend.repository")
//@EntityScan("com.example.basebackend.entities")
//@EnableAspectJAutoProxy
public class BaseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseBackendApplication.class, args);
    }

}
