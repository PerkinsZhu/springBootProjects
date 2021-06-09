package com.perkins;


import com.perkins.bean.BB;

/**
 * @author: perkins Zhu
 * @date: 2021/6/8 18:59
 * @description:
 **/
public class JavaApp {
    public static void main(String[] args) {
        System.out.println("hello world");
        BB a = new BB();
        a.setAge(12);
        a.setName(23);
        System.out.println(a);
    }
}
