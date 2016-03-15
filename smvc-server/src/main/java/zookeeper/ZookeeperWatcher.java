package zookeeper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * zookeoper 观察者模式
 * Created by pei.xu on 2016/1/18.
 */
public class ZookeeperWatcher {

    private ZooKeeper zooKeeperClient = null;


    public void zkWatcher() throws IOException, NoSuchAlgorithmException {
        zooKeeperClient = new ZooKeeper("127.0.0.1:2181", 30000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    System.out.println(event.getType().toString());
                    System.out.println(event.getState().toString());
                    repeatWatch(zooKeeperClient);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        //List<ACL> aclList = Lists.newArrayList();

        // aclList.add(new ACL(ZooDefs.Perms.ALL, new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin"))));
        //aclList.add(new ACL(ZooDefs.Perms.READ, new Id("digest", DigestAuthenticationProvider.generateDigest("guest:guest"))));
        //zooKeeperClient.addAuthInfo("digest", "admin:admin".getBytes());
    }

    public void repeatWatch(ZooKeeper zooKeeper) throws KeeperException, InterruptedException, UnsupportedEncodingException {
        List<String> retList = zooKeeper.getChildren("/zkHead", true);
        List<Map<String, Object>> tmpList = Lists.newArrayList();
        for (String str : retList) {
            Map<String, Object> tmp = Maps.newHashMap();
            byte[] b = zooKeeper.getData("/zkHead" + "/" + str, false, null);
            tmp.put(str, new String(b, "utf-8"));
            tmpList.add(tmp);
        }
        System.out.println(tmpList);
    }

    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {
        ZookeeperWatcher zookeeperWatcher = new ZookeeperWatcher();
        zookeeperWatcher.zkWatcher();

        Thread.sleep(Long.MAX_VALUE);
    }

}
