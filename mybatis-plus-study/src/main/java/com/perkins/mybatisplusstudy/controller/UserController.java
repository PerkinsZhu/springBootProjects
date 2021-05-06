package com.perkins.mybatisplusstudy.controller;


import com.perkins.mybatisplusstudy.model.auto.User;
import com.perkins.mybatisplusstudy.service.IUserService;
import com.perkins.mybatisplusstudy.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-30
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/id")
    public User getById(@PathParam("id") String id) {
        User user = userService.getById(id);
        log.info(user.toString());
        return user;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        List<User> list = userService.getAll();
        return list;
    }

}
