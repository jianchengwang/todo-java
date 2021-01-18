package todo.springcloud.atguigucloud2020.seata.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.springcloud.atguigucloud2020.common.entity.CommonResult;
import todo.springcloud.atguigucloud2020.seata.order.domain.Order;
import todo.springcloud.atguigucloud2020.seata.order.service.OrderService;

import javax.annotation.Resource;

/**
 * @author wjc
 * @date 2021/1/18
 */
@RestController
public class OrderController
{
    @Resource
    private OrderService orderService;

    @RequestMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}