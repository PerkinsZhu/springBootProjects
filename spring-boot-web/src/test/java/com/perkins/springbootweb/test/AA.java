package com.perkins.springbootweb.test;

/**
 * @author ：Perkins Zhu
 * @date ：Created in 2021/2/25 15:12
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class AA extends A {
    public AA() {
//        super();
        //如果父类无无参构造函数(被重载了)，则子类必须手动调用父类的构造函数
        super("JAVA");
        System.out.println("aa");
    }

    public static void run() {
        System.out.println("RUN");
    }
}
