package org.stones.skystonesjavaed;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class VideoPlayer {

    private MediaPlayer videoPlayer;
    private MediaView mediaView;

    public VideoPlayer(String videoPathstr, int times, double volume, double rate, double width, double height) {
        // Use an absolute file path or convert from relative path
        try {
            String videoPath = new File("assets/" + videoPathstr).toURI().toString();
            Media media = new Media(videoPath);
            videoPlayer = new MediaPlayer(media);
            videoPlayer.setCycleCount(times); // loop music
            videoPlayer.setVolume(volume); // volume from 0.0 to 1.0
            videoPlayer.setRate(rate);
            mediaView = new MediaView(videoPlayer);
            mediaView.setFitWidth(width);
            mediaView.setFitHeight(height);
        } catch (Exception e){
            System.out.println("Data Corrupted!");
        }

    }
    public MediaPlayer getVideoMediaPlayer(){return videoPlayer;}
    public MediaView getVideoMediaView(){return mediaView;}

    public void stopMusic() {
        videoPlayer.stop();
    }


}