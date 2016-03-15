package thread;


import sun.misc.Lock;

/**
 * Created with IntelliJ IDEA.
 * User: pei.xu
 * Date: 15-1-5
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class ThreadLockTwo implements Runnable {

    private Lock lock = new Lock();

    private int count = 0;


    public void addObject1(int val) throws InterruptedException {
        lock.lock();
        this.count++;
        System.out.println("test" + count);
        lock.unlock();
    }


    @Override
    public void run() {
        try {
            this.addObject1(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        ThreadLockTwo threadLockTwo= new ThreadLockTwo();
        Thread thread = new Thread(threadLockTwo);
        thread.start();

        Thread thread1 = new Thread(threadLockTwo);
        thread1.start();
    }
}
