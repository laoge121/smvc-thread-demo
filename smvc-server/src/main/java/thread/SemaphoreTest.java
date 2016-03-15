package thread;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 * 信号量的使用
 * User: pei.xu
 * Date: 15-4-29
 * Time: 下午3:47
 */
public class SemaphoreTest {

    //设置可用的信号量
    private static final int MAX_AVAILABLE = 10;

    //设置信号量
    public static final Semaphore semaphoreAvailable = new Semaphore(MAX_AVAILABLE, true);

    //集合数据
    public static List<Object> objectsLinkedQueue = null;

    private static boolean[] used = new boolean[MAX_AVAILABLE];

    //初始化链接
    static {
        objectsLinkedQueue = new ArrayList<Object>();
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            tmpMap.put("data", ">>>>>>>>>>>>>>>" + i);
            tmpMap.put("index", i);
            objectsLinkedQueue.add(tmpMap);

            //初始化站位标识都默认初始化可用
            used[i] = true;
        }
    }

    public static Map<String, Object> getObject() throws InterruptedException {
        semaphoreAvailable.acquire();
        return campData();
    }

    private static synchronized Map<String, Object> campData() {
        Map<String, Object> retMap = null;
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            if (used[i] == true) {
                retMap = (Map<String, Object>) objectsLinkedQueue.get(i);
                used[i] = false;
                break;
            }
        }
        return retMap;
    }

    public static void relaseObject(Map<String, Object> relaseMap) {
        used[(Integer) relaseMap.get("index")] = true;
        semaphoreAvailable.release();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 101; i++) {
            Map<String, Object> tmpMap = SemaphoreTest.getObject();
            System.out.println(tmpMap.get("data"));
        }
    }
}
