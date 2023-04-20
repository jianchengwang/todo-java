package design.singleton;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
public final class LazySingletonInnerClass {
    private static class SingletonHolder {
        private static final LazySingletonInnerClass INSTANCE = new LazySingletonInnerClass();
    }

    public static LazySingletonInnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
