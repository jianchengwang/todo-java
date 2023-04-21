package mq.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author jianchengwang
 * @date 2023/4/21
 */
public interface TransferService {
    /**
     *
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @return
     */
    CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount);
}
