package guava.base;


//import com.google.common.base.Optional;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by pei.xu on 2015/12/15.
 */
public class BaseTest {


    /**
     * null的 去除
     */
    public void optionalTest() {
        /*Optional<String> opt = Optional.of("aaa");
        System.out.print(opt.get());*/
        Optional optional = Optional.of("bbbb");
        System.out.println(optional.get());

        Optional<tests1> test1 = Optional.of(new tests1());
        System.out.println(test1.get().getName());
    }

    public void checkParam(boolean bool, Object obj) {


        //boolean 校验
        Preconditions.checkArgument(bool);

    }

    /**
     * 对象比较 排序
     */
    public void ObjectsTest() {

        tests1 t1 = new tests1();

        t1.setName("aa");

        tests1 t2 = new tests1();

        t2.setName("aa");

        System.out.println(t1.equals(t2));

        System.out.println(Objects.equal(t1, t2));


    }

    /**
     * 集合排序
     */
    public void orderSortTest() {

        //排序器创建 只是用用设置 排序的规则 实际 排序还需要调用 collections 的 sort 方法
        Ordering<?> ordering = new Ordering<Object>() {
            @Override
            public int compare(Object left, Object right) {
                return 0;
            }
        };

        Ordering ordering1 = Ordering.natural();

        Ordering<String> ordering2 = Ordering.from(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Ints.compare(o1.length(), o2.length());
            }
        });

        List<String> sortList = Lists.newArrayList(new String[]{"aaa", "ddd", "cccc", "eewew", "sfff"});

        System.out.println(sortList);

        Collections.sort(sortList, ordering1);

        System.out.println("sort:" + sortList);
    }


    /**
     * throw 延续
     */
    public void throwTest() {

        try {

        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }


    /**
     * 不可变集合测试
     */
    public void immutableTest() {
        ImmutableMap<String, tests1> immutableMap= ImmutableMap.of("aa", new tests1());
    }

    public static void main(String[] args) {
//        new BaseTest().optionalTest();
//        new BaseTest().ObjectsTest();
        new BaseTest().orderSortTest();
    }

    class tests1 implements Comparable<tests1> {
        private String name;

        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof tests1)) return false;

            tests1 tests1 = (tests1) o;

            if (name != null ? !name.equals(tests1.name) : tests1.name != null) return false;
            if (password != null ? !password.equals(tests1.password) : tests1.password != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (password != null ? password.hashCode() : 0);
            return result;
        }

        @Override
        public int compareTo(tests1 o) {
            return ComparisonChain.start().compare(this.getName(), o.getName()).compare(this.getPassword(), o.getPassword()).result();
        }
    }

}
