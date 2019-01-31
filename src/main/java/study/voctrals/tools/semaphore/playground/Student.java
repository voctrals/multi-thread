package study.voctrals.tools.semaphore.playground;

import java.util.concurrent.TimeUnit;

public class Student implements Runnable {

    private PlayGround playGround;
    private String name;

    public Student(PlayGround playGround, String name) {
        this.playGround = playGround;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            PlayGround.Track track = playGround.getTrack();
            if (track != null) {
                System.out.println("学生" + name + "在" + track.toString() + "上跑步");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("学生" + name + "释放" + track.toString());
                //释放跑道
                playGround.releaseTrack(track);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
