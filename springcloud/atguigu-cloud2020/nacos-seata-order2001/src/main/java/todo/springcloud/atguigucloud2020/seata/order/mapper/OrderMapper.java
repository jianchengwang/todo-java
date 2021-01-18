package todo.springcloud.atguigucloud2020.seata.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import todo.springcloud.atguigucloud2020.seata.order.domain.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
