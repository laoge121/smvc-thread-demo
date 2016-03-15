package guava.cache;

import com.google.common.cache.CacheLoader;

import java.util.Map;

/**
 * Created by pei.xu on 2015/12/9.
 */
public class CacheLoaderAll extends CacheLoader {
    @Override
    public Object load(Object key) throws Exception {
        return null;
    }

    @Override
    public Map loadAll(Iterable keys) throws Exception {
        return super.loadAll(keys);
    }
}
