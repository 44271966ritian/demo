package com.example.demo.entity;


import lombok.Data;

@Data
public class Teacher {

    private String name;

    private int age;

    public void say(){
        System.out.println("I am a teacher");
    }

}
