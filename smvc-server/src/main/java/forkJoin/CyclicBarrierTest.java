package forkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * User: pei.xu
 * Date: 15-1-27
 * Time: 下午3:52
 */
public class CyclicBarrierTest {

    public static ExecutorService executorService = Executors.newCachedThreadPool();

    //目的是等待需要统计的线程都执行完以后在执行回调的 方法
    public static CyclicBarrier cyclicBarraier;


    class CallableData implements Callable<Object> {

        private int a = 0;

        private int b = 0;

        CallableData(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Object call() throws Exception {
            System.out.println("please wating ………………");
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am ok !");
            try {
                cyclicBarraier.await();
                System.out.println("<<<");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public void execute() {

        //注意这个地方的4的设置这个4 如果添加的线程不到4个那么用于等下去
        cyclicBarraier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("go all ready!");
            }
        });

        List<Callable<Object>> paramList = new ArrayList<Callable<Object>>();
        for (int i = 0; i < 4; i++) {
            paramList.add(new CallableData(1, 2));
        }
        try {
            executorService.invokeAll(paramList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new CyclicBarrierTest().execute();
    }
}
