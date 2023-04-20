package design.decorator.shopping;

import java.math.BigDecimal;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
public class BaseCount implements IBaseCount {

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        orderDetail.setPayMoney(orderDetail.getMerchandise().getPrice());
        System.out.println("商品原单价金额为：" + orderDetail.getPayMoney());
        return orderDetail.getPayMoney();
    }
}
