package com.perkin.ws;

import javax.xml.ws.Endpoint;

/**
 * @author: perkins Zhu
 * @date: 2021/6/10 16:40
 * @description:
 **/
public class Publish {

    //发布服务
    public static void main(String[] args) {
        String url = "http://localhost:8080/demo/hello";
        Endpoint.publish(url, new UserServiceImpl());
        System.out.println("webservice publish service success...");
    }

}

