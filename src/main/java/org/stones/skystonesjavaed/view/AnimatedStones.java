package org.stones.skystonesjavaed.view;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimatedStones extends ImageView {

    private RotateTransition rotationAnim;
    private double imageRotationDegree;

    public AnimatedStones(Image image, double imageRotationDegree,  double fromAngle, double toAngle, double duration, int cycleCount) {
        //AnimatedStones inherits the constructor from ImageView.
        super(image);
        //Logic for the rotation animation
        this.setRotate(imageRotationDegree);
        this.imageRotationDegree = imageRotationDegree;
        this.rotationAnim = new RotateTransition(Duration.millis(duration), this);
        this.rotationAnim.setFromAngle(fromAngle);
        this.rotationAnim.setToAngle(toAngle);
        this.rotationAnim.setInterpolator(Interpolator.EASE_BOTH);
        this.rotationAnim.setCycleCount(cycleCount);
        this.rotationAnim.setAutoReverse(true);
    }

    public void stopRotation(){
        this.rotationAnim.stop();
        this.setRotate(imageRotationDegree);
    }

    public RotateTransition getRotationAnim(){return this.rotationAnim;}

}
