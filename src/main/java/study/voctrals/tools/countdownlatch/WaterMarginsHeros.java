package study.voctrals.tools.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 108个水浒好汉凑齐了再去征讨方腊
 *
 * @author lei.liu
 * @since 19-1-30
 */
public class WaterMarginsHeros {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(108);
        for (int i = 0; i < 108; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "英雄创建。");
                latch.countDown();
            }, "第" + (i + 1) + "个").start();
        }
        latch.await();
        System.out.println("出征。。。");
    }

}
