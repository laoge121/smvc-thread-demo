package proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * Created by pei.xu on 2015/11/5.
 */
public class ProxyTest {

    public static void main(String args[]) {
        User user = new UserImpl();
        MethodHandler methodHandler = new MethodHandler(user);

        //User us = (User) methodHandler.getProxy();

        Object o = Proxy.newProxyInstance(User.class.getClassLoader(), user.getClass().getInterfaces(), methodHandler);
        User us = (User) o;
        us.add();
        us.delete();
    }
}
