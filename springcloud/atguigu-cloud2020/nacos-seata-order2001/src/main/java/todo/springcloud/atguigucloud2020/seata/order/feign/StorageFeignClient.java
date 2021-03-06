package todo.springcloud.atguigucloud2020.seata.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import todo.springcloud.atguigucloud2020.common.entity.CommonResult;

/**
 * @auther zzyy
 * @create 2020-02-26 15:22
 */
@FeignClient(value = "nacos-seata-storage")
public interface StorageFeignClient
{
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
