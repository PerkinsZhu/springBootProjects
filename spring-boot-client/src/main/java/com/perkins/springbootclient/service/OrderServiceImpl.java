package com.perkins.springbootclient.service;


import com.perkins.dubboapi.DemoService;
import com.perkins.dubboapi.OrderService;
import com.perkins.dubboapi.UserService;
import com.perkins.dubboapi.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class OrderServiceImpl implements OrderService {
    @Reference(registry = "registry1")
    UserService userService;

    @Reference(registry = "registry2")
    DemoService demoService;

    @Override
    public List<User> initOrder(String uid) {
        log.info("-----send  request----");
        List<User> user = userService.getUser();
        List<User> user2 = demoService.getUser();
        return user;
    }
}
