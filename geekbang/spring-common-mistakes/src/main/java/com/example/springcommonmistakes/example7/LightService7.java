package com.example.springcommonmistakes.example7;

import org.springframework.stereotype.Service;

/**
 * @author jianchengwang
 * @date 2023/3/7
 */
@Service
public class LightService7 {
    public void start() {        System.out.println("turn on all lights");    }
    public void shutdown() {        System.out.println("turn off all lights");    }
    public void check() {        System.out.println("check all lights");    }
}