package study.voctrals.tools.exchanger;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lei.liu
 * @since 19-1-30
 */
public class NBATrade {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Exchanger<List<String>> nbaExchanger = new Exchanger<>();
        executorService.execute(() -> {
            try {
                List<String> player = Arrays.asList("James", "Anthony");
                List<String> after = nbaExchanger.exchange(player);
                System.out.println("1" + after);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            List<String> player = Arrays.asList("Harden", "Zion");
            try {
                List<String> after = nbaExchanger.exchange(player);
                System.out.println("2" + after);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
