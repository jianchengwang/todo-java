package com.example.springcommonmistakes.example5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianchengwang
 * @date 2023/3/3
 */
@RestController
@Slf4j
public class ValueTestController5 {

    @Value("#{student5}")
    private Student5 student5;

    //注册正常字符串
    @Value("我是字符串")
    private String text;

    //注入系统参数、环境变量或者配置文件中的值
    @Value("${USERNAME}")
    private String USERNAME;

    //注入其他Bean属性，其中student为bean的ID，name为其属性
    @Value("#{student5.name}")
    private String name;

    // 注入配置文件的属性
    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @RequestMapping(path = "5/user", method = RequestMethod.GET)
    public String getUser(){
        log.info(student5.getName());
        log.info(text);
        log.info(USERNAME);
        log.info(name);
        return username + ":" + password;
    };

}
