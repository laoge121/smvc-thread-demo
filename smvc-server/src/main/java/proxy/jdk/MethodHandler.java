package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by pei.xu on 2015/11/5.
 */
public class MethodHandler implements InvocationHandler {

    private Object target;


    public MethodHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">>>>>>>>>>>start");
        Object object = method.invoke(target, args);
        System.out.println(">>>>>>>>>>>end");

        return object;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), this.target.getClass().getInterfaces(), this);
    }

}
