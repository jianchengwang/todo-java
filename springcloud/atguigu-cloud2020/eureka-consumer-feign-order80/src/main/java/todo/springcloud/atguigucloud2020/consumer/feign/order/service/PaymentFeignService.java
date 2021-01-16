package todo.springcloud.atguigucloud2020.consumer.feign.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import todo.springcloud.atguigucloud2020.common.entity.CommonResult;
import todo.springcloud.atguigucloud2020.common.entity.Payment;

@Component
@FeignClient(value = "eureka-provider-payment")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/{id}")
    CommonResult<Payment> getById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
