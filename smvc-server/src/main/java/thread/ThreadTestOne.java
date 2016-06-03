package thread;

/**
 * User: pei.xu
 * Date: 15-1-5
 * Time: 下午3:31
 */
public class ThreadTestOne implements Runnable {

    private int count;

    private static int count2;

    //方法同步设置
    public synchronized void addObject1(int val) throws InterruptedException {
        this.count += val;
        System.out.println("test" + count);
        Thread.sleep(1000 * 10);
    }

    public void addObject2(int val) throws InterruptedException {
        synchronized (this) {
            this.count += val;
            System.out.println("test2" + count);
            Thread.sleep(1000 * 10);
        }
    }

    public void addObject3(int val) throws InterruptedException {
        synchronized (this) {
            this.count += val;
            System.out.println("test3" + count);
            Thread.sleep(1000 * 10);
        }
    }

    //静态同步设置

    public static synchronized void addStatic1(int val) throws InterruptedException {
        ThreadTestOne.count2 += val;
        System.out.println("test" + ThreadTestOne.count2);
        Thread.sleep(1000 * 10);
    }

    public static void addStatic2(int val) throws InterruptedException {
        synchronized (ThreadTestOne.class) {
            ThreadTestOne.count2 += val;
            System.out.println("test" + ThreadTestOne.count2);
            Thread.sleep(1000 * 10);
        }
    }


    //特别的一种内部方法设置静态同步块

    public void addStatic3(int val) throws InterruptedException {
        synchronized (ThreadTestOne.class) {
            ThreadTestOne.count2 += val;
            System.out.println("test" + ThreadTestOne.count2);
            Thread.sleep(1000 * 10);
        }
    }

    @Override
    public void run() {
        try {
            //this.addObject1(1);
            //this.addObject2(1);
           // ThreadTestOne.addStatic1(2);
            //ThreadTestOne.addStatic2(2);
            this.addStatic3(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        //证明实例方法同步是同步于一个对象上
//        ThreadTestOne threadTestOne = new ThreadTestOne();
//        Thread thread = new Thread(threadTestOne);
//        thread.start();
//
//        Thread thread1 = new Thread(threadTestOne);
//        thread1.start();

        //静态方法同步是同步于类上
        //ThreadTestOne threadTestOne = new ThreadTestOne();
        Thread thread = new Thread(new ThreadTestOne());
        thread.start();

        Thread thread1 = new Thread(new ThreadTestOne());
        thread1.start();
    }
}
