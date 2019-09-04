package com.perkins.service;

import com.perkins.beans.User;
import com.perkins.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> getUser() throws Exception {
        return userMapper.getUser();
    }
    //根据id删除用户
    @Override
    public void deleteUser(int id) throws Exception {
        userMapper.deleteUser(id);
    }
    //新增用户
    @Override
    public void addUser(User user) throws Exception {
//        userMapper.addUser(user);
    }
}