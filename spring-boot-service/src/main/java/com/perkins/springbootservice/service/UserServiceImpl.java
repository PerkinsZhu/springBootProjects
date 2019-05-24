package com.perkins.springbootservice.service;

import com.perkins.dubboapi.UserService;
import com.perkins.dubboapi.bean.User;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUser() {
        System.out.printf("-----get request----");
        List<User> list = new LinkedList<User>();
        list.add(new User("jack"));
        return list;
    }
}
