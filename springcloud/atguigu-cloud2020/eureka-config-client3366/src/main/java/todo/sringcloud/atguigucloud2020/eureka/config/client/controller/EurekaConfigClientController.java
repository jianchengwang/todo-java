package todo.sringcloud.atguigucloud2020.eureka.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjc
 * @date 2021/1/16
 */
@RestController
@RefreshScope // 手动动态刷新配置 curl -X POST "http://localhost:3366/actuator/refresh"
public class EurekaConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return configInfo;
    }
}
