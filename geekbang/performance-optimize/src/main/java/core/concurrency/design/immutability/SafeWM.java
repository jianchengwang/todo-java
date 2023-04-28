package core.concurrency.design.immutability;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */

public class SafeWM {
    class WMRange{
        final int upper;
        final int lower;
        public WMRange(int upper,int lower){
            //省略构造函数实现
            this.upper = upper;
            this.lower = lower;
        }
    }
    final AtomicReference<WMRange>
            rf = new AtomicReference<>(
            new WMRange(0,0)
    );
    // 设置库存上限
    void setUpper(int v){
        while(true){
            WMRange or = rf.get();
            // 检查参数合法性
            if(v < or.lower){
                throw new IllegalArgumentException();
            }
            WMRange nr = new
                    WMRange(v, or.lower);
            if(rf.compareAndSet(or, nr)){
                return;
            }
        }
    }
}
