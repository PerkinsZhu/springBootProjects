package com.perkins.service;

import com.perkins.beans.User;
import com.perkins.mapper.UserDao;
import com.perkins.mapper2.UserDao2;
import com.perkins.mapper3.UserDao3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceDAO {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDao2 userDao2;

    @Autowired
    private UserDao3 userDao3;


    /**
     * 根据名字查找用户
     */
    public User selectUserByName(String name) {
        return userDao.findUserByName(name);
    }

    /**
     * 查找所有用户
     */
    public List<User> selectAllUser() {
        return userDao.findAllUser();
//        return userDao2.findAllUser();
    }

    public List<User> selectAllUserWithDS2() {
//        return userDao.findAllUser();
        return userDao2.findAllUser();
    }
    public List<User> selectAllUserWithDS3() {
//        return userDao.findAllUser();
        return userDao3.findAllUser();
    }

    /**
     * 插入两个用户
     */
    public void insertService() {
        userDao.insertUser("SnailClimb", 22, 3000.0);
        userDao.insertUser("Daisy", 19, 3000.0);
    }

    /**
     * 根据id 删除用户
     */

    public void deleteService(int id) {
        userDao.deleteUser(id);
    }

    /**
     * 模拟事务。由于加上了 @Transactional注解，如果转账中途出了意外 SnailClimb 和 Daisy 的钱都不会改变。
     */
    @Transactional
    public void changemoney() {
        userDao.updateUser("SnailClimb", 22, 2000.0, 3);
        // 模拟转账过程中可能遇到的意外状况
        int temp = 1 / 0;
        userDao.updateUser("Daisy", 19, 4000.0, 4);
    }
}