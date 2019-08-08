package cn.jianchengwnag.todo.springcloud.nacos.provider.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class EchoController {

    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "Echo Nacos Discovery " + string;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }
}
