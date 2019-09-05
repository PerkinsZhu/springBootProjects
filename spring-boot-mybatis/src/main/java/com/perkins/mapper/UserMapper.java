package com.perkins.mapper;

import java.util.List;

import com.perkins.beans.User;
import org.apache.ibatis.annotations.Mapper;

// 这种接口形式没有在方法上配置SQL，因此必须对应的有XXXMapper.xml文件
public interface UserMapper {

    //获取用户名单
    public List<User> getUser() throws Exception;

    //根据id删除用户
    public void deleteUser(int id) throws Exception;

    //新增用户
    public void addUser(User user) throws Exception;
}