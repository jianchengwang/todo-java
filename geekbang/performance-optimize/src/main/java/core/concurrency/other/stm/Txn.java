package core.concurrency.other.stm;

/**
 * @author jianchengwang
 * @date 2023/5/4
 */
public interface Txn {
    <T> T get(TxnRef ref);
    <T> void set(TxnRef ref, T value);
}
