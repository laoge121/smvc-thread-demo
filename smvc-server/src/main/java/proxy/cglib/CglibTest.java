package proxy.cglib;

/**
 * Created by pei.xu on 2015/11/5.
 */
public class CglibTest {

    public static void main(String[] args) {

        CustomerServiceImpl customerService = new CustomerServiceImpl();

        CustomerServiceImpl cProxy = (CustomerServiceImpl) new CglibProxy().createCglibProxy(customerService);

        cProxy.deleteInfo();
    }
}
