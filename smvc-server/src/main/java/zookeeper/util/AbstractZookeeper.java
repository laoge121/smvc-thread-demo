package zookeeper.util;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by pei.xu on 2016/2/24.
 */
public abstract class AbstractZookeeper implements Watcher {

    //日志
    private Logger logger = LoggerFactory.getLogger(AbstractZookeeper.class);

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    //私有zookeeper
    private ZooKeeper zooKeeper = null;

    public AbstractZookeeper(String hosts) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(hosts, 2000, this);
        countDownLatch.await();
        System.out.println("链接建立成功!");
    }

    public ZooKeeper getZooKeeper() {
        return this.zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {

        //链接状态监控
        if (event.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
            System.out.println("建立简介process event");
        }

        //节点状态 节点创建监控
        if (event.getType() == Event.EventType.NodeCreated) {
            System.out.println("创建路径事件!" + event.getPath());
        }

        //执行 自己的业务逻辑
        this.doProcess(event, this.getZooKeeper());

    }

    public abstract void doProcess(WatchedEvent event, ZooKeeper zooKeeper);

    /**
     * 关闭
     *
     * @throws InterruptedException
     */
    public void zkClose() throws InterruptedException {
        this.zooKeeper.close();
    }
}
