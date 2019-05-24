package com.perkins.springbootclient.controller;

import com.perkins.dubboapi.OrderService;
import com.perkins.dubboapi.bean.User;
import com.perkins.springbootclient.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Autowired
    OrderService orderService;

    @RequestMapping("getOrder")
    public List<User> getUser(@RequestParam("uid") String uid) {
        return orderService.initOrder(uid);
    }
}
