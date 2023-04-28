Guarded Suspension 模式： 多线程下，A线程有个受保护的操作，在执行过程中需要等待满足某个条件，这个条件由B线程控制，条件不满足，A线程则挂起，直到条件满足才继续执行。本质上该模式是对线程的互斥锁+等待唤醒机制应用的规范化。

GuardedObject 的内部实现非常简单，是管程的一个经典用法，你可以参考下面的示例代码，核心是：get() 方法通过条件变量的 await() 方法实现等待，onChanged() 方法通过条件变量的 signalAll() 方法实现唤醒功能

Guarded Suspension 模式也常被称作 Guarded Wait 模式、Spin Lock 模式（因为使用了 while 循环去等待），这些名字都很形象，不过它还有一个更形象的非官方名字：多线程版本的 if。
