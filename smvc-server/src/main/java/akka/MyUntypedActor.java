package akka;

import akka.actor.UntypedActor;

/**
 * User: pei.xu
 * Date: 15-1-26
 * Time: 下午5:23
 */
public class MyUntypedActor extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {

        System.out.println("test");
    }
}
