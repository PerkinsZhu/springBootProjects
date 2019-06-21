package com.perkins.springbootweb.controller;

import com.perkins.dubboapi.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {


    @RequestMapping("/hello")
    public User getUser() {
        User user = new User("jack");
        return user;
    }

}
