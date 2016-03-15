package zookeeper;

import com.google.common.collect.Lists;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * zk 测试
 * Created by pei.xu on 2015/12/28.
 */
public class ZookeeperTest1 {


    public static Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println(watchedEvent.toString());
        }
    };

    public ZooKeeper zooKeeperClient = null;

    public void zookeeperClient(String i) throws IOException, KeeperException, InterruptedException, NoSuchAlgorithmException {

        System.out.println("建立 zookeeper 的链接! ");

        List<ACL> aclList = Lists.newArrayList();

        aclList.add(new ACL(ZooDefs.Perms.ALL, new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin"))));
        aclList.add(new ACL(ZooDefs.Perms.READ, new Id("digest", DigestAuthenticationProvider.generateDigest("guest:guest"))));

        zooKeeperClient = new ZooKeeper("127.0.0.1:2181", 30000, watcher);
        //zooKeeperClient.addAuthInfo("digest", "admin:admin".getBytes());
        System.out.println("创建 节点! ");
        zooKeeperClient.create("/head/data", "aaa".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println("获取 节点 数据! ");
        System.out.println(zooKeeperClient.getData("/head", true, null).toString());

        System.out.println("删除 节点! ");
        zooKeeperClient.delete("/head/data", -1);
//
        System.out.println("查看 节点状态! ");
        System.out.println(zooKeeperClient.exists("/head", true));

        List<String> retList = zooKeeperClient.getChildren("/head", true);

    }


    public static void main(String[] args) throws InterruptedException, IOException, KeeperException, NoSuchAlgorithmException {
        new ZookeeperTest1().zookeeperClient("a");
    }

}
