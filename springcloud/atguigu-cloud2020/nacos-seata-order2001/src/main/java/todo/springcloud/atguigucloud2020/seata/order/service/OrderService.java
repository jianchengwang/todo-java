package todo.springcloud.atguigucloud2020.seata.order.service;

import todo.springcloud.atguigucloud2020.seata.order.domain.Order;

public interface OrderService {
    void create(Order order);
}
