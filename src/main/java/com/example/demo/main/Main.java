package com.example.demo.main;


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1","1");
        String s = hashMap.get("1");
        System.out.println(s);
        hashMap.put("1","2");
        String s1 = hashMap.get("1");
        System.out.println(s1);

    }

}


