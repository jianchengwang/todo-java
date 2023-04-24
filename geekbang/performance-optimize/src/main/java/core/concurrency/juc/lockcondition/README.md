并发编程领域，有两大核心问题：一个是互斥，即同一时刻只允许一个线程访问共享资源；另一个是同步，即线程之间如何通信、协作。
这两大问题，管程都是能够解决的。
Java SDK 并发包通过 Lock 和 Condition 两个接口来实现管程，其中 Lock 用于解决互斥问题，Condition 用于解决同步问题。

synchronized 没有办法解决“破坏不可抢占条件方案”。 
原因是synchronized 申请资源的时候，如果申请不到，线程直接进入阻塞状态了，而线程进入阻塞状态，啥都干不了，也释放不了线程已经占有的资源。

Java SDK 里面锁的实现非常复杂，这里我就不展开细说了，但是原理还是需要简单介绍一下：它是利用了 volatile 相关的 Happens-Before 规则。
Java SDK 里面的 ReentrantLock，内部持有一个 volatile 的成员变量 state，获取锁的时候，会读写 state 的值；
解锁的时候，也会读写 state 的值（简化后的代码如下面所示）。
也就是说，在执行 value+=1 之前，程序先读写了一次 volatile 变量 state，在执行 value+=1 之后，又读写了一次 volatile 变量 state。
根据相关的 Happens-Before 规则：
顺序性规则：对于线程 T1，value+=1 Happens-Before 释放锁的操作 unlock()；
volatile 变量规则：由于 state = 1 会先读取 state，所以线程 T1 的 unlock() 操作 Happens-Before 线程 T2 的 lock() 操作；
传递性规则：线程 T1 的 value+=1 Happens-Before 线程 T2 的 lock() 操作

```java

class SampleLock {
  volatile int state;
  // 加锁
  lock() {
    // 省略代码无数
    state = 1;
  }
  // 解锁
  unlock() {
    // 省略代码无数
    state = 0;
  }
}
```

所谓可重入锁，顾名思义，指的是线程可以重复获取同一把锁

可重入函数，指的是多个线程可以同时调用该函数

锁的最佳实践
1. 永远只在更新对象的成员变量时加锁
2. 永远只在访问可变的成员变量时加锁
3. 永远不在调用其他对象的方法时加锁



