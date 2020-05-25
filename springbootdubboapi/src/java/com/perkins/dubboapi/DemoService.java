package com.perkins.dubboapi;

import com.perkins.dubboapi.bean.User;

import java.util.List;

public interface DemoService {
    List<User> getUser();
}