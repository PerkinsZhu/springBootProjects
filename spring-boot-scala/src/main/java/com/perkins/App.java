package com.perkins;


import com.perkins.bean.A;
import org.junit.Test;

public class App {

    @Test
    public void testSimple() {
        //java 调用groovy 对象
        A a = new A();
        a.setAge(12);
        a.setName("张三");
        System.out.println(a);
    }
}
