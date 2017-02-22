package guava.base;

import com.google.common.base.*;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.List;

/**
 * Created by pei.xu on 2015/12/15.
 */
public class BaseTest {

    /**
     * null non-null guava 测试
     * Optional用于包含非空对象的不可变对象。 Optional对象，用于不存在值表示null。
     * 这个类有各种实用的方法，以方便代码来处理为可用或不可用，而不是检查null值。
     *
     * @see http://www.cnblogs.com/peida/archive/2013/06/14/Guava_Optional.html
     */
    @Test
    public void testOptional() {
        Integer integer1 = null;
        Integer integer2 = new Integer(12);
        Optional<Integer> a = Optional.fromNullable(integer1);
        Optional<Integer> b = Optional.of(integer2);

        System.out.println("First parameter is present: " + a.isPresent());

        System.out.println("Second parameter is present: " + b.isPresent());

        integer1 = a.or(new Integer(0));
        integer2 = b.get();
        System.out.println(integer1 + integer1);
    }

    /**
     * 用于替换模式数据处理
     * 大小写转换
     */
    @Test
    public void testAscii() {
        System.out.println("大写转小写:" + Ascii.toLowerCase("C"));
        System.out.println("小写转大写:" + Ascii.toUpperCase("d"));
        System.out.println(Ascii.toLowerCase("你好"));
    }

    /**
     * 提供不同的ASCII字符格式之间的转换
     */
    @Test
    public void testCaseFormat() {
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
    }

    /**
     * CharMatcher 字符串的替换
     *
     * @see http://www.yiibai.com/guava/guava_charmatcher.html
     */
    @Test
    public void testCharMatcher() {

        //返回满足相应条件的数据
        System.out.println(CharMatcher.ascii().and(CharMatcher.digit()).retainFrom("年后asdfsdf adsfa asd33234@df "));

        //返回不满足相应条件的数据
        System.out.println(CharMatcher.ascii().removeFrom("31432 DDDD 嗯呢"));
    }

    /**
     * 数据类型转换
     */
    @Test
    public void testConverter() {
        System.out.println(Ints.stringConverter().convert("11"));
        final BiMap<Integer, String> map = HashBiMap.create();
        map.put(1, "a");
        map.put(2, "b");
        System.out.println(map);
        System.out.println(map.inverse());
    }

    /**
     * 比较任何两个对象是否相等
     *
     * @Test public void testEquivalence(){
     * Integer a= new Integer(130);
     * Integer b= new Integer(131);
     * <p>
     * }
     */
    @Test
    public void testJoiner() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        StringBuilder sb = new StringBuilder("");
        System.out.println(Joiner.on("-").appendTo(sb, list));
        System.out.println(Joiner.on(":").join(list));
    }

    @Test
    public void testMoreObjects() {
        //String str = MoreObjects.toStringHelper(UserPojo.class);
        //System.out.println(str);
    }

}
