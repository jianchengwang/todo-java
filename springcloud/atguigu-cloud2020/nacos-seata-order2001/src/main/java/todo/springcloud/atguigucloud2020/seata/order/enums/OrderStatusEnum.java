package todo.springcloud.atguigucloud2020.seata.order.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author wjc
 * @date 2021/1/18
 */
public enum OrderStatusEnum implements IEnum<Integer> {
    CREATING(0),
    DON(1),
    ;

    private Integer value;
    OrderStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
