package com.yn.springapp.util.queue;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * User: pei.xu
 * Date: 15-2-3
 * Time: 下午12:18
 */
public class QueueExecuteThread {

    private static Logger logger = Logger.getLogger(QueueExecuteThread.class.getName());

    /**
     * 用于控制顺序执行的队列
     */
    private static LinkedBlockingQueue<Callable<Object>> linkedQueue = new LinkedBlockingQueue(10000);

    /**
     * 并发排队数据返回结果处理
     */
    private static ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<String, Object>();

    /**
     * 缓存线程池
     */
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static {
        execute();
        logger.info("队列任务启动!");
    }

    public static void addResultInfo(String key, Object obj) {
        concurrentHashMap.put(key, obj);
    }

    public static Object getResultInfo(String key) {
        return concurrentHashMap.get(key);
    }

    public static void removeKey(String key) {
        concurrentHashMap.remove(key);
    }

    public static void addExecuteInfo(Callable<Object> callable) {
        linkedQueue.offer(callable);
    }

    static class LinkQueueThread extends Thread {

        LinkQueueThread() {
        }

        @Override
        public void run() {
            Callable<Object> retCallable = null;
            try {
                retCallable = linkedQueue.take();
            } catch (InterruptedException e) {
                logger.info("获取队列数据异常!" + e.getMessage());
            }
            while (null != retCallable) {
                Future<Object> future = executorService.submit(retCallable);
                try {
                    future.get();
                    retCallable = linkedQueue.take();
                } catch (InterruptedException e) {
                    logger.info("获取队列数据异常!" + e.getMessage());
                } catch (ExecutionException e) {
                    logger.info("线程池执行线程异常!" + e.getMessage());
                }
            }
        }
    }


    public static void execute() {
        new LinkQueueThread().start();
    }
}
