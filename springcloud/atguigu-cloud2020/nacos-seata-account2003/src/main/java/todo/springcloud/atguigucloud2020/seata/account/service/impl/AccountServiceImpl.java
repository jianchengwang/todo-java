package todo.springcloud.atguigucloud2020.seata.account.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import todo.springcloud.atguigucloud2020.seata.account.domain.Account;
import todo.springcloud.atguigucloud2020.seata.account.mapper.AccountMapper;
import todo.springcloud.atguigucloud2020.seata.account.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author wjc
 * @date 2021/1/18
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        Account account = new Account();
        Account updateDo = accountMapper.selectOne(Wrappers.lambdaQuery(account).eq(Account::getId, userId));
        updateDo.setUsed(updateDo.getUsed().add(money));
        updateDo.setResidue(updateDo.getResidue().subtract(money));
        accountMapper.updateById(updateDo);
    }
}
