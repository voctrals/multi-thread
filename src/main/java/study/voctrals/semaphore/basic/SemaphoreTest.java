package study.voctrals.semaphore.basic;

/**
 * Semaphore，信号量，用来控制并发数
 * <p>
 * 控制并发数最大为Semaphore指定的大小
 * <p>
 * 每调用acquire()或者acquire(n)，就注册了1个或者n个可能运行的信号，但并不一定真能够运行，如果有剩余的信号量供使用，那么可以直接使用，不然的话会被阻塞
 * <p>
 * release用来释放当前控制的信号量，一般与acquire的数量相同
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        java.util.concurrent.Semaphore most5 = new java.util.concurrent.Semaphore(5);

        // 前5个线程会直接获取得到信号量，并休眠
        // 后5个线程获得到信号量之前会被阻塞，直到前5个逐个释放信号量
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " before acquire~");
                    most5.acquire();
                    System.out.println(Thread.currentThread().getName() + " after acquire~");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " before release~");
                    most5.release();
                    System.out.println(Thread.currentThread().getName() + " after release~");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread " + (i + 1)).start();
        }
    }

}
