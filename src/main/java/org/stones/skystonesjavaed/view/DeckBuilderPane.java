package org.stones.skystonesjavaed.view;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javafx.scene.media.MediaPlayer;
import org.stones.skystonesjavaed.model.GameEngine;
import org.stones.skystonesjavaed.config.VideoPlayer;
import org.stones.skystonesjavaed.controller.GameApp;


public class DeckBuilderPane extends Pane {

    private GameEngine model;

    private VideoPlayer bgVideo;

    private Button backToMenu;

    public DeckBuilderPane(GameEngine initModel){
        model = initModel;

        backToMenu = new Button("Back");
        backToMenu.setPrefSize(200, 25);
        double centerXBack = 150;
        double centerYBack = 675;
        backToMenu.setLayoutX(centerXBack - backToMenu.getPrefWidth() / 2);
        backToMenu.setLayoutY(centerYBack - backToMenu.getPrefHeight() / 2);

        bgVideo = new VideoPlayer("videoAssets/deckBuildBg.mp4", MediaPlayer.INDEFINITE, 0.25, 0.5, GameApp.windowWidth, GameApp.windowHeight);
        bgVideo.getVideoMediaPlayer().play();

        getChildren().addAll(bgVideo.getVideoMediaView(), backToMenu);
    }

    public Button getBackToMenu() {return backToMenu;}
}
