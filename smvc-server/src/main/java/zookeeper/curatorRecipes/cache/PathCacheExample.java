package zookeeper.curatorRecipes.cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Path Cache用来监控一个ZNode的子节点. 当一个子节点增加， 更新，删除时，
 * Path Cache会改变它的状态， 会包含最新的子节点， 子节点的数据和状态。 这也正如它的名字表示的那样， 那监控path。
 * <p>
 * PathChildrenCache
 * PathChildrenCacheEvent
 * PathChildrenCacheListener
 * ChildData
 * <p>
 * 路径测试cache
 * <p>
 * Created by pei.xu on 2016/3/15.
 */
public class PathCacheExample {


    private CuratorFramework client = null;

    private PathChildrenCache pathChildrenCache = null;

    //private CountDownLatch countDownLatch = new CountDownLatch(1);

    private Semaphore semaphore = new Semaphore(0,true);

    private String path = "/pathCache";

    public PathCacheExample(String address) throws Exception {

        client = CuratorFrameworkFactory.builder().connectString(address).sessionTimeoutMs(30000).
                connectionTimeoutMs(30000).canBeReadOnly(false).retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                if (newState.isConnected()) {
                    System.out.println("client connected success!");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //countDownLatch.countDown();
                    semaphore.release();
                }
            }
        });

        client.start();
        semaphore.acquire();
       // countDownLatch.await();

        pathChildrenCache = new PathChildrenCache(client, path, true);
        this.addListener(pathChildrenCache);

        pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
    }

    public void addListener(PathChildrenCache cache) {

        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED: {
                        System.out.println("CHILD_ADDED" + ">>>>>>>>>>>>>>>>>>>" + event.getData().getPath());
                        break;
                    }
                    case CHILD_REMOVED: {
                        System.out.println("CHILD_REMOVED" + ">>>>>>>>>>>>>>>>>>>" + event.getData().getPath());
                        break;
                    }
                    case CHILD_UPDATED: {
                        System.out.println("CHILD_UPDATED" + ">>>>>>>>>>>>>>>>>>>" + event.getData().getPath());
                        break;
                    }
                }

                System.out.println("<><<<<<<<<" + event.getType());
            }
        };

        cache.getListenable().addListener(pathChildrenCacheListener);
    }

    public void close() {
        CloseableUtils.closeQuietly(client);
        CloseableUtils.closeQuietly(pathChildrenCache);
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }

    public PathChildrenCache getPathChildrenCache() {
        return pathChildrenCache;
    }

    public void setPathChildrenCache(PathChildrenCache pathChildrenCache) {
        this.pathChildrenCache = pathChildrenCache;
    }

    public static void main(String[] args) throws Exception {

        PathCacheExample pathCacheExample = new PathCacheExample("*******:****");

        CuratorFramework client = pathCacheExample.getClient();

        //PathChildrenCache cache = pathCacheExample.getPathChildrenCache();

       // pathCacheExample.addListener(cache);

        //client.create().creatingParentsIfNeeded().forPath("/pathCache");
        client.setData().forPath("/pathCache", "niaho".getBytes());
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/pathCache/data1");

        pathCacheExample.close();
    }

}
