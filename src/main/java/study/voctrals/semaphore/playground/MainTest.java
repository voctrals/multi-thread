package study.voctrals.semaphore.playground;

public class MainTest {

    public static void main(String[] args) {

        PlayGround playGround = new PlayGround();
        for (int i = 0; i < 10; i++) {
            new Thread(new Student(playGround, i + 1 + "")).start();
        }

    }
}
