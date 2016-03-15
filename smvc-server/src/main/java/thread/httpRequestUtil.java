package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * User: pei.xu
 * Date: 15-1-7
 * Time: 下午12:05
 */
public class httpRequestUtil {


    public void test() {

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        final List<String> list = new ArrayList<String>();
        list.add("aaa");

        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                StringBuffer sb = new StringBuffer();
                for (String str : list) {
                    sb.append(str);
                }
                return sb.toString();
            }
        });
        executor.execute(future);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void test2(){
        ExecutorService executorService = Executors.newWorkStealingPool();
    }

    public static void main(String[] args) {
        new httpRequestUtil().test();
    }
}
