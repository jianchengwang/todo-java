package todo.springcloud.atguigucloud2020.provider.payment.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import todo.springcloud.atguigucloud2020.common.entity.Payment;

/**
 * @author wjc
 * @date 2021/1/15
 */

@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getById(@Param("id") Long id);
}