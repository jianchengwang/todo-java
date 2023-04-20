package design.decorator.shopping;

import java.math.BigDecimal;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
public class BaseCountDecorator implements IBaseCount {

    private IBaseCount count;

    public BaseCountDecorator(IBaseCount baseCount) {
        this.count = baseCount;
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        if(count!=null) {
            payTotalMoney = count.countPayMoney(orderDetail);
        } return payTotalMoney;
    }
}
