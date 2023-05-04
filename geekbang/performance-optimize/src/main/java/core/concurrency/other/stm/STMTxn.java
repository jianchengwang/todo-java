package core.concurrency.other.stm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jianchengwang
 * @date 2023/5/4
 */
public final class STMTxn implements Txn {

    // 事务ID生成器
    private static AtomicLong txnSeq = new AtomicLong(0);
    // 当前事务所有相关数据的快照
    private final Map<TxnRef, VersionedRef> inTxnMap = new HashMap<>();
    // 当前事务所有需要修改的数据
    private final Map<TxnRef, Object> writeMap = new HashMap<>();
    // 当前事务ID
    private long txnId;

    // 构造函数，生成事务ID
    public STMTxn() {
        txnId = txnSeq.incrementAndGet();
    }

    @Override
    public <T> T get(TxnRef ref) {
        // 将需要读取的数据，加入到inTxnMap中
        if (!inTxnMap.containsKey(ref)) {
            inTxnMap.put(ref, ref.curRef);
        }
        return (T) inTxnMap.get(ref).value;
    }

    @Override
    public <T> void set(TxnRef ref, T value) {
        // 将需要修改的数据，加入到inTxnMap中
        if (!inTxnMap.containsKey(ref)) {
            inTxnMap.put(ref, ref.curRef);
        }
        writeMap.put(ref, value);
    }

    // 提交事务
    boolean commit() {
        synchronized (STM.commitLock) {
            boolean isValid = true;
            // 校验所有读过的数据，在此期间是否被其他事务修改过
            for (Map.Entry<TxnRef, VersionedRef> entry : inTxnMap.entrySet()) {
                // 如果当前版本号与当初读取的版本号不一致，则表示数据已经被修改过了
                if (entry.getKey().curRef.version != entry.getValue().version) {
                    isValid = false;
                    break;
                }
            }
            if(isValid) {
                // 如果校验成功，则所有更改生效
                writeMap.forEach((k, v) -> k.curRef = new VersionedRef(v, txnId));
            }
            return isValid;
        }
    }
}
