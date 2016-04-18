package zookeeper.curatorRecipes.EphemeralNode;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.nodes.PersistentEphemeralNode;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * Created by pei.xu on 2016/3/16.
 */
public class EphemeralNodeExample {


    private CuratorFramework client = null;


    private PersistentEphemeralNode ephemeralNode = null;

    public EphemeralNodeExample(String address) throws Exception {

        client = CuratorFrameworkFactory.builder().connectString(address).sessionTimeoutMs(30000).
                connectionTimeoutMs(30000).canBeReadOnly(false).retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        client.start();

        ephemeralNode = new PersistentEphemeralNode(client, PersistentEphemeralNode.Mode.EPHEMERAL, "/example/ephemeralNode", "test".getBytes());

        ephemeralNode.start();

        ephemeralNode.waitForInitialCreate(10, TimeUnit.SECONDS);

        String path = ephemeralNode.getActualPath();

        System.out.println("》》》》》》》》》》》" + path);

        client.create().withMode(CreateMode.EPHEMERAL).forPath("/example/node", "test1".getBytes());

        System.out.println("node " + "example/node" + " value: " + new String(client.getData().forPath("/example/node")));
        client.close();
        client = CuratorFrameworkFactory.builder().connectString(address).sessionTimeoutMs(30000).
                connectionTimeoutMs(30000).canBeReadOnly(false).retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        System.out.println("node " + "one" + " doesn't exist: " + (client.checkExists().forPath("/example/ephemeralNode") == null));
        System.out.println("node " + "two" + " value: " + new String(client.getData().forPath("/example/node")));

        CloseableUtils.closeQuietly(ephemeralNode);
        CloseableUtils.closeQuietly(client);

    }

    public static void main(String[] args) throws Exception {
        new EphemeralNodeExample("************:****");
    }
}
