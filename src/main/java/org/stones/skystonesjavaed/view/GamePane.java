package org.stones.skystonesjavaed.view;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import org.stones.skystonesjavaed.config.VideoPlayer;
import org.stones.skystonesjavaed.controller.GameApp;
import org.stones.skystonesjavaed.model.GameEngine;

public class GamePane extends Pane {

    private GameEngine model;

    private Button backToMenu;
    private Button reset;
    private Button optionsButton;

    private Label topMsg;
    private Label p1Label;
    private Label p2Label;

    private GridPane gameBoardPane;
    private PlayedStonePane playedStonePane;
    private StonePane stonePaneP1;
    private StonePane stonePaneP2;

    private VideoPlayer bgVideo;

    public GamePane(GameEngine initModel){
        model = initModel;

        backToMenu = new Button("Back to Menu");
        backToMenu.setPrefSize(300, 40);
        double centerXBack = 454;
        double centerYBack = 685;
        backToMenu.setLayoutX(centerXBack - backToMenu.getPrefWidth() / 2);
        backToMenu.setLayoutY(centerYBack - backToMenu.getPrefHeight() / 2);
        backToMenu.setDisable(true);
        backToMenu.setVisible(false);

        reset = new Button("Reset Game");
        reset.setPrefSize(300, 40);
        double centerXReset = 902;
        double centerYReset = 685;
        reset.setLayoutX(centerXReset - reset.getPrefWidth() / 2);
        reset.setLayoutY(centerYReset - reset.getPrefHeight() / 2);
        reset.setDisable(true);
        reset.setVisible(false);

        optionsButton = new Button("Options");
        optionsButton.setPrefSize(100, 50);
        double centerXOpBut = 683;
        double centerYOpbut = 725;
        optionsButton.setLayoutX(centerXOpBut - optionsButton.getPrefWidth() / 2);
        optionsButton.setLayoutY(centerYOpbut - optionsButton.getPrefHeight() / 2);

        topMsg = new Label("Base");
        topMsg.relocate(575, 50);
        topMsg.setPrefSize(474, 10);
        topMsg.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, #000000, 2.5, 0.25, 0, 0);");
        topMsg.setVisible(false);

        gameBoardPane = new GridPane(model);
        gameBoardPane.relocate(446, 150);
        gameBoardPane.setPrefSize(466, 466);

        playedStonePane = new PlayedStonePane(model);
        playedStonePane.relocate(466, 170);
        playedStonePane.setPrefSize(466, 466);

        stonePaneP1 = new StonePane(model.getPlayer1());
        stonePaneP1.setPrefSize(125, 665);
        stonePaneP1.relocate(50, 66.5);

        stonePaneP2 = new StonePane(model.getPlayer2());
        stonePaneP2.setPrefSize(125, 665);
        stonePaneP2.relocate(1191, 66.5);

        p1Label = new Label(""+model.getP1Score());
        p1Label.setPrefSize(200, 50);
        p1Label.relocate(380,20);
        p1Label.setStyle("-fx-text-fill: #ca587d; -fx-font-size: 100; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, #000000, 2.5, 0.2, 0, 0);");

        p2Label = new Label(""+model.getP2Score());
        p2Label.setPrefSize(200, 50);
        p2Label.relocate(910,20);
        p2Label.setStyle("-fx-text-fill: #8cd9f4; -fx-font-size: 100; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, #000000, 2.5, 0.25, 0, 0);");

        bgVideo = new VideoPlayer("videoAssets/movingBackground.mp4", MediaPlayer.INDEFINITE, 0.25, 0.5, GameApp.windowWidth, GameApp.windowHeight);
        bgVideo.getVideoMediaPlayer().play();


        getChildren().addAll(bgVideo.getVideoMediaView(), backToMenu, reset, gameBoardPane, playedStonePane, stonePaneP1, stonePaneP2, topMsg, p1Label, p2Label, optionsButton);
    }

    public Button getBackToMenu() {return backToMenu;}
    public Button getReset() {return reset;}
    public Button getOptionsButton() {return optionsButton;}
    public GridPane getGameBoardPane() {return gameBoardPane;}
    public PlayedStonePane getPlayedStonePane() {return playedStonePane;}
    public StonePane getStonePaneP1() {return stonePaneP1;}
    public StonePane getStonePaneP2() {return stonePaneP2;}
    public Label getTopMsg() {return topMsg;}
    public Label getP1Label() {return p1Label;}
    public Label getP2Label() {return p2Label;}

}
