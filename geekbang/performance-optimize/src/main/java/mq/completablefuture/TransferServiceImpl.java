package mq.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author jianchengwang
 * @date 2023/4/21
 */
public class TransferServiceImpl implements TransferService {

    private AccountService accountService = new AccountServiceImpl();

    @Override
    public CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount) {
        // 异步调用add方法从fromAccount扣减相应金额
        return accountService.add(fromAccount, -1 * amount)
        // 然后调用add方法给toAccount增加相应金额
        .thenCompose(v -> accountService.add(toAccount, amount));
    }
}
