package com.perkins.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("serial")
@ConfigurationProperties(prefix = "lynch.person")
public class PersonProperties implements Serializable {
    // 姓名
    private String name;
    // 年龄
    private int age;
    // 性别
    private String sex = "M";

    public PersonProperties() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}