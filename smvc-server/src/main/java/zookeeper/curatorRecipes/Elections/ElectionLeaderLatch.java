package zookeeper.curatorRecipes.Elections;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by pei.xu on 2016/3/14.
 */
public class ElectionLeaderLatch implements LeaderLatchListener, Closeable {

    private String path = "/test";

    private int num = 0;

    private LeaderLatch leaderLatch;

    public ElectionLeaderLatch(CuratorFramework curatorFramework, int id) throws Exception {
        this.num = id;

        leaderLatch = new LeaderLatch(curatorFramework, path, "这是第：" + num + "个leader选举!");
        leaderLatch.addListener(this);
        leaderLatch.start();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public LeaderLatch getLeaderLatch() {
        return leaderLatch;
    }

    public void setLeaderLatch(LeaderLatch leaderLatch) {
        this.leaderLatch = leaderLatch;
    }

    @Override
    public void close() throws IOException {
        System.out.println("这是第：" + this.num + "个leader选举类关闭!");
    }

    @Override
    public void isLeader() {
        System.out.println("当前 ：" + this.num + "leader!");
    }

    @Override
    public void notLeader() {
        System.out.println("当前 ：" + this.num + " 不是leader!");
    }
}
