package zookeeper.curatorRecipes.lock;

import org.apache.curator.framework.recipes.locks.InterProcessLock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by pei.xu on 2016/3/14.
 */
public class ReadCuratorLock implements Runnable {


    private final String threadName;

    private final InterProcessLock lock;

    private final int wait_time = 10;

    public ReadCuratorLock(String name, InterProcessLock lock) {
        this.threadName = name;
        this.lock = lock;
    }

    @Override
    public void run() {

        try {
            this.doWork();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doWork() throws Exception {

        try {
            if (!lock.acquire(wait_time, TimeUnit.SECONDS)) {
                System.out.println(threadName + "等待多长时间没有获得锁!");
            }

            //设置线程执行毫秒数
            int nextTime = new Random().nextInt(4000);

            System.out.println(threadName + "开始执行需要消耗多少秒!" + nextTime + "秒");

            Thread.sleep(nextTime);
        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            lock.release();
        }
    }
}
