package todo.springcloud.atguigucloud2020.consumer.feign.hystrix.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wjc
 * @date 2021/1/16
 */
@Component
@FeignClient(value = "eureka-provider-hystrix-payment", fallback = PaymentHystrixFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/circuitBreaker/{id}")
    String paymentInfo_CircuitBreaker(@PathVariable("id") Integer id);
}
