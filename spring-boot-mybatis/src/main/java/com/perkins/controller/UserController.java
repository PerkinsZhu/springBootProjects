package com.perkins.controller;


import cn.hutool.core.util.RandomUtil;
import com.perkins.aop.DataSource;
import com.perkins.aop.DynamicDataSource;
import com.perkins.beans.Person;
import com.perkins.beans.User;
import com.perkins.repo.PersonRepository;
import com.perkins.service.PersonService;
import com.perkins.service.PersonServiceScala;
import com.perkins.service.UserService;
import com.perkins.service.UserServiceDAO;
import com.perkins.utils.FileToBase64;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private User user;

    @Autowired
    private UserServiceDAO userServiceDAO;


    @Autowired
    PersonService personService;

    @Autowired
    PersonServiceScala personServiceScala;


    @Autowired
    DynamicDataSource dynamicDataSource;
    //显示用户
    @RequestMapping("list")
    public List<User> index() throws Exception {
//        return userService.getUser();
//       通过接口方式进行查询
        return userServiceDAO.selectAllUser();
    }

    @RequestMapping("list2")
    public List<User> list2() throws Exception {
//        return userService.getUser();
//       通过接口方式进行查询

        return userServiceDAO.selectAllUserWithDS2();
    }

//    @DataSource(value="master")
    @DataSource(value="slave")
    @RequestMapping("list3")
    public List<User> list3() throws Exception {
//        return userService.getUser();
//       通过接口方式进行查询
        return userServiceDAO.selectAllUserWithDS3();
    }

    @RequestMapping("listPerson")
    public List<Person> listPerson() {
        return personService.listPerson();
    }

    @RequestMapping("scalaListPerson")
    public List<Person> scalaListPerson() {
        return personServiceScala.listPerson();
    }

    //删除用户
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable int id) throws Exception {
//        userService.deleteUser(id);
        userServiceDAO.deleteService(id);
        return "你已经删掉了id为" + id + "的用户";
    }

    //增加用户
    @RequestMapping("addUser")
    public String addUser() throws Exception {
        System.out.printf("----addd-----");
        user.setAge(33);
        user.setUsername("阿花");
        userService.addUser(user);
        return "增加用户";
    }

    @Autowired
    private PersonRepository personRepository;

    //增加用户
    @RequestMapping("addPerson")
    public String addPerson() throws Exception {
        System.out.printf("----addd-----");
        Person person = new Person();
        // 通过 jpa 的方式实现数据库操作
        personRepository.save(person);
        personRepository.findAll().forEach(person1 -> {
            System.out.printf(person.toString());
        });
        return "增加用户";
    }

    @RequestMapping("download")
    public String downloadFile(){
        File file = FileUtils.getFile(System.nanoTime() + RandomUtil.randomChar() + ".xlsx");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            //TODO把文件数据写入file
            outputStream.write("helloworld".getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            //把file二进制转base64 String
            String baseStr = FileToBase64.encodeBase64File(file.getAbsolutePath());
            return baseStr;
        } catch (Exception e) {
            log.error("数据下载异常", e);
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
        return "";
    }
}