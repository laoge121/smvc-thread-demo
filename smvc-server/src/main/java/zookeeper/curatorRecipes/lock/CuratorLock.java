package zookeeper.curatorRecipes.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pei.xu on 2016/3/14.
 */
public class CuratorLock {

    private static final CuratorFramework client;

    private static final InterProcessReadWriteLock lock;

    private static final InterProcessLock readLock;

    private static final InterProcessLock writeLock;

    static {
        client = CuratorFrameworkFactory.builder().connectString("******:****")
                .sessionTimeoutMs(30000)
                .connectionTimeoutMs(30000)
                .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
                .build();
        client.start();
        lock = new InterProcessReadWriteLock(client, "/test");
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }


    public static void close() throws Exception {
        CloseableUtils.closeQuietly(client);
        readLock.release();
        writeLock.release();
    }


    public static void main(String[] args) throws Exception {
        try {
            List<Thread> jobs = new ArrayList<Thread>();

            for (int i = 0; i < 3; i++) {
                Thread t = new Thread(new ReadCuratorLock("Parallel任务" + i, readLock));
                jobs.add(t);
            }

            for (int i = 0; i < 3; i++) {
                Thread t = new Thread(new WriteCuratorLock("Mutex任务" + i, writeLock));
                jobs.add(t);
            }

            for (Thread t : jobs) {
                t.start();
            }

            new BufferedReader(new InputStreamReader(System.in)).readLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CuratorLock.close();
        }


    }
}
