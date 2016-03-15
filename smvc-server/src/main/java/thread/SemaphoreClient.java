package thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pei.xu
 * Date: 15-4-29
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public class SemaphoreClient {
    public static void main(String[] args) {
        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("data", ">>>>>>>>>>>>>>>" + 5);
        tmpMap.put("index", 1);
        SemaphoreTest.relaseObject(tmpMap);
    }
}
