package com.perkins.springcloudgateway.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：Perkins Zhu
 * @date ：Created in 2020/4/24 0:09
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Controller
@RequestMapping("/base")
public class BaseController {

    @GetMapping("/index")
    @ResponseBody
    public String get(){
        return "asdf";
    }
}
