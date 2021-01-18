package todo.springcloud.atguigucloud2020.seata.order.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.springcloud.atguigucloud2020.seata.order.domain.Order;
import todo.springcloud.atguigucloud2020.seata.order.enums.OrderStatusEnum;
import todo.springcloud.atguigucloud2020.seata.order.mapper.OrderMapper;
import todo.springcloud.atguigucloud2020.seata.order.feign.AccountFeignClient;
import todo.springcloud.atguigucloud2020.seata.order.service.OrderService;
import todo.springcloud.atguigucloud2020.seata.order.feign.StorageFeignClient;

import javax.annotation.Resource;

/**
 * @author wjc
 * @date 2021/1/18
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StorageFeignClient storageFeignClient;
    @Resource
    private AccountFeignClient accountFeignClient;

    @GlobalTransactional(name = "test-create-order", rollbackFor = Throwable.class)
    @Transactional(rollbackFor = Exception.class) // 本地事务注解要位于内层
    @Override
    public void create(Order order) {

        log.info("----->开始新建订单");
        order.setStatus(OrderStatusEnum.CREATING);
        //1 新建订单
        orderMapper.insert(order);

        //2 扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageFeignClient.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        //3 扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountFeignClient.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");

        // 异常
        int age = 1/0;

        //4 修改订单状态，从零到1,1代表已经完成
        log.info("----->修改订单状态开始");
        Order updateDo = new Order();
        updateDo.setUserId(order.getUserId());
        updateDo.setStatus(OrderStatusEnum.DON);
        log.info("----->修改订单状态结束");

        log.info("----->下订单结束了，O(∩_∩)O哈哈~");
    }
}
