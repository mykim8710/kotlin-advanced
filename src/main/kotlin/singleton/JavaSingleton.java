package singleton;

public class JavaSingleton {
    private static final JavaSingleton INSTANCE = new JavaSingleton();

    private JavaSingleton() {}

    public JavaSingleton getInstance() {
        // 이른 초기화
        return INSTANCE;
    }

}
