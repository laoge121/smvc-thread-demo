package zookeeper.curatorRecipes.cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;

import java.io.Closeable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 节点数据变化 监听测试类处理
 * <p>
 * NodeCache
 * NodeCacheListener
 * ChildData
 * <p>
 * Created by pei.xu on 2016/3/15.
 */
public class NodeCacheExample implements Closeable{

    private CuratorFramework client = null;

    private NodeCache nodeCache = null;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private String path = "/nodeCache";

    public NodeCacheExample(String address) throws Exception {

        client = CuratorFrameworkFactory.builder().connectString(address).sessionTimeoutMs(30000).
                connectionTimeoutMs(30000).canBeReadOnly(false).retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                if (newState.isConnected()) {
                    System.out.println("client connected success!");
                    countDownLatch.countDown();
                }
            }
        });

        client.start();
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(">>>>>>>链接超时线程中断!<<<<<<<<<<<<<<");
            throw new RuntimeException(">>>>>>>链接超时线程中断!<<<<<<<<<<<<<<");
        }
        nodeCache = new NodeCache(client, path);
        this.addListener(nodeCache);

        nodeCache.start();
    }

    public void addListener(final NodeCache nodeCache) {
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {

                System.out.println(">>>>>>>>>>>>>>> nodeCache 事件监控!");

                System.out.println("Node changed: " + nodeCache.getCurrentData().getPath() + ", value: " + new String(nodeCache.getCurrentData().getData()));
            }
        };

        nodeCache.getListenable().addListener(nodeCacheListener);
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }

    public NodeCache getNodeCache() {
        return nodeCache;
    }

    public void setNodeCache(NodeCache nodeCache) {
        this.nodeCache = nodeCache;
    }

    public void close() {
        CloseableUtils.closeQuietly(client);
        CloseableUtils.closeQuietly(nodeCache);
    }

    public static void main(String[] args) throws Exception {
        NodeCacheExample nodeCacheExample = new NodeCacheExample("****:**");

        CuratorFramework client = nodeCacheExample.getClient();

        //client.create().creatingParentsIfNeeded().forPath("/nodeCache");

        client.setData().forPath("/nodeCache", "nihao".getBytes());
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/nodeCache/data1", "nihao".getBytes());
        client.setData().forPath("/nodeCache/data1", "nihao".getBytes());
        CloseableUtils.closeQuietly(nodeCacheExample);
    }

}
