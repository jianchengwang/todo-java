package todo.springcloud.atguigucloud2020.consumer.order.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author wjc
 * @date 2021/1/16
 */
public interface LoadBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);
}
