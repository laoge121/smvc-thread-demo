package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by pei.xu on 2016/3/22.
 */
public class Test implements MethodInterceptor {


    public static Object getCgLibProxy(Object target) {

        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new Test());
        enhancer.setSuperclass(target.getClass());
        enhancer.setClassLoader(target.getClass().getClassLoader());
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        System.out.println(">>>>>>>>>>>start!");

        Object retObj = proxy.invokeSuper(obj, args);

        System.out.println(">>>>>>>>>>>end!");
        return retObj;
    }

    public static void main(String[] args) {
        CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
        CustomerServiceImpl proxy = (CustomerServiceImpl) Test.getCgLibProxy(customerServiceImpl);

        proxy.deleteInfo();

        proxy.addInfo();

    }

}

