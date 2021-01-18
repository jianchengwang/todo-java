package todo.springcloud.atguigucloud2020.seata.order.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import todo.springcloud.atguigucloud2020.seata.order.enums.OrderStatusEnum;

import java.math.BigDecimal;

/**
 * @author wjc
 * @date 2021/1/18
 */
@Data
@TableName("t_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    private OrderStatusEnum status; //订单状态：0：创建中；1：已完结
}
