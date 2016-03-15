package zookeeper.curatorRecipes.Elections;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 当前方法的实现是轮询 选举的 即 轮流做leader
 * Created by pei.xu on 2016/3/14.
 */
public class ElectionLeaderSelector extends LeaderSelectorListenerAdapter implements Closeable {

    private String name;

    private static LeaderSelector leaderSelector = null;

    private final String path = "/test";


    public ElectionLeaderSelector(String name, CuratorFramework curatorFramework) {
        this.name = name;
        leaderSelector = new LeaderSelector(curatorFramework, path, this);
        leaderSelector.autoRequeue();
    }

    public void start() throws IOException {
        leaderSelector.start();
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        System.out.println(this.name + "当前clent 是 leader!");
        int waitSeconds = (int) (5 * Math.random()) + 1;
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(waitSeconds));

        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(this.name + "让出领导权！");
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
