package event;

import org.springframework.context.ApplicationListener;

/**
 * Created by pei.xu on 2015/10/14.
 */
public class ClientListener implements ApplicationListener<ClientTestEvent> {
    @Override
    public void onApplicationEvent(ClientTestEvent event) {

        System.out.println("测试event");

        System.out.println(event.getDataMap());

    }
}
