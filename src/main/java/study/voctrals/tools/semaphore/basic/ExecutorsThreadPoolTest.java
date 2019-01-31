package study.voctrals.tools.semaphore.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsThreadPoolTest {

    public static void main(String[] args) {


        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {

            service.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread " + (i + 1));
        }

        service.shutdown();

    }

}
