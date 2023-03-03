package com.example.springcommonmistakes.example3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianchengwang
 * @date 2023/3/3
 */

@RestController
public class HelloWorldController3 {

    @Autowired
    private ServiceImpl3 serviceImpl3;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(path = "3/hi", method = RequestMethod.GET)
    public String hi(){
        return "helloworld, service is : " + serviceImpl3; // error
//        return "helloworld, service is : " + getServiceImplRight2();
    };

    public ServiceImpl3 getServiceImplRight1(){
        return applicationContext.getBean(ServiceImpl3.class);
    }

    @Lookup
    public ServiceImpl3 getServiceImplRight2(){
        return null;
    }
}
