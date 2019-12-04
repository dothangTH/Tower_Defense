package Util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sound {
    private boolean pause;
    MediaPlayer mediaPlayer;

    public Sound(String path) {
        pause = false;
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public void playFX() {
        mediaPlayer.stop();
        mediaPlayer.play();
        mediaPlayer.setStartTime(Duration.ZERO);
    }

    public void playTheme() {
        mediaPlayer.play();
    }

    public void loop() {
        if (pause) return;
        mediaPlayer.setOnEndOfMedia(
                () -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void pause() {
        pause = true;
        mediaPlayer.pause();
    }

    public void continuePlay() {
        pause = false;
        mediaPlayer.play();
    }
}