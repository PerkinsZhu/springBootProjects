package com.perkins.mybatisplusstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.perkins.mybatisplusstudy.model.auto.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-30
 */
public interface IUserService extends IService<User> {

    List<User> getAll();

}
