package zookeeper.curatorRecipes;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by pei.xu on 2016/3/10.
 */
public class CuratorRecipes {


    public CuratorFramework client = null;

    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }

    public CuratorRecipes(String address) {
        client = CuratorFrameworkFactory.builder().connectString(address).sessionTimeoutMs(30000).
                connectionTimeoutMs(30000).canBeReadOnly(false).retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
                .build();
        client.start();
    }


    public void createPath(String path, String data) throws Exception {

        client.create().forPath(path, data.getBytes());
    }


    public void getPathData(String path) throws Exception {
        byte[] b = client.getData().forPath(path);
        System.out.println(new String(b));
    }

    public void setData(String path, String data) throws Exception {
        client.setData().forPath(path, data.getBytes());
    }

    public void getDataBackGround(String path) throws Exception {
        client.getData().watched().inBackground().forPath(path);
    }

    public void deletePath(String path) throws Exception {
        client.delete().forPath(path);
    }

    public Stat getPathStat(String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        return stat;
    }

    public List<String> getChild(String path) throws Exception {

        return client.getChildren().forPath(path);
    }

    public void close() {
        CloseableUtils.closeQuietly(client);
    }

    public static void main(String[] args) throws Exception {
        CuratorRecipes curatorRecipes = new CuratorRecipes("******:****");

        CuratorFramework client = curatorRecipes.getClient();
        client.getZookeeperClient().getZooKeeper();

        curatorRecipes.createPath("/test", "nihao");

        curatorRecipes.close();
    }
}
