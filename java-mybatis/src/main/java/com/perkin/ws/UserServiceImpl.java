package com.perkin.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: perkins Zhu
 * @date: 2021/6/10 16:40
 * @description:
 **/
@WebService
public class UserServiceImpl implements UserService {

    @Override
    @WebMethod
    public List<String> sayHi(String username) {
        System.out.println("hi, " + username);
        List<String> list = new ArrayList<>(2);
        list.add("1");
        list.add("2");
        return list;
    }

}

