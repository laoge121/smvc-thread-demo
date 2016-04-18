package guava.concurrent.test;

import com.google.common.util.concurrent.Service;
import guava.concurrent.AbstractIdleServiceTest;

import java.util.concurrent.Executors;

/**
 * Created by pei.xu on 2016/3/29.
 */
public class AbstractIdleServiceTestUtil {


    public static void main(String[] args) {

        AbstractIdleServiceTest service = new AbstractIdleServiceTest();
        service.startAsync().awaitRunning();
        System.out.println(Service.State.RUNNING == service.state());
        service.stopAsync().awaitTerminated();
        System.out.println(Service.State.TERMINATED == service.state());
    }

}
