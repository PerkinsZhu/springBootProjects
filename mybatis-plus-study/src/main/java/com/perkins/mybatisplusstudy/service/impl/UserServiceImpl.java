package com.perkins.mybatisplusstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.perkins.mybatisplusstudy.mapper.auto.UserMapper;
import com.perkins.mybatisplusstudy.model.auto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.perkins.mybatisplusstudy.service.IUserService;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
}
