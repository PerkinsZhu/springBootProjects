package com.perkins.springbootweb.vo;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author: perkins Zhu
 * @date: 2022/2/8 10:35
 * @description:
 **/
@Data
public class UserVO {

    @Size(max = 5)
    @NotNull(message = "name 不能为空")
    private String name;

    @Min(12)
    @Max(20)
    @NotNull
    private Integer age;

    @Email(message = "email 格式不正确")
    @NotNull(message = "email 不能为空")
    private String email;
}
