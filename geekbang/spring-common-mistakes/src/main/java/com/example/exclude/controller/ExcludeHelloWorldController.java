package com.example.exclude.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianchengwang
 * @date 2023/2/28
 */
@RestController
public class ExcludeHelloWorldController {
    @RequestMapping(path = "excludeHi", method = RequestMethod.GET)
    public String hi(){
        return "excludeHi";
    };
}
