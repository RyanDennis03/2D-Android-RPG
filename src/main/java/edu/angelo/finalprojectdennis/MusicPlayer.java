package edu.angelo.finalprojectdennis;

import com.badlogic.androidgames.framework.Music;

/**
 * Represents the music player.
 * Created by rdennis4 on 12/3/2018.
 */
public class MusicPlayer {

    /**
     * The current music being played.
     * This has to be created static so that it can be stopped
     * to prevent music overlap when entering a new area.
     */
    public static Music CURRENT_MUSIC;

    public static void play(Music music) {
        if (!Settings.soundEnabled) {
            return;
        }
        stop();
        MusicPlayer.CURRENT_MUSIC = music;
        MusicPlayer.CURRENT_MUSIC.play();
    }

    public static void stop() {
        if (MusicPlayer.CURRENT_MUSIC != null && MusicPlayer.CURRENT_MUSIC.isPlaying()) {
            MusicPlayer.CURRENT_MUSIC.pause();
        }
    }

}
