package com.perkins.apollo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/test")
public class BaseController {
    @Value("${test.name}")
    String name;
    @Value("${test.age}")
    String age;
    @Value("${local.age}")
    String localAge;


    @GetMapping(value = "/name")
    @ResponseBody
    public String getName(HttpServletRequest request) {

        System.out.println(name);
        System.out.println(age);
        System.out.println(localAge);
        return "sss";
    }
}
