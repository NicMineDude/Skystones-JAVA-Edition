package org.stones.skystonesjavaed.view;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.stones.skystonesjavaed.config.MusicPlayer;
import org.stones.skystonesjavaed.config.VideoPlayer;
import org.stones.skystonesjavaed.controller.GameApp;
import org.stones.skystonesjavaed.model.GameEngine;

public class InGameOptionPane extends Pane {

    private GameEngine model;
    private VideoPlayer bgVideo;
    private Button backToMenu;
    private Button backToGame;
    private Label optionsLabel;
    private Label musicVolLabel;
    private Slider musicSlider;
    private Label sfxVolLabel;
    private Slider sfxSlider;


    public InGameOptionPane(GameEngine initModel){
        model = initModel;

        optionsLabel = new Label("Options");
        optionsLabel.setPrefSize(200, 50);
        double centerXOpLabel = 700;
        double centerYOpLabel = 125;
        optionsLabel.setLayoutX(centerXOpLabel - optionsLabel.getPrefWidth() / 2);
        optionsLabel.setLayoutY(centerYOpLabel - optionsLabel.getPrefHeight() / 2);
        optionsLabel.setStyle("-fx-font-size: 50; -fx-font-weight: bold; -fx-text-fill: white;");

        musicSlider = new Slider(0, 1, MusicPlayer.MUSIC_VOL); // start at 50%
        musicSlider.setPrefSize(700, 25);
        double centerXMusicVol = 683;
        double centerYMusicVol = 300;
        musicSlider.setLayoutX(centerXMusicVol - musicSlider.getPrefWidth() / 2);
        musicSlider.setLayoutY(centerYMusicVol - musicSlider.getPrefHeight() / 2);

        musicVolLabel = new Label("Music Volume: " + String.format("%.2f", musicSlider.getValue() * 100));
        musicVolLabel.setPrefSize(500, 100);
        musicVolLabel.relocate(348, 200);
        musicVolLabel.setStyle("-fx-font-size: 30; -fx-font-weight: bold; -fx-text-fill: #e3e3e3; -fx-effect: dropshadow(gaussian, #000000, 8, 0.25, 0, 0);");

        sfxSlider = new Slider(0, 1, MusicPlayer.SFX_VOL); // start at 50%
        sfxSlider.setPrefSize(700, 25);
        double centerXSFX = 683;
        double centerYSFX = 450;
        sfxSlider.setLayoutX(centerXSFX - sfxSlider.getPrefWidth() / 2);
        sfxSlider.setLayoutY(centerYSFX - sfxSlider.getPrefHeight() / 2);

        sfxVolLabel = new Label("SFX Volume: "+ String.format("%.2f", sfxSlider.getValue()*100));
        sfxVolLabel.setPrefSize(500, 100);
        sfxVolLabel.relocate(348, 350);
        sfxVolLabel.setStyle("-fx-font-size: 30; -fx-font-weight: bold; -fx-text-fill: #e3e3e3; -fx-effect: dropshadow(gaussian, #000000, 8, 0.25, 0, 0);");

        backToMenu = new Button("Return to main menu");
        backToMenu.setPrefSize(250, 30);
        double centerXBack = 475;
        double centerYBack = 550;
        backToMenu.setLayoutX(centerXBack - backToMenu.getPrefWidth() / 2);
        backToMenu.setLayoutY(centerYBack - backToMenu.getPrefHeight() / 2);

        backToGame = new Button("Return");
        backToGame.setPrefSize(100, 50);
        double centerXToGame = 683;
        double centerYToGame = 725;
        backToGame.setLayoutX(centerXToGame - backToGame.getPrefWidth() / 2);
        backToGame.setLayoutY(centerYToGame - backToGame.getPrefHeight() / 2);

        Rectangle bg = new Rectangle(GameApp.windowWidth, GameApp.windowHeight);
        bg.setOpacity(0.5);

        getChildren().addAll(bg, musicSlider, backToMenu, backToGame, musicVolLabel, sfxSlider, sfxVolLabel, optionsLabel);
    }

    public Slider getMusicSlider(){return musicSlider;}
    public Button getBackToMenu() {return backToMenu;}
    public Button getBackToGame() {return backToGame;}
    public Slider getSfxSlider(){return sfxSlider;}
    public Label getSfxVolLabel() {return sfxVolLabel;}
    public Label getMusicVolLabel(){return musicVolLabel;}
}
