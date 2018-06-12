package study.voctrals.semaphore.playground;

import java.util.concurrent.Semaphore;

/**
 * 操场
 * <p>
 * 每个操场有5个跑道，只允许5个人同时跑步或者遛弯儿
 * <p>
 * Semaphore用来约束以实现这个要求
 *
 * @author voctrals
 */
public class PlayGround {

    /**
     * 五个赛道
     */
    private Track[] tracks = {
            new Track(1), new Track(2), new Track(3), new Track(4), new Track(5)
    };

    /**
     * 被使用的赛道
     */
    private volatile boolean[] used = {false, false, false, false, false};
    // same as
    // private volatile boolean[] used = new boolean[5];

    private Semaphore semaphore = new Semaphore(5, true);

    /**
     * 获取赛道
     */
    public Track getTrack() throws InterruptedException {
        // 就是要一个赛道，如果没有的话，阻塞线程
        semaphore.acquire(1);
        return getNextAvailableTrack();
    }

    /**
     * 释放赛道
     */
    public void releaseTrack(Track track) {
        if (makeAsUsed(track)) {
            semaphore.release(1);
        }
    }

    /**
     * 如果存在未被使用的赛道则返回
     * 否则，返回null
     */
    private Track getNextAvailableTrack() {
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                used[i] = true;
                return tracks[i];
            }
        }
        return null;
    }

    /**
     * 标记当前赛道
     */
    private boolean makeAsUsed(Track track) {
        for (int i = 0; i < used.length; i++) {
            if (tracks[i] == track) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * 跑道
     */
    class Track {
        private int number;

        public Track(int number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "Track{" +
                    "number=" + number +
                    '}';
        }
    }
}
