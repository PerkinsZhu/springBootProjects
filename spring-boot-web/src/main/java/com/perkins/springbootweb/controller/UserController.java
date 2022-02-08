package com.perkins.springbootweb.controller;

import com.perkins.dubboapi.bean.User;
import com.perkins.springbootweb.dao.mongo.UserDao;
import com.perkins.springbootweb.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/user")
public class UserController extends BaseController {

    private String colName = "user";
    @Autowired
    UserDao userDaoImpl;

    @RequestMapping("/hello")
    public User getUser() {
        User user = new User("jack");
        return user;
    }

    @RequestMapping("/add")
    public User addUser() {
        for (int i = 0; i < 10; i++) {
            User user = new User("jack-" + i);
            userDaoImpl.insert(user, colName);

        }
        return new User("ok");
    }

    @RequestMapping("/jump")
    public String jump() {
        return "new.html";
    }

    @PostMapping("/addV2")
    public int addUserV2(@RequestBody @Valid UserVO userVO) {
        System.out.println(userVO);
        return userVO.getAge();
    }
}
