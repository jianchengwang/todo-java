package com.example.springcommonmistakes.example7;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jianchengwang
 * @date 2023/3/7
 */
@Component
public class LightMgrService7 implements InitializingBean {
    @Autowired
    private LightService7 lightService;

    // 构造器注入
    private LightService7 lightService1;

    public LightMgrService7(LightService7 lightService) {
        this.lightService1 = lightService;
        lightService1.check();
    }

    @PostConstruct
    public void init() {
        lightService.check();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        lightService.check();
    }
}
