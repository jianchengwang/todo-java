package core.concurrency.juc.atomic;

/**
 * @author jianchengwang
 * @date 2023/4/26
 */

public class SimulatedCAS{
    volatile int count;
    // 实现count+=1
    public void addOne(){
        int newValue;
        do {
            newValue = count+1;
        }while(count != cas(count, newValue));
    }
    // 模拟实现CAS，仅用来帮助理解
    public synchronized int cas(int expect, int newValue) {
        // 读目前count的值
        int curValue = count;
        // 比较目前count值是否==期望值
        if(curValue == expect){
            // 如果是，则更新count的值
            count= newValue;
        }
        // 返回写入前的值
        return curValue;
    }
}
