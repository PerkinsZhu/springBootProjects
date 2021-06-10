package com.perkin.ws;

import javax.jws.WebService;
import java.util.List;

/**
 * @author: perkins Zhu
 * @date: 2021/6/10 16:39
 * @description:
 **/
@WebService
public interface UserService {
    List<String> sayHi(String username);

}

