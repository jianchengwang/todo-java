package todo.springcloud.atguigucloud2020.seata.storage.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wjc
 * @date 2021/1/18
 */
@Data
@TableName("t_storage")
public class Storage {
    private Long id;
    private Long productId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
