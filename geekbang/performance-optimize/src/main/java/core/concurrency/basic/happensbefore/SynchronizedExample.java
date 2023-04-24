package core.concurrency.basic.happensbefore;

/**
 * 在操作系统中，管程的定义如下： 管程是由一组数据以及定义在这组数据之上的对该组数据操作的操作组成的软件模块，称之为管程。
 * 基本特性： 1. 局部于管程的数据只能被局部于管程内的过程所访问。
 * 2. 一个进程只有通过调用管程内的过程才能进入管程访问共享数据
 * 3. 每次仅允许一个进程在管程中执行某个内部过程。
 * 注意：由于管程是一个语言的成分，所以管程的互斥访问完全由编译程序在编译时自动添加，无需程序员关注。
 *
 * 管程是一种通用的同步原语，在 Java 中指的就是 synchronized，synchronized 是 Java 里对管程的实现。
 * @author jianchengwang
 * @date 2023/4/23
 */
public class SynchronizedExample {

    private static int x = 10;

    public static void main(String[] args) {
        synchronized (SynchronizedExample.class) {
            //此处自动加锁
            // x是共享变量,初始值=10
            if (SynchronizedExample.x < 12) {
                SynchronizedExample.x = 12;
            }
        } //此处自动解锁
    }
}
