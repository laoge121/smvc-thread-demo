package annotation;

import java.lang.annotation.*;

/**
 * Created by pei.xu on 2016/1/25.
 */
@Target({ElementType.METHOD, ElementType.TYPE})//表明 直接用着什么上面 比如 方法 类型
@Retention(RetentionPolicy.RUNTIME) //注解按照运行机制分为3中 源码注解 编译时注解 运行是注解
@Documented //说明 生产javadoc 的时候 带有这个 注解
@Inherited //元注解 标识 这个注解 可以被基继承 只用于类的 继承 不包括 接口的 继承 (只会继承累的注解)
public @interface AnnotationTest {
}
