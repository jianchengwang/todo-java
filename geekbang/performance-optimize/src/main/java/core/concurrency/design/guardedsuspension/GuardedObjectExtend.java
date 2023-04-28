package core.concurrency.design.guardedsuspension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */

class GuardedObjectExtend<T>{
    //受保护的对象
    T obj;
    final Lock lock =
            new ReentrantLock();
    final Condition done =
            lock.newCondition();
    final int timeout=2;
    //保存所有GuardedObject
    final static Map<Object, GuardedObjectExtend>
            gos=new ConcurrentHashMap<>();
    //静态方法创建GuardedObject
    static <K> GuardedObjectExtend
    create(K key){
        GuardedObjectExtend go=new GuardedObjectExtend();
        gos.put(key, go);
        return go;
    }
    static <K, T> void
    fireEvent(K key, T obj){
        GuardedObjectExtend go=gos.remove(key);
        if (go != null){
            go.onChanged(obj);
        }
    }
    //获取受保护对象
    T get(Predicate<T> p) {
        lock.lock();
        try {
            //MESA管程推荐写法
            while(!p.test(obj)){
                done.await(timeout,
                        TimeUnit.SECONDS);
            }
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }finally{
            lock.unlock();
        }
        //返回非空的受保护对象
        return obj;
    }
    //事件通知方法
    void onChanged(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
