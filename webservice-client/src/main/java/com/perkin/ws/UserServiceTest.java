package com.perkin.ws;

import java.util.List;

/**
 * @author: perkins Zhu
 * @date: 2021/6/10 16:57
 * @description:
 **/
public class UserServiceTest {

    // 先运行 java-mybatis module中发布服务
    // 运行 com.perkin.ws.Publish.main
    //然后再浏览器中访问http://localhost:8080/demo/hello?wsdl看是否ok
    //访问没问题后运行该方法调用接口服务
    public static void main(String[] args) {
        UserServiceImplService userService = new UserServiceImplService();
        List<String> list = userService.getUserServiceImplPort().sayHi("carl");
        list.forEach(i -> System.out.println(i));
//        System.out.println(hiWord);
    }


}
