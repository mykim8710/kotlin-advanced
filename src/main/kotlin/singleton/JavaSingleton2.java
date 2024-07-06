package singleton;

public class JavaSingleton2 {
    private JavaSingleton2() {}

    public JavaSingleton2 getInstance() {
        // 지연 초기화
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final JavaSingleton2 INSTANCE = new JavaSingleton2();
    }
}
