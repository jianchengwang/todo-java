package cn.jianchengwang.todo.springboot2.hello.controller;


import cn.jianchengwang.todo.springboot2.hello.dao.UserRepository;
import cn.jianchengwang.todo.springboot2.hello.model.User;
import cn.jianchengwang.todo.springboot2.hello.properties.MyProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    MyProperties myProperties;

    @Resource
    UserRepository userRepository;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser(String name) {
        User user = userRepository.findUserByName(name);
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/getProperties")
    public MyProperties getProperties() {
        return myProperties;
    }
}
