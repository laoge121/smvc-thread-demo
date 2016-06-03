package sThread;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by pei.xu on 2015/12/1.
 */
public class SThreadPool implements Runnable, Callable {

    private static String[] str = new String[10];

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static ConcurrentLinkedQueue<Thread> wConcurrentLinkedQueue = new ConcurrentLinkedQueue();
    private static ConcurrentLinkedQueue<Thread> rConcurrentLinkedQueue = new ConcurrentLinkedQueue();

    @Override
    public void run() {

    }

    @Override
    public Object call() throws Exception {
        return null;
    }

    public static void main(String[] args) {

        Thread write = new Thread(new Runnable() {
            @Override
            public void run() {

                if (StringUtils.isNotBlank(str[10])) {
                    try {
                        Thread.currentThread().wait();
                        wConcurrentLinkedQueue.add(Thread.currentThread());
                        for (Thread thread : rConcurrentLinkedQueue) {
                            thread.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                readWriteLock.writeLock().lock();
                int i = 0;
                for (String s : str) {
                    if (StringUtils.isBlank(s)) {
                        str[i] = "bb";
                    }
                    i++;
                }
                readWriteLock.writeLock().unlock();
            }
        });

        Thread read = new Thread(new Runnable() {
            @Override
            public void run() {

                if (StringUtils.isBlank(str[10])) {
                    try {
                        Thread.currentThread().wait();
                        rConcurrentLinkedQueue.add(Thread.currentThread());
                        for (Thread thread : wConcurrentLinkedQueue) {
                            thread.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                readWriteLock.readLock().lock();
                int i = 0;
                for (String s : str) {
                    if (StringUtils.isNotBlank(s)) {
                        str[i] = "";
                        break;
                    }
                    i++;
                }
                readWriteLock.readLock().unlock();
            }
        });
    }
}
