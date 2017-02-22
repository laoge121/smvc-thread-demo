package forkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * User: pei.xu
 * Date: 15-1-27
 * Time: 下午2:00
 */
public class ForkJoinTest {

    private static ForkJoinPool pool = new ForkJoinPool();

    private static int aa = 0;

    class test extends RecursiveAction{

        @Override
        protected void compute() {

        }
    }

    class ForkJoinInfo extends RecursiveTask<Object> {

        private int a = 0;

        private int b = 0;

        private String name = "";

        ForkJoinInfo(int a, int b, String name) {
            this.a = a;
            this.b = b;
            this.name = name;
        }

        @Override
        protected Object compute() {
            Object sa = 0;
            if (a > b) {
                //sa = new ForkJoinInfo(1, 2).invoke();//.fork().join();
                List<RecursiveTask<Object>> paramList = new ArrayList<RecursiveTask<Object>>();
                for (int i = 0; i < 10; i++) {
                    paramList.add(new ForkJoinInfo2(i, i + 1));
                }
                List<RecursiveTask<Object>> retList = (List<RecursiveTask<Object>>) ForkJoinTask.invokeAll(paramList);
                for (RecursiveTask recursiveTask : retList) {
                    System.out.println(name + ":" + recursiveTask.join());
                }
            }

            return a + b + (Integer) sa;
        }
    }


    class ForkJoinInfo2 extends RecursiveTask<Object> {

        private int a = 1;

        private int b = 0;

        ForkJoinInfo2(int a1, int a2) {
            this.a = a1;
            this.b = a2;
        }

        @Override
        protected Object compute() {
            System.out.println("this is " + (a + b));
            return a + b;
        }
    }

    public void execute() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            //ForkJoinTask<Object> object = pool.submit(new ForkJoinInfo(3, 2, "name" + i + "Test"));
            ForkJoinTask<Object> object = pool.submit(new ForkJoinInfo(3, 2, "name" + i + "Test"));
            System.out.println(object.get());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new ForkJoinTest().execute();
    }
}
