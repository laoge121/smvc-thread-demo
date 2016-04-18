package forkJoin;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Exchanger;

/**
 * Created by pei.xu on 2016/4/18.
 */
public class ExChangeTest {

    private Exchanger<Map<String, Object>> exchanger = new Exchanger<Map<String, Object>>();

    public void test1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> tmp = Maps.newHashMap();
                tmp.put("aa", ">>>>>>>>>>>>>>>>>one thread");
                try {
                    System.out.println(">>>>>>>>1>>>>>>> 准备数据");
                    tmp = exchanger.exchange(tmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(">>>>>>>>1>>>>>>>" + tmp);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> tmp = Maps.newHashMap();
                tmp.put("bb", ">>>>>>>>>>>>>>>>>two thread");
                try {
                    System.out.println(">>>>>>>>2>>>>>>> 准备数据");
                    tmp = exchanger.exchange(tmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(">>>>>>>>2>>>>>>>" + tmp);
            }
        }).start();
    }

    public static void main(String[] args) {

        new ExChangeTest().test1();
    }

}
