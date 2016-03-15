package thread;

import com.yn.springapp.util.http.HttpClientUntil3;

import java.net.URLEncoder;
import java.util.concurrent.*;

/**
 * User: pei.xu
 * Date: 15-6-12
 * Time: 下午3:19
 */
public class ThreadPoolExecutorTest {

    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    class aa implements Callable<Object> {
        @Override
        public Object call() throws Exception {
            String str = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=" + URLEncoder.encode("看书", "UTF-8") + "&bk_length=600";
            return HttpClientUntil3.requestUrl(str, null);

        }
    }


//    public void test() throws ExecutionException, InterruptedException {
//
//        int i = 0;
//        List<Callable<Object>> paramList = new ArrayList<>();
//        while (true) {
//            i++;
//            paramList.add(new aa());
//
//            if (i % 5000 == 0) {
//                List<Future<Object>> futureList = executorService.invokeAll(paramList);
//                futureList.forEach(a -> {
//                    try {
//                        System.out.println(HttpClientUntil3.unicodeToUtf8((String) (a.get())));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                });
//                Thread.sleep(1000);
//                paramList.clear();
//            }
//        }
//    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
       // new ThreadPoolExecutorTest().test();

    }
}
