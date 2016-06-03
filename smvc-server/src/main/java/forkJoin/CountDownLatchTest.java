package forkJoin;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 栓操作
 * User: pei.xu
 * Date: 15-5-11
 * Time: 下午3:23
 */
public class CountDownLatchTest {


    public threadRun getThreadRun(CountDownLatch startLatch, CountDownLatch endLatch, int i) {
        return new threadRun(startLatch, endLatch, i);
    }


    public static void main(String[] args) {

        int size = 4;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(new CountDownLatchTest().getThreadRun(startLatch, endLatch, i)).start();
        }

        System.out.println(">>>>>>>>>go");
        //startLatch.countDown();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    class threadRun implements Runnable {

        CountDownLatch startLatch;

        CountDownLatch endLatch;

        int i = 0;

        threadRun(CountDownLatch startLatch, CountDownLatch endLatch, int i) {
            this.startLatch = startLatch;
            this.endLatch = endLatch;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                //startLatch.wait();
                System.out.println("线程" + i + "等等…………");
                endLatch.countDown();
                System.out.println(">>>>");
            } catch (Exception e) {
                //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }

}
