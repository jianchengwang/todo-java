package todo.springcloud.atguigucloud2020.consumer.feign.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import todo.springcloud.atguigucloud2020.common.entity.CommonResult;
import todo.springcloud.atguigucloud2020.common.entity.Payment;
import todo.springcloud.atguigucloud2020.consumer.feign.order.service.PaymentFeignService;

import javax.annotation.Resource;

/**
 * @author wjc
 * @date 2021/1/16
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id) {
        return paymentFeignService.getById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // OpenFeign客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
