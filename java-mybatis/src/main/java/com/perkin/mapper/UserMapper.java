package com.perkin.mapper;

import com.perkin.entity.User;

import java.util.List;

public interface UserMapper {
    public List<User> getById(Long id);
    public Integer updateById(Long id);
}
