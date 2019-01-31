package study.voctrals.tools.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 妖怪出没１０次，葫芦娃集齐才能打
 *
 * @author lei.liu
 * @since 19-1-30
 */
public class CalabashBrothers {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier atLease7 = new CyclicBarrier(7, () -> System.out.println(Thread.currentThread().getName() + " 葫芦娃集齐，准备消灭妖怪。"));

        for (int i = 0; i < 10; i++) {
            System.out.println("妖怪" + i + "出现。");
            for (int calabash = 0; calabash < 7; calabash++) {
                int finalCalabash = calabash;
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "出世");
                    try {
                        if (finalCalabash == 2) {
                            Thread.sleep(1000);
                        }
                        atLease7.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }, calabash + 1 + "娃").start();
            }
//            System.out.println(Thread.currentThread().getName() + "...");
        }
    }
}
