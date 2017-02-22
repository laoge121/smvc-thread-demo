package java8.lambda;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yuhou on 2017/2/22.
 */
public class LambdaTest {
    List<String> list = null;

    @Before
    public void before() {
        list = Arrays.asList("peter", "anna", "nike", "xenia");
    }

    @Test
    public void testCollectionsSort() {
        /*Collections.sort(list, (a, b) -> {
            return a.compareTo(b);
        });
        */
        Collections.sort(list, (a, b) -> a.compareTo(b));
        System.out.println(list);
    }

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);

        //这种智能有一个方法
        //T aa(F from);
    }

    @Test
    public void testFunctionInterface() {
        Converter<String, Integer> converter = from -> Integer.valueOf(from);
        Integer in = converter.convert("12");
        System.out.print(in);

        //相当于如下代码
        Converter<String, Integer> converter1 = new Converter<String, Integer>() {
            @Override
            public Integer convert(String from) {
                return Integer.valueOf(from);
            }
        };
        in = converter1.convert("22");
        System.out.println(in);

        Converter<String, Integer> converter2 = Integer::valueOf;
    }

    class Person {
        String firstName;

        String lastName;

        public Person() {
        }

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    interface PersonFactory<T> {
        T create(String f, String l);
    }

    /**
     * 测试 :: 关键是使用
     */
    @Test
    public void testConstruct() {
        PersonFactory personFactory = Person::new;
        Person person = (Person) personFactory.create("a", "b");
        System.out.println(person.getFirstName());
    }

}
