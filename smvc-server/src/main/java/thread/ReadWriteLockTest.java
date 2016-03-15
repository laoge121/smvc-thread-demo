package thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: pei.xu
 * Date: 15-1-5
 * Time: 下午5:04
 */
public class ReadWriteLockTest implements Runnable {

    public static final ReadWriteLock readwriteLock = new ReentrantReadWriteLock();

    public static Map<String, String> data = new HashMap<String, String>();

    private boolean isWrite = false;


    public ReadWriteLockTest(boolean isWrite) {

        System.out.println("<<<<<<设置数据" + isWrite);

        this.isWrite = isWrite;
    }

    public static void main(String[] args) {
        new Thread(new ReadWriteLockTest(false)).start();
        new Thread(new ReadWriteLockTest(true)).start();
        new Thread(new ReadWriteLockTest(true)).start();


    }

    @Override
    public void run() {

        System.out.println("<<<<<<获取状态" + isWrite);


        if (isWrite) {

            readwriteLock.writeLock().lock();
            System.out.println("><<<<<<<<<");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ReadWriteLockTest.data.put("aa", "bb");

            System.out.println("<<<<<<设置数据" + ReadWriteLockTest.data);

            readwriteLock.writeLock().unlock();

        } else {
            System.out.println(">>>>>>>>>>>度");
            readwriteLock.readLock().lock();

            System.out.println(">>>>>读取>>>>>>" + ReadWriteLockTest.data);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readwriteLock.readLock().unlock();

        }

    }
}
