package todo.springcloud.atguigucloud2020.consumer.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import todo.springcloud.atguigucloud2020.common.entity.CommonResult;
import todo.springcloud.atguigucloud2020.common.entity.Payment;
import todo.springcloud.atguigucloud2020.consumer.order.lb.LoadBalancer;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author wjc
 * @date 2021/1/15
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderController {
//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://eureka-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
//        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
        // 调用自定义实现轮询的ribbon算法
        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-PROVIDER-PAYMENT");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance instance = loadBalancer.instance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/" + id, CommonResult.class);
    }
}