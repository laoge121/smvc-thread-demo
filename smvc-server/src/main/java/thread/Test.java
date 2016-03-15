package thread;

import java.util.concurrent.*;

/**
 * Created by pei.xu on 2015/10/12.
 */
public class Test {


    public static void main(String[] args) throws InterruptedException {

//        CountDownLatch countDownLatch = new CountDownLatch(2);
//
//        System.out.println("aaa");
//        for (int i = 0; i < 2; i++) {
//
//            new Thread(new TestCountDownLatch(countDownLatch)).start();
//
//        }
//        System.out.println("ccc");
//        countDownLatch.await();
//        System.out.println("bbb");

//        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println("cyclicBarrier end!");
//            }
//        });
//        for (int i = 0; i < 3; i++) {
//            new Thread(new CyclicBarrierTest(cyclicBarrier)).start();
//        }



        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 1000 * 10, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));
        //threadPoolExecutor.


    }

    static class CyclicBarrierTest implements Runnable {

        private CyclicBarrier cyclicBarrier = null;

        CyclicBarrierTest(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("waiting……&……");
            try {
                cyclicBarrier.await();
                System.out.println("other thead start1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class TestCountDownLatch implements Runnable {

        private CountDownLatch countDownLatch = null;

        TestCountDownLatch(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            System.out.println("???????????????");
            countDownLatch.countDown();
            System.out.println("<<<<<<>>>>>>>");

        }
    }

}
