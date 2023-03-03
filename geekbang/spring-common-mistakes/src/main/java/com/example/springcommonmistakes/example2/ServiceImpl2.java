package com.example.springcommonmistakes.example2;

import org.springframework.stereotype.Service;

/**
 * AbstractAutowireCapableBeanFactory#createBeanInstance
 * ConstructorResolver#instantiate
 * ConstructorResolver#autowireConstructor
 *
 * @author jianchengwang
 * @date 2023/3/3
 */
@Service
public class ServiceImpl2 {
    private String serviceName;
    public ServiceImpl2(String serviceName){
        this.serviceName = serviceName;
    }

    // No default constructor found
//    public ServiceImpl2(String serviceName, String otherStringParameter){
//        this.serviceName = serviceName;
//    }
}
