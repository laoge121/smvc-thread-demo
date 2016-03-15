package com.yn.springapp.hession.simple;

import com.google.common.collect.Maps;
import com.yn.springapp.util.interceptor.BeanUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by pei.xu on 2016/3/11.
 */
public class SimpleServiceImpl implements SimpleService {

    @Override
    public Object invoke(String path, String method, Object[] args) throws InvocationTargetException, IllegalAccessException {

        //String name = path.substring(0, 1).toUpperCase(Locale.getDefault()) + path.substring(1, path.length());

        Object object = BeanUtil.getBean(path);

        Object ret = Maps.newHashMap();

        for (Method method1 : object.getClass().getMethods()) {

            //判断参数数量 和 方法名字
            if (method1.getName().equals(method) && method1.getParameterCount() == args.length) {

                boolean bool = false;
                Class[] types = method1.getParameterTypes();
                for (int i = 0; i < types.length; i++) {
                    if (!types[i].getTypeName().equals(args[i].getClass().getTypeName())) {
                        bool = true;
                        break;
                    }
                }
                if (bool)
                    continue;

                ret = method1.invoke(object, args);
                break;
            }
        }

        return ret;

    }

    @Override
    public String getData() {
        return ">>>>>>>>>>";
    }
}
