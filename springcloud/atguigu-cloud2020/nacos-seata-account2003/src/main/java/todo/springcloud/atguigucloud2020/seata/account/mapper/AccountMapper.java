package todo.springcloud.atguigucloud2020.seata.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import todo.springcloud.atguigucloud2020.seata.account.domain.Account;

/**
 * @author wjc
 * @date 2021/1/18
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
