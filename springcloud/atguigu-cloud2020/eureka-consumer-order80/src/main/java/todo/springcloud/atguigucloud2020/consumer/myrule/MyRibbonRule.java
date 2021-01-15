package todo.springcloud.atguigucloud2020.consumer.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * @author wjc
 * @date 2021/1/16
 */
public class MyRibbonRule {
    //不能加Bean注解
    public IRule myRule() {
        return new RandomRule(); // 定义为随机
    }
}
