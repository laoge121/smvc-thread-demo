package thread;


import java.util.Scanner;
import java.util.concurrent.Phaser;

/**
 * User: pei.xu
 * Date: 15-3-17
 * Time: 下午6:48
 */
public class PhaserTest {

    private static Phaser phaser = null;

    public static void main(String[] args) {

        //控制指定的线程定时运行
//        int count = 3;
//        phaser = new Phaser(count);
//        for (int i = 0; i < count; i++) {
//            System.out.println("start");
//            new Thread(new ThreadTest1(phaser, i)).start();
//        }

        //打到某个条件后在执行
        phaser = new Phaser(3) {

            /**
             * 这个方法是终止phaser的条件如果返回true就能终止否则不能终止
             *
             * @param phase             传入的第几个阶段数量
             * @param registeredParties 注册的中数量
             * @return
             */
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println(">>>>>>>>>>over" + phase + ";;;;;;;;;" + registeredParties);
                if (phase >= registeredParties || registeredParties == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        for (int i = 0; i < 3; i++) {
            System.out.println("start" + i);
           // phaser.register();
            new Thread(new ThreadTest1(phaser, i)).start();
        }

//        for (int i = 0; i < 3; i++) {
//            System.out.println("start" + i);
//            phaser.register();
//            new Thread(new ThreadTest1(phaser, i)).start();
//        }

//        System.out.println("please input ok go!");
//
//        Scanner scanner = new Scanner(System.in);
//        String data = scanner.next();
//        while (true) {
//            if ("ok".equals(data)) {
//                phaser.arriveAndAwaitAdvance();
//            } else {
//                System.out.println("please input ok go!");
//                data = scanner.next();
//            }
//        }
    }

    static class ThreadTest1 implements Runnable {

        Phaser phaser = null;
        int num = 0;

        ThreadTest1() {
        }

        ThreadTest1(Phaser phaser, int num) {
            this.phaser = phaser;
            this.num = num;
        }

        @Override
        public void run() {
            phaser.arriveAndAwaitAdvance();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + num);
        }
    }
}
