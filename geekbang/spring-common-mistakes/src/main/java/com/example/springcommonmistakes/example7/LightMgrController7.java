package com.example.springcommonmistakes.example7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianchengwang
 * @date 2023/3/8
 */
@RestController
public class LightMgrController7 {
    @Autowired
    private LightMgrService7 lightMgrService7;
}
