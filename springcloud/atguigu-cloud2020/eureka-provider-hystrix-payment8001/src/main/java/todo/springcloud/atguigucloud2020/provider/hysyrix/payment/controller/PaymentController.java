package todo.springcloud.atguigucloud2020.provider.hysyrix.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import todo.springcloud.atguigucloud2020.provider.hysyrix.payment.service.PaymentService;

import javax.annotation.Resource;

/**
 * @author wjc
 * @date 2021/1/16
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("*****result: " + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("*****result: " + result);
        return result;
    }

    @GetMapping("/payment/hystrix/circuitBreaker/{id}")
    public String paymentInfo_CircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("*****result: " + result);
        return result;
    }
}
