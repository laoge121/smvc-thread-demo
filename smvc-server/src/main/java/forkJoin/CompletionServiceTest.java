package forkJoin;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: pei.xu
 * Date: 15-1-27
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
public class CompletionServiceTest {

    public static ExecutorService executorService = Executors.newCachedThreadPool();
    public static CompletionService completionService = new ExecutorCompletionService(executorService);


    public void execute() throws InterruptedException, ExecutionException {

        for (int i = 0; i < 10; i++) {
            completionService.submit(new test(i, i + 1));
        }

        for (int i = 0; i < 10; i++) {
            Object object = completionService.take().get();
            if (object instanceof Integer) {
                System.out.println(object);
            }
        }

    }

    class test implements Callable<Object> {

        private int a = 0;

        private int b = 0;

        test(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Object call() throws Exception {
            return a + b;
        }
    }

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        new CompletionServiceTest().execute();

    }

}
