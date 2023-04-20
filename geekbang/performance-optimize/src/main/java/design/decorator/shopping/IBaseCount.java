package design.decorator.shopping;

import java.math.BigDecimal;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
public interface IBaseCount {
    BigDecimal countPayMoney(OrderDetail orderDetail);
}
