package mq.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author jianchengwang
 * @date 2023/4/21
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public CompletableFuture<Void> add(int account, int amount) {
        System.out.println("add " + amount + " to " + account);
        return CompletableFuture.completedFuture(null);
    }
}
