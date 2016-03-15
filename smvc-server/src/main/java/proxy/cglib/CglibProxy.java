package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by pei.xu on 2015/11/5.
 */
public class CglibProxy implements MethodInterceptor {

    private Object taget;


    public Object createCglibProxy(Object target) {
        this.taget = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        enhancer.setClassLoader(target.getClass().getClassLoader());
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(">>>>>>>>>>>>>>start");

        Object ret = methodProxy.invokeSuper(o, objects);

        System.out.println(">>>>>>>>>>>>>>end");
        return ret;
    }
}
