package org.stones.skystonesjavaed;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.util.Duration;

public class MusicPlayer {

    private MediaPlayer mediaPlayer;

    public static double SFX_VOL = 0.5;
    public static double MUSIC_VOL = 0.25;

    public MusicPlayer(String songmp3, int times, double volume) {
        // Use an absolute file path or convert from relative path
        String musicPath = new File("assets/" + songmp3).toURI().toString();
        Media media = new Media(musicPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(times); // loop music
        mediaPlayer.setVolume(volume); // volume from 0.0 to 1.0
    }

    public MediaPlayer getMusicMediaPlayer(){return mediaPlayer;}

    public void stopMusic() {
        mediaPlayer.stop();
    }
}
