package design.singleton;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
public final class LazySingletonSynchronized {
    private static LazySingletonSynchronized instance = null;//自行创建实例

    public LazySingletonSynchronized(){}//构造函数

    public static synchronized LazySingletonSynchronized getInstance(){//通过该函数向整个系统提供实例
        if(instance == null){
            instance = new LazySingletonSynchronized();
        }
        return instance;
    }
}
