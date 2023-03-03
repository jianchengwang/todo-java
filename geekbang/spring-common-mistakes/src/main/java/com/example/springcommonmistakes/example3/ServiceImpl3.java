package com.example.springcommonmistakes.example3;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 * @author jianchengwang
 * @date 2023/3/3
 */
@Service
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // error
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) // right
public class ServiceImpl3 {

}
