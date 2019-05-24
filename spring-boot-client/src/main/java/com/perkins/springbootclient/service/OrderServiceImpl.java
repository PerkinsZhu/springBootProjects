package com.perkins.springbootclient.service;


import com.perkins.dubboapi.OrderService;
import com.perkins.dubboapi.UserService;
import com.perkins.dubboapi.bean.User;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Reference
    UserService userService;

    @Override
    public List<User> initOrder(String uid) {
        System.out.printf("-----send  request----");
        List<User> user = userService.getUser();
        return user;
    }
}
