package guava.cache;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by pei.xu on 2015/12/9.
 */
public class LocalCacheTest {

    private final static LoadingCache<Object, Object> load = CacheBuilder.newBuilder().build(new CacheLoader<Object, Object>() {
        @Override
        public Object load(Object key) throws Exception {
            System.out.print(">>>>>>");
            return "value:" + key + ";";
        }

        @Override
        public Map<Object, Object> loadAll(Iterable<?> keys) throws Exception {
            Map<Object, Object> tmp = new HashMap<Object, Object>();
            for (Object obj : keys) {
                tmp.put(obj, obj + ":hello!");
            }

            return tmp;
        }

        @Override
        public ListenableFuture<Object> reload(Object key, Object oldValue) throws Exception {
            return super.reload(key, oldValue);
        }
    });

    public void testLocalCache() throws ExecutionException {
        System.out.println(load.get("aa"));
        System.out.println(load.get("bb", new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "aa";
            }
        }));
        System.out.println(load.get("bb", new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "aa1";
            }
        }));


        List<String> list = new ArrayList<String>();
        list.add("ccc");
        list.add("ddd");
        list.add("eee");

        load.getAll(list);
        System.out.println(load.get("ccc"));
        System.out.println(load.get("ddd"));
        System.out.println(load.get("eee"));
    }

    public static void main(String[] args) throws ExecutionException {
        new LocalCacheTest().testLocalCache();
    }

}
