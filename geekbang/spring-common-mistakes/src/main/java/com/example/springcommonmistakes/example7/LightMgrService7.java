package com.example.springcommonmistakes.example7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jianchengwang
 * @date 2023/3/7
 */
@Component
public class LightMgrService7 {
    @Autowired
    private LightService7 lightService;

    public LightMgrService7() {
        lightService.check();
    }
}
