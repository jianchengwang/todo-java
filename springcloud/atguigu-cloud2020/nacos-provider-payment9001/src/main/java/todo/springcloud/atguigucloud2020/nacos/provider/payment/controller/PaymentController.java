package todo.springcloud.atguigucloud2020.nacos.provider.payment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjc
 * @date 2021/1/17
 */
@RestController
@RefreshScope //支持Nacos的动态刷新功能。
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "nacos registry, serverPort: "+ serverPort+"\t id"+id;
    }

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
