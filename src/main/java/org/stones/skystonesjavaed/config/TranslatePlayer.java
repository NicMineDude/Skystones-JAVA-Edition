package org.stones.skystonesjavaed.config;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class TranslatePlayer {

    public TranslateTransition transition;

    public TranslatePlayer(Duration duration, Node node, boolean autoReverse, int cycleCount, Interpolator interpolator){
        this.transition = new TranslateTransition(duration, node);
        transition.setAutoReverse(autoReverse);
        transition.setCycleCount(cycleCount);
        transition.setInterpolator(interpolator);
    }
}
