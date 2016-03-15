package zookeeper.curatorRecipes.transaction;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.Collection;

/**
 * Created by pei.xu on 2016/3/14.
 */
public class CuratorTransactionTest {

    private CuratorFramework client = null;

    public CuratorTransactionTest(String host) {

        client = CuratorFrameworkFactory.builder().connectString(host).canBeReadOnly(false).retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE)).build();
        client.start();
    }

    public void transactionExecute() {

        CuratorTransaction curatorTransaction = null;

        try {

            curatorTransaction = client.inTransaction();

            Collection<CuratorTransactionResult> results = curatorTransaction.delete().forPath("/test").and().create().forPath("/test", "test".getBytes())
                    .and().create().forPath("/test/data", "nihao".getBytes()).and().commit();

            for (CuratorTransactionResult result : results) {
                System.out.println(result.getForPath() + "===========" + result.getType());
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    public static void main(String[] args) {
        new CuratorTransactionTest("******:****").transactionExecute();
    }
}
