package memcache;

import net.rubyeye.xmemcached.*;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * User: pei.xu
 * Date: 15-3-9
 * Time: 下午6:57
 */
public class MemcachedTest {

    private static MemcachedClient memcachedClient = null;

    /**
     * 获得xMemcached 数据
     *
     * @param host
     * @param port
     * @return
     */

    public static MemcachedClient getMemCachedInstance(String host, int port) {
        if (null == memcachedClient) {
            MemcachedClientBuilder memcachedClientBuilder = new XMemcachedClientBuilder(AddrUtil.getAddressMap(host + ":" + port + ",127.0.0.1:11211 127.0.0.1:11211,127.0.0.1:11211"));
            memcachedClientBuilder.setCommandFactory(new BinaryCommandFactory());
            memcachedClientBuilder.setSessionLocator(new KetamaMemcachedSessionLocator());

            //启用失效模式为了配置热备模式
            memcachedClientBuilder.setFailureMode(true);
            try {
                memcachedClient = memcachedClientBuilder.build();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return memcachedClient;
    }

    public static void main(String[] args) throws IOException, InterruptedException, MemcachedException, TimeoutException {

        MemcachedClient memcachedClient = MemcachedTest.getMemCachedInstance("127.0.0.1", 11211);
        //memcachedClient.add("aa", 0, 1);
        Object num = memcachedClient.get("aa");

        System.out.println(num);

        //MemcachedTest memcachedTest = new MemcachedTest();

//        memcachedClient.cas("aa", memcachedClient.gets("aa"), new CASOperation<Object>() {
//            @Override
//            public int getMaxTries() {
//                return Integer.MAX_VALUE;
//            }
//
//            @Override
//            public Object getNewValue(long currentCAS, Object currentValue) {
//                return (int) currentValue + 1;
//            }
//        });
//        num = memcachedClient.get("aa");
//        System.out.println("更新后:" + num);


        for (int i = 0; i < 1; i++) {
            // new Thread(memcachedTest.new MemcacheCaseTest()).start();
            break;
        }

        //num = memcachedClient.get("aa");
        //System.out.println("更新后:" + num);
        memcachedClient.shutdown();
    }


    /**
     * memcached Case 验证
     */
    private class MemcacheCaseTest implements Runnable {

        @Override
        public void run() {

            int i = 0;

            while (true) {
                MemcachedClient memcachedClient1 = MemcachedTest.getMemCachedInstance("127.0.0.1", 11211);
                try {
                    memcachedClient1.cas("aa", memcachedClient1.gets("aa"), new CASOperation<Object>() {
                        @Override
                        public int getMaxTries() {
                            return Integer.MAX_VALUE;
                        }

                        @Override
                        public Object getNewValue(long currentCAS, Object currentValue) {
                            return (Integer) currentValue + 1;
                        }
                    });
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MemcachedException e) {
                    e.printStackTrace();
                }
                i++;
                if (i > 10) {
                    break;
                }
            }

        }
    }


}
