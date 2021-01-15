package todo.springcloud.atguigucloud2020.provider.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import todo.springcloud.atguigucloud2020.provider.payment.service.PaymentService;
import todo.springcloud.atguigucloud2020.common.entity.CommonResult;
import todo.springcloud.atguigucloud2020.common.entity.Payment;

import javax.annotation.Resource;

/**
 * @author wjc
 * @date 2021/1/15
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping
    public CommonResult<Object> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);

        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功,serverPort: " + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getById(id);

        if (payment != null) {
            return new CommonResult<>(200, "查询成功,serverPort:  " + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有对应记录,查询ID: " + id, null);
        }
    }
}