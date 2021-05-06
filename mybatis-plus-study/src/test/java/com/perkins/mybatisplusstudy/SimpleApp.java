package com.perkins.mybatisplusstudy;

import com.perkins.mybatisplusstudy.mapper.auto.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.HashMap;

public class SimpleApp {


    @Test
    public void testHashMap() {
        HashMap<String, String> map = new HashMap<>(1);
        map.get("aa");
    }


    @Test
    public void testMybatis() {
/*
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            System.out.println(res.getTitle());
        } finally {
            session.close();
        }*/
    }
}
