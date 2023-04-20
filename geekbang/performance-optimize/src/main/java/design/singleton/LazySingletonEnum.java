package design.singleton;

/**
 * 跟内部静态类实现一样
 * @author jianchengwang
 * @date 2023/4/20
 */
public final class LazySingletonEnum {
    private LazySingletonEnum (){}

    public static LazySingletonEnum getInstance(){
        return SinletonEnum.SINLETON.getInstance();
    }

    private enum SinletonEnum {
        SINLETON;

        private LazySingletonEnum singleton;

        // JVM保证这个方法只调用一次
        SinletonEnum(){
            singleton = new LazySingletonEnum();
        }

        public LazySingletonEnum getInstance(){
            return singleton;
        }
    }
}
