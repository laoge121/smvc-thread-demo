package memcache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pei.xu on 2016/5/25.
 */
public class Test {

    public static void main(String[] args) {
        Map map = new HashMap();
        int a = 0;
        map.put(a, "aa");

        System.out.println(1 << Integer.SIZE-3);

        System.out.println(1212 & ~(1 << Integer.SIZE-1));
        System.out.println(1212 & (1 << Integer.SIZE-1));
        System.out.println(0|2);
    }
}
