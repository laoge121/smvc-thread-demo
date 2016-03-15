package zookeeper.curatorRecipes.Elections;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by pei.xu on 2016/3/14.
 */
public class ElectionLeaderLatch implements LeaderLatchListener, Closeable {

    @Override
    public void close() throws IOException {

    }

    @Override
    public void isLeader() {

    }

    @Override
    public void notLeader() {

    }
}
