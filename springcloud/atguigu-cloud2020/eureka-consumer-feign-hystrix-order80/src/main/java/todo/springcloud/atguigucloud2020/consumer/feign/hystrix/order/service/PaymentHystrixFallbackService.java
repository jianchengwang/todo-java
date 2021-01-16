package todo.springcloud.atguigucloud2020.consumer.feign.hystrix.order.service;

import org.springframework.stereotype.Component;

/**
 * @author wjc
 * @date 2021/1/16
 */
@Component
public class PaymentHystrixFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_CircuitBreaker(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_CircuitBreaker ,o(╥﹏╥)o";
    }
}
