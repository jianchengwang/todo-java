package mq.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author jianchengwang
 * @date 2023/4/21
 */
public interface AccountService {
    /**
     * 转账
     * @param account
     * @param amount
     * @return
     */
    CompletableFuture<Void> add(int account, int amount);
}
