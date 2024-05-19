package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.main.LibraryProperties;
import com.example.demo.utils.BusinessErrorException;
import com.example.demo.utils.BusinessMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("my")
public class MyController {


    @Value("${wuhan2020}")
    String message;

    @Autowired
    LibraryProperties libraryProperties;


//    @GetMapping("num")
    @RequestMapping(value = "num",method = RequestMethod.GET)
    public int getNum(){

        try {
            int i = 1/0;
        } catch (Exception e) {
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }

        System.out.println(message);
        return 1;
    }

    @GetMapping("num/{id}/{password}")
    public int[]getNums(@PathVariable("id") int id,@PathVariable("password")int password){
        int []arr=new int[2];
        arr[0]=id;
        arr[1]=password;
        return arr;
    }

    @GetMapping("num/{classId}/teachers")
    public void etKlassRelatedTeachers(@PathVariable("classId")Long classId,
                                       @RequestParam(value = "type",required = false,defaultValue = "默认")
                                       String type){
        System.out.println("classId = " + classId + ", type = " + type);
    }


    @GetMapping("lib")
    public void getLib(){
        System.out.println(libraryProperties);
    }

    @GetMapping("json")
    public User getUser(){
        User user = new User();
        user.setUserName("tyy");
        user.setPassword("123456");
        user.setFullName("2104010719");

        return user;
    }


}
