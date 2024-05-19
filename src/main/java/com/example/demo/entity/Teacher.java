package com.example.demo.entity;


import lombok.Data;

@Data
public class Teacher {

    private String name;

    private int age;

    public void say(){
        System.out.println("I am a teacher");
    }

    public void test(){
        System.out.println("现在开始考试");
    }

    public void checkHomework(){
        System.out.println("突击检查昨天留下作业");
    }

}
