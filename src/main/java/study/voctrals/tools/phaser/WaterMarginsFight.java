package study.voctrals.tools.phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * @author lei.liu
 * @since 19-1-30
 */
public class WaterMarginsFight {

    public static void main(String[] args) {
        Phaser phaser = new WaterMarginsPhaser(108);
        ExecutorService executors = Executors.newCachedThreadPool();
        for (int i = 0; i < 108; i++) {
            executors.submit(new Hero(i + 1 + "", phaser));
        }
        executors.shutdown();
    }

    static class WaterMarginsPhaser extends Phaser {
        WaterMarginsPhaser(int a) {
            super(a);
        }
        /**
         * 汇总时做的任务
         */
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                // 征辽
                case 0:
                    System.out.println("出征大辽，凯旋");
                    return false;
                // 田虎
                case 1:
                    System.out.println("出征田虎，凯旋");
                    return false;
                // 王庆
                case 2:
                    System.out.println("出征王庆，凯旋");
                    return false;
                //方腊
                case 3:
                    System.out.println("出征方腊，凯旋");
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Hero implements Runnable {
        private String name;
        private Phaser phaser;

        Hero(String name, Phaser phaser) {
            this.name = name;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(name + "号好汉到达。等待出征大辽出发。。。");
            phaser.arriveAndAwaitAdvance();

            System.out.println(name + "号好汉到达。等待出征田虎出发。。。");
            phaser.arriveAndAwaitAdvance();

            System.out.println(name + "号好汉到达。等待出征王庆出发。。。");
            phaser.arriveAndAwaitAdvance();

            System.out.println(name + "号好汉到达。等待出征方腊出发。。。");
            phaser.arriveAndAwaitAdvance();
        }

    }

}
