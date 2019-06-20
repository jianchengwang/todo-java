package cn.jianchengwang.todo.springboot2.controller;


import cn.jianchengwang.todo.springboot2.model.User;
import cn.jianchengwang.todo.springboot2.properties.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    MyProperties myProperties;

    @RequestMapping("/getUser")
    public User getUser() {
        User user=new User();
        user.setName("小明");
        user.setPassword("xxxx");
        return user;
    }

    @RequestMapping("/getProperties")
    public MyProperties getProperties() {
        return myProperties;
    }
}
