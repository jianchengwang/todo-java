package core.concurrency.other.stm;

/**
 * @author jianchengwang
 * @date 2023/5/4
 */
@FunctionalInterface
public interface TxnRunnable {
    void run(Txn txn);
}
