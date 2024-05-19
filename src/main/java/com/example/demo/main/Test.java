package com.example.demo.main;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Test {


    @Bean
    @Scope("singleton")
    public A getA(){
        return new A();
    }

}

