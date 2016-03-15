package zookeeper.util;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by pei.xu on 2016/2/24.
 */
public class ZookeeperOperator extends AbstractZookeeper {

    private Logger logger = LoggerFactory.getLogger(ZookeeperOperator.class);

    public ZookeeperOperator(String hosts) throws IOException, InterruptedException {
        super(hosts);
    }


    /**
     * 默认任何人都能看到 并且 链接断掉不删除数据
     *
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void createPath(String path, byte[] data) throws KeeperException, InterruptedException {
        this.getZooKeeper().create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void getChild(String path) throws KeeperException, InterruptedException {
        List<String> list = this.getZooKeeper().getChildren(path, true);
        if (list.isEmpty()) {
            System.out.println("中没有节点");
            logger.debug(path + "中没有节点");
        } else {
            logger.debug(path + "中存在节点");
            for (String child : list) {
                System.out.println("节点为：" + child);
                logger.debug("节点为：" + child);
            }
        }
    }


    @Override
    public void doProcess(WatchedEvent event, ZooKeeper zooKeeper) {

        System.out.println("ZookeeperOperator.end");
    }


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        ZookeeperOperator zookeeperOperator = new ZookeeperOperator("127.0.0.1:2181");

        zookeeperOperator.getZooKeeper().delete("/zkHead/child1", -1);
        //zookeeperOperator.getZooKeeper().delete("/zkHead", -1);

        //zookeeperOperator.createPath("/zkHead", null);
        //zookeeperOperator.getChild("/zkHead");

        //zookeeperOperator.createPath("/zkHead/child4", new byte[]{'c', 'f'});
        zookeeperOperator.getChild("/zkHead");

        zookeeperOperator.zkClose();
    }
}
