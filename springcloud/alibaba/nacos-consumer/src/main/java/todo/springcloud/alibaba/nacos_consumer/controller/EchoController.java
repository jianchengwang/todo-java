package todo.springcloud.alibaba.nacos_consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class EchoController {

    private final RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    public EchoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {

        String url = "http://nacos-provider/echo/" + str;

        // 或者通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
//        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
//        url = serviceInstance.getUri() + "/echo/" + str;

        return restTemplate.getForObject(url, String.class);
    }

//    @Resource
//    EchoFeign echoFeign;
//
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String hello(@RequestParam String name) {
//        return echoFeign.hello(name);
//    }

}
