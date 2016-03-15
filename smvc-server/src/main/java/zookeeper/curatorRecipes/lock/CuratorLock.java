package zookeeper.curatorRecipes.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pei.xu on 2016/3/14.
 */
public class CuratorLock {

    private static final CuratorFramework client;

    private static final InterProcessReadWriteLock lock;

    static {
        client = CuratorFrameworkFactory.builder().connectString("******:****")
                .sessionTimeoutMs(30000)
                .connectionTimeoutMs(30000)
                .canBeReadOnly(false)
                .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
                .defaultData(null)
                .build();
        client.start();
        lock = new InterProcessReadWriteLock(client, "/test");
        //readLock = lock.readLock();
        // writeLock = lock.writeLock();
    }

    public static void main(String[] args) {
        try {
            List<Thread> jobs = new ArrayList<Thread>();

            for (int i = 0; i < 10; i++) {
                Thread t = new Thread(new ReadCuratorLock("Parallel任务" + i, lock.readLock()));
                jobs.add(t);
            }

            for (int i = 0; i < 10; i++) {
                Thread t = new Thread(new WriteCuratorLock("Mutex任务" + i, lock.writeLock()));
                jobs.add(t);
            }

            for (Thread t : jobs) {
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }


    }
}
