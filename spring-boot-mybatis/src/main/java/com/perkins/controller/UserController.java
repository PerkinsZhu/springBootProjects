package com.perkins.controller;


import com.perkins.beans.Person;
import com.perkins.beans.User;
import com.perkins.repo.PersonRepository;
import com.perkins.service.PersonService;
import com.perkins.service.PersonServiceScala;
import com.perkins.service.UserService;
import com.perkins.service.UserServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        person.setEmail("asdf@ddd.com");
        person.setPassword("asdf");
        person.setUsername("name--" + System.currentTimeMillis());
        // 通过 jpa 的方式实现数据库操作
        personRepository.save(person);
        personRepository.findAll().forEach(person1 -> {
            System.out.printf(person.toString());
        });
        return "增加用户";
    }
}