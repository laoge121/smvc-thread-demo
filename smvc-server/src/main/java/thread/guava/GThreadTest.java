package thread.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * Created by pei.xu on 2015/12/15.
 */
public class GThreadTest {


    public void test() throws ExecutionException, InterruptedException {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        ListenableFuture<String> listenableFuture = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "aaaa";
            }
        });

        System.out.println(listenableFuture.get());

        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.print("返回结果集：" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("返回结果集：" + t);
            }
        });

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new GThreadTest().test();
    }
}
