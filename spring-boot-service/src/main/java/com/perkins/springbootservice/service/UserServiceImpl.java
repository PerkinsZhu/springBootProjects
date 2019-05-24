package com.perkins.springbootservice.service;

import com.perkins.dubboapi.UserService;
import com.perkins.dubboapi.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getUser() {
        log.info("-----get request----");
        List<User> list = new LinkedList<User>();
        list.add(new User("jack"));
        return list;
    }
}
