package core.concurrency.juc.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jianchengwang
 * @date 2023/4/24
 */
public class Cache<K,V> {
    final Map<K,V> map = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock rl = rwl.readLock();
    final Lock wl = rwl.writeLock();

    public V get(K key) {
        try {
            rl.lock();
            return map.get(key);
        } finally {
            rl.unlock();
        }
    }

    public void set(K key, V value) {
        try {
            wl.lock();
            map.put(key, value);
        } finally {
            wl.unlock();
        }
    }

}
