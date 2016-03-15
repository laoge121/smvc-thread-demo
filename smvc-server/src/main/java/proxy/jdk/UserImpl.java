package proxy.jdk;

/**
 * Created by pei.xu on 2015/11/5.
 */
public class UserImpl implements User {
    @Override
    public void add() {

        System.out.println(">>>>>>>>>>>add");

    }

    @Override
    public void delete() {
        System.out.println(">>>>>>>>>>>delete");
    }
}
