package com.perkins.springbootweb.mongo;

import com.perkins.dubboapi.bean.User;
import com.perkins.springbootweb.dao.mongo.UserDao;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUser {

    private static UserDao userDaoImpl;
    private static ClassPathXmlApplicationContext app;
    private static String collectionName;

    @BeforeClass
    public static void initSpring() {
        try {
            app = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext-mongo.xml",
                    "classpath:spring-dispatcher.xml"});
            userDaoImpl = (UserDao) app.getBean("userDaoImpl");
            collectionName = "users";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
        //添加一百个user
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId("" + i);
            user.setAge(i);
            user.setName("zcy" + i);
            user.setPassword("zcy" + i);
            userDaoImpl.insert(user, collectionName);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("maxAge", 50);
        List<User> list = userDaoImpl.findAll(params, collectionName);
        System.out.println("user.count()==" + list.size());
    }
}
