package com.perkins.utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: perkins Zhu
 * @date: 2021/5/31 16:32
 * @description:
 **/
public class SimpleTest {

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()));
    }
}
