package zookeeper.curatorRecipes.Elections;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by pei.xu on 2016/3/30.
 */
public class ElectionLeaderLatchTest {

    public static CuratorFramework getClient(String address) {
        return CuratorFrameworkFactory.builder().connectString(address).sessionTimeoutMs(30000).
                connectionTimeoutMs(30000).canBeReadOnly(false).retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
                .build();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        List<CuratorFramework> clients = Lists.newArrayList();

        List<ElectionLeaderLatch> examples = Lists.newArrayList();
        try {
            for (int i = 0; i < 10; i++) {

                CuratorFramework client = ElectionTest.getClient("*******:****");
                final ElectionLeaderLatch electionLeaderSelector = new ElectionLeaderLatch(client, i);
                client.getCuratorListenable().addListener(new CuratorListener() {

                    @Override
                    public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                        if (event.getType() == CuratorEventType.CLOSING) {
                            //electionLeaderSelector.getSemaphore().release();
                        }
                    }
                });
                clients.add(client);

                examples.add(electionLeaderSelector);

                client.start();
            }
            System.out.println("----------先观察一会选举的结果-----------");
            Thread.sleep(10000);

            System.out.println("----------关闭当前的leader的connection-----------");
            for (ElectionLeaderLatch el : examples) {

                //如果当前是leader 那么关闭当前leader
                if (el.getLeaderLatch().hasLeadership()) {
                    clients.get(el.getNum()).close();
                    break;
                }
            }

            System.out.println("----------关闭前5个客户端，再观察选举的结果-----------");
            //for (int i = 0; i < 5; i++) {
            //    clients.get(i).close();
            //}

            //等待键盘输入enter 或者esc

            new BufferedReader(new InputStreamReader(System.in)).readLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (ElectionLeaderLatch exampleClient : examples) {
                CloseableUtils.closeQuietly(exampleClient.getLeaderLatch());
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
        }
    }
}

