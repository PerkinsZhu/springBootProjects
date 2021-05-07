package com.perkin.mapper;

import com.perkin.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<User> getById(Long id);

    public List<User> getByIdAndName(@Param("id") Long id, @Param("name") String name);

    public Integer updateById(Long id);
}
