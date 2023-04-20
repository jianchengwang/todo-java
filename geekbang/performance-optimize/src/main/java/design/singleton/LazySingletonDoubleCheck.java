package design.singleton;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
public final class LazySingletonDoubleCheck {
    private static volatile LazySingletonDoubleCheck instance = null;//自行创建实例

    public LazySingletonDoubleCheck(){}//构造函数

    public static LazySingletonDoubleCheck getInstance(){//通过该函数向整个系统提供实例
        if(instance == null){
            synchronized (LazySingletonDoubleCheck.class){
                if(instance == null){
                    instance = new LazySingletonDoubleCheck();
                }
            }
        }
        return instance;
    }
}
