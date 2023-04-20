package design.singleton;

/**
 *
 * 使用了 static 修饰了成员变量 instance，所以该变量会在类初始化的过程中被收集进类构造器即 方法中。
 * 在多线程场景下，JVM 会保证只有一个线程能执行该类的 方法，其它线程将会被阻塞等待。
 * 等到唯一的一次 方法执行完成，其它线程将不会再执行 方法，转而执行自己的代码。也就是说，static 修饰了成员变量 instance，在多线程的情况下能保证只实例化一次。
 * 这种方式实现的单例模式，在类初始化阶段就已经在堆内存中开辟了一块内存，用于存放实例化对象，所以也称为饿汉模式。
 * 饿汉模式实现的单例的优点是，可以保证多线程情况下实例的唯一性，而且 getInstance 直接返回唯一实例，性能非常高。
 * 然而，在类成员变量比较多，或变量比较大的情况下，这种模式可能会在没有使用类对象的情况下，一直占用堆内存。
 * 试想下，如果一个第三方开源框架中的类都是基于饿汉模式实现的单例，这将会初始化所有单例类，无疑是灾难性的。
 * @author jianchengwang
 * @date 2023/4/20
 */

//饿汉模式
public final class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();//自行创建实例
    private HungrySingleton(){}//构造函数
    public static HungrySingleton getInstance(){//通过该函数向整个系统提供实例
        return instance;
    }
}
