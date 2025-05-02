package org.stones.skystonesjavaed;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

public class MenuPane extends Pane {

    private GameEngine model;

    private Button playButton;
    private Button deckBuilderBut;
    private Button optionButton;
    private Button quitButton;

    private VideoPlayer bgVideo;

    private ImageView title;

    public MenuPane(GameEngine initModel){
        model = initModel;

        //Play button
        playButton = new Button("Play");
        playButton.setPrefSize(550, 40);
        double centerXPlay = 683;
        double centerYPlay = 375;
        playButton.setLayoutX(centerXPlay - playButton.getPrefWidth() / 2);
        playButton.setLayoutY(centerYPlay - playButton.getPrefHeight() / 2);

        deckBuilderBut = new Button("Deck Builder");
        deckBuilderBut.setPrefSize(550, 40);
        double centerXDeck = 683;
        double centerYDeck = 450;
        deckBuilderBut.setLayoutX(centerXDeck - deckBuilderBut.getPrefWidth() / 2);
        deckBuilderBut.setLayoutY(centerYDeck - deckBuilderBut.getPrefHeight() / 2);

        optionButton = new Button("Options");
        optionButton.setPrefSize(260, 40);
        double centerXOption = 538;
        double centerYOption = 525;
        optionButton.setLayoutX(centerXOption - optionButton.getPrefWidth() / 2);
        optionButton.setLayoutY(centerYOption - optionButton.getPrefHeight() / 2);

        quitButton = new Button("Quit Game");
        quitButton.setPrefSize(260, 40);
        double centerXQuit = 828;
        double centerYQuit = 525;
        quitButton.setLayoutX(centerXQuit - quitButton  .getPrefWidth() / 2);
        quitButton.setLayoutY(centerYQuit - quitButton.getPrefHeight() / 2);

        //Title Image
        title = new ImageView( new Image("file:assets/title.png"));
        title.setFitWidth(800);
        title.setFitHeight(400);
        double centerXImage = 683;
        double centerYImage = 200;
        title.setLayoutX(centerXImage - title.getFitWidth() / 2);
        title.setLayoutY(centerYImage - title.getFitHeight() / 2);


        bgVideo = new VideoPlayer("videoAssets/movingBackground.mp4", MediaPlayer.INDEFINITE, 0.25, 0.5, GameApp.windowWidth, GameApp.windowHeight);
        bgVideo.getVideoMediaPlayer().play();

        getChildren().addAll(bgVideo.getVideoMediaView(), playButton, title, deckBuilderBut, quitButton, optionButton);
    }

    public Button getPlayButton(){return playButton;}
    public Button getQuitButton(){return quitButton;}
    public Button getOptionButton() {return optionButton;}
    public Button getDeckBuilderBut() {return deckBuilderBut;}
}
