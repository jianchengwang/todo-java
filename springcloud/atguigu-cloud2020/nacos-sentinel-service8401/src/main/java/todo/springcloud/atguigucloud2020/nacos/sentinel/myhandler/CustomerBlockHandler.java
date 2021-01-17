package todo.springcloud.atguigucloud2020.nacos.sentinel.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import todo.springcloud.atguigucloud2020.common.entity.CommonResult;

/**
 * @author wjc
 * @date 2021/1/17
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----1");
    }
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----2");
    }
}

