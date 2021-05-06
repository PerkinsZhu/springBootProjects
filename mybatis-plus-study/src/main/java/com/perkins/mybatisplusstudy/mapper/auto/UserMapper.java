package com.perkins.mybatisplusstudy.mapper.auto;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.perkins.mybatisplusstudy.model.auto.User;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-30
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> getAll();
}
