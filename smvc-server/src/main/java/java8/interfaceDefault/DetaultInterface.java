package java8.interfaceDefault;

/**
 * Created by yuhou on 2017/2/22.
 */
public class DetaultInterface {
    public static void main(String[] args) {
        DefaultMethodInterface defaultI = new DefaultMethodInterface() {
            @Override
            public int add2(int a, int b) {
                return 0;
            }
        };
        int add = defaultI.add(1, 2);
        int mult = defaultI.mult(3, 1);
        System.out.println(add + "-" + mult);
    }
}
