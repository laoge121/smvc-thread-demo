package java8.interfaceDefault;

/**
 * Created by yuhou on 2017/2/22.
 */
public interface DefaultMethodInterface {

    int add2(int a, int b);

    default int add(int a, int b) {
        return a + b;
    }

    default int mult(int a, int b) {
        return a - b;
    }
}
