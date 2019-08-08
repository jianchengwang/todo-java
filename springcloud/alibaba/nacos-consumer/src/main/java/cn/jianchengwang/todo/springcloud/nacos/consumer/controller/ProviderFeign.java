package cn.jianchengwang.todo.springcloud.nacos.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("nacos-provider")
public interface ProviderFeign {
    @GetMapping("/hello")
    String hello(@RequestParam(name = "name") String name);
}
