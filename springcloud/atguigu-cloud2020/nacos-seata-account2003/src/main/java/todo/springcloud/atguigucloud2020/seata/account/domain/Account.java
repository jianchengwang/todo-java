package todo.springcloud.atguigucloud2020.seata.account.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author wjc
 * @date 2021/1/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_account")
public class Account {

    private Long id;
    private Long userId;
    private BigDecimal total;
    private BigDecimal used;
    private BigDecimal residue;
}
