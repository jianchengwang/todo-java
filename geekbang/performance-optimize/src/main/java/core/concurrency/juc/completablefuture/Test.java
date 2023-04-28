package core.concurrency.juc.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
public class Test {
    public static void main(String[] args) {

        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                                () -> "Hello World")
                        .thenApply(s -> s + " QQ")
                        .thenApply(String::toUpperCase);

        System.out.println(f0.join());
    }
}
