package zookeeper.curatorRecipes.Elections;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 当前方法的实现是轮询 选举的 即 轮流做leader
 * Created by pei.xu on 2016/3/14.
 */
public class ElectionLeaderSelector extends LeaderSelectorListenerAdapter implements Closeable {

    private String name;

    private LeaderSelector leaderSelector = null;

    private Semaphore semaphore = new Semaphore(0);

    private final String path = "/test";

    private int num = 0;

    public ElectionLeaderSelector(int id, String name, CuratorFramework curatorFramework) {
        this.num = id;
        this.name = name;
        leaderSelector = new LeaderSelector(curatorFramework, path, this);
        leaderSelector.autoRequeue();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void start() throws IOException {
        leaderSelector.start();
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    public LeaderSelector getLeaderSelector() {
        return leaderSelector;
    }

    public void setLeaderSelector(LeaderSelector leaderSelector) {
        this.leaderSelector = leaderSelector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        System.out.println(this.name + "当前clent 是 leader!");
        try {
            semaphore.acquire();
        } catch (Exception e) {
            semaphore.release();
            //Thread.currentThread().interrupt();
        } finally {
            System.out.println(this.name + "让出领导权！");
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
