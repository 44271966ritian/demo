package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
//@JsonIgnoreProperties({"userName"})
public class User {


    @JsonIgnore
    private String userName;
    private String fullName;
    private String password;


}
