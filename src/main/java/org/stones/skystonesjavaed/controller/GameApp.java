package org.stones.skystonesjavaed.controller;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;
import org.stones.skystonesjavaed.config.MusicPlayer;
import org.stones.skystonesjavaed.model.GameEngine;
import org.stones.skystonesjavaed.view.GameView;
import org.stones.skystonesjavaed.view.GridCellPane;
import org.stones.skystonesjavaed.view.StonePane;

//IF YOU DO ./gradlew clean MAKE SURE TO FOLLOW IT UP WITH ./gradlew build OR ELSE PROJECT WON'T RUN PROPERLY
//ALSO IF INTELLIJ CAN'T RECOGNIZE JAVAFX MAKE SURE TO SYNC ALL GRADLE PROJECTS (THE ELEPHANT ICON TO THE RIGHT)

import java.io.IOException;

public class GameApp extends Application {
    private GameEngine model;
    private GameView view;
    public static int windowWidth = 1366;
    public static int windowHeight = 768;

    public GameApp(){
        this.model = new GameEngine();
        this.view = new GameView(model);
        System.out.println("the one piece?????");
    }

    public void start(Stage primaryStage) throws IOException {

        //Pane and Scene for Gameplay
        Pane mainPane = new Pane();
        mainPane.getChildren().addAll(view);
        Scene menuScene = new Scene(mainPane, windowWidth, windowHeight);
        menuScene.getStylesheets().add(getClass().getResource("/org/stones/skystonesjavaed/styles.css").toExternalForm());

        EventHandler<MouseEvent> update = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getCurrentMenu() == GameView.OPTIONPANE_INDEX){
                    view.getMusic().getMusicMediaPlayer().volumeProperty().bind(view.getOptionPane().getMusicSlider().valueProperty());
                    view.getClickSFX().getMusicMediaPlayer().volumeProperty().bind(view.getOptionPane().getSfxSlider().valueProperty());
                    view.getInGameOptionPane().getMusicSlider().setValue(MusicPlayer.MUSIC_VOL);
                    view.getInGameOptionPane().getSfxSlider().setValue(MusicPlayer.SFX_VOL);
                } else {
                    view.getMusic().getMusicMediaPlayer().volumeProperty().bind(view.getInGameOptionPane().getMusicSlider().valueProperty());
                    view.getClickSFX().getMusicMediaPlayer().volumeProperty().bind(view.getInGameOptionPane().getSfxSlider().valueProperty());
                    view.getOptionPane().getMusicSlider().setValue(MusicPlayer.MUSIC_VOL);
                    view.getOptionPane().getSfxSlider().setValue(MusicPlayer.SFX_VOL);
                }
                MusicPlayer.SFX_VOL = view.getClickSFX().getMusicMediaPlayer().volumeProperty().getValue();
                MusicPlayer.MUSIC_VOL = view.getMusic().getMusicMediaPlayer().volumeProperty().getValue();
                view.update();
            }
        };

        view.getGamePane().getReset().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                model.resetGame();
                PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
                delay.setOnFinished(e -> {
                    view.resetGameView();
                    view.update();
                });
                delay.play();
                view.getSlide().playFromStart();
            }
        });

        view.getGamePane().getBackToMenu().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                view.getGamePane().getBackToMenu().setDisable(true);
                PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
                delay.setOnFinished(e -> {
                    view.setCurrentMenu(GameView.MENUPANE_INDEX);
                    model.resetGame();
                    view.resetGameView();
                    view.update();
                });
                delay.play();
                view.getSlide().playFromStart();
            }
        });

        view.getGamePane().getOptionsButton().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
                delay.setOnFinished(e -> {
                    view.getInGameOptionPane().setDisable(false);
                    view.getInGameOptionPane().setVisible(true);
                });
                delay.play();
            }
        });

        view.getInGameOptionPane().getBackToGame().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
                delay.setOnFinished(e -> {
                    view.getInGameOptionPane().setDisable(true);
                    view.getInGameOptionPane().setVisible(false);
                });
                delay.play();
            }
        });

        view.getInGameOptionPane().getBackToMenu().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                view.getGamePane().getBackToMenu().setDisable(true);
                PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
                delay.setOnFinished(e -> {
                    view.setCurrentMenu(GameView.MENUPANE_INDEX);
                    model.resetGame();
                    view.resetGameView();
                    view.update();
                    view.getInGameOptionPane().setDisable(true);
                    view.getInGameOptionPane().setVisible(false);
                });
                delay.play();
                view.getSlide().playFromStart();
            }
        });

        view.getInGameOptionPane().getMusicSlider().addEventHandler(MouseEvent.MOUSE_DRAGGED, update);
        view.getInGameOptionPane().getSfxSlider().addEventHandler(MouseEvent.MOUSE_DRAGGED, update);

        Button menuButton = view.getMenuPane().getPlayButton();
        menuButton.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                menuButton.setDisable(true);
                PauseTransition delay = new PauseTransition(Duration.seconds(1.25));
                delay.setOnFinished(e -> {
                    view.setCurrentMenu(GameView.GAMEPANE_INDEX);
                    view.update();
                });
                delay.play();
                view.getSlide().playFromStart();
            }
        });

        view.getMenuPane().getQuitButton().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                PauseTransition delay = new PauseTransition(Duration.seconds(0.75));
                delay.setOnFinished(e -> {
                    Platform.exit();
                });
                delay.play();
            }
        });

        view.getMenuPane().getOptionButton().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                PauseTransition delay = new PauseTransition(Duration.seconds(0.25));
                delay.setOnFinished(e -> {
                    view.setCurrentMenu(GameView.OPTIONPANE_INDEX);
                    view.update();
                });
                delay.play();
                view.getSlide().setDuration(Duration.seconds(0.5));
                view.getSlide().playFromStart();
                view.getSlide().setDuration(Duration.seconds(2.5));
            }
        });

        view.getMenuPane().getDeckBuilderBut().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                PauseTransition delay = new PauseTransition(Duration.seconds(0.25));
                delay.setOnFinished(e -> {
                    view.setCurrentMenu(GameView.DECKBUILDERPANE_INDEX);
                    view.update();
                });
                delay.play();
                view.getSlide().setDuration(Duration.seconds(0.5));
                view.getSlide().playFromStart();
                view.getSlide().setDuration(Duration.seconds(2.5));
            }
        });



        view.getOptionPane().getBackToMenu().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                PauseTransition delay = new PauseTransition(Duration.seconds(0.25));
                delay.setOnFinished(e -> {
                    view.setCurrentMenu(GameView.MENUPANE_INDEX);
                    view.update();
                });
                delay.play();
                view.getSlide().setDuration(Duration.seconds(0.5));
                view.getSlide().playFromStart();
                view.getSlide().setDuration(Duration.seconds(2.5));
            }
        });

        view.getOptionPane().getMusicSlider().addEventHandler(MouseEvent.MOUSE_DRAGGED, update);
        view.getOptionPane().getSfxSlider().addEventHandler(MouseEvent.MOUSE_DRAGGED, update);

        view.getDeckBuilderPane().getBackToMenu().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getClickSFX().getMusicMediaPlayer().play();
                view.getClickSFX().getMusicMediaPlayer().seek(Duration.ZERO);
                PauseTransition delay = new PauseTransition(Duration.seconds(0.25));
                delay.setOnFinished(e -> {
                    view.setCurrentMenu(GameView.MENUPANE_INDEX);
                    view.update();
                });
                delay.play();
                view.getSlide().setDuration(Duration.seconds(0.5));
                view.getSlide().playFromStart();
                view.getSlide().setDuration(Duration.seconds(2.5));
            }
        });

        //Remove player one's tile from grid
        for (int i = 0; i < view.getGamePane().getPlayedStonePane().getPsTilePane().getChildren().size(); i++) {
            view.getGamePane().getPlayedStonePane().getPsTilePane().getChildren().get(i).addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    GridCellPane clickedRect = (GridCellPane) mouseEvent.getSource();

                    int boardIndex = view.getGamePane().getPlayedStonePane().getPsTilePane().getChildren().indexOf(clickedRect);

                    //Find Player's stone index
                    StonePane p1StonePane = view.getGamePane().getStonePaneP1();
                    StonePane p2StonePane = view.getGamePane().getStonePaneP2();
                    StonePane turnHolderPane = model.getTurnHolder() == model.getPlayer1() ? p1StonePane : p2StonePane;

                    int stoneIndex = model.getTurnHolder() == model.getPlayer1() ? p1StonePane.getSelectedTile() : p2StonePane.getSelectedTile();

                    if ((p1StonePane.getSelectedTile() >= 0 || p2StonePane.getSelectedTile() >= 0) && model.addStoneToBoard(boardIndex, stoneIndex)) {
                        clickedRect.setStoneOcc(model.getTurnHolder().getActiveDeck().getStones().get(stoneIndex).getName());
                        model.removeStoneFromDeck(stoneIndex);
                        turnHolderPane.getHotBar().getChildren().get(stoneIndex).setVisible(false);
                        turnHolderPane.getHotBar().getChildren().get(stoneIndex).setDisable(true);
                        turnHolderPane.getHotBar().getChildren().get(turnHolderPane.getSelectedTile()).setTranslateX(0);
                        model.battleChecks(model.getBoardDimension(), boardIndex);
                        view.update();
                        turnHolderPane.setSelectedTile(-1);
                        model.setTurnHolder(model.getTurnHolder() == model.getPlayer1() ? model.getPlayer2() : model.getPlayer1());

                    }
                }
            });
        }

        //Select Player one's tiles
        for (int i = 0; i < view.getGamePane().getStonePaneP1().getHotBar().getChildren().size(); i++) {
            view.getGamePane().getStonePaneP1().getHotBar().getChildren().get(i).addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    StonePane p1StonePane = view.getGamePane().getStonePaneP1();
                    ImageView clickedImage = (ImageView) mouseEvent.getSource();
                    int liveIndex = p1StonePane.getHotBar().getChildren().indexOf(clickedImage);

                    //Sets color back to og color
                    if (p1StonePane.getSelectedTile() < p1StonePane.getHotBar().getChildren().size() && p1StonePane.getSelectedTile() >= 0) {
                        if (p1StonePane.getSelectedTile() != liveIndex) {
                            p1StonePane.getHotBar().getChildren().get(p1StonePane.getSelectedTile()).setTranslateX(0);
                        }
                    }
                    p1StonePane.setSelectedTile(liveIndex);
                    p1StonePane.getHotBar().getChildren().get(p1StonePane.getSelectedTile()).setTranslateX(20);
                    p1StonePane.setSelectedTile(p1StonePane.getSelectedTile());
                }
            });
        }

        //Select Player two's tiles
        for (int i = 0; i < view.getGamePane().getStonePaneP2().getHotBar().getChildren().size(); i++) {
            view.getGamePane().getStonePaneP2().getHotBar().getChildren().get(i).addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    StonePane p2StonePane = view.getGamePane().getStonePaneP2();
                    ImageView clickedImage = (ImageView) mouseEvent.getSource();
                    int liveIndex = p2StonePane.getHotBar().getChildren().indexOf(clickedImage);

                    //Sets color back to og color
                    if (p2StonePane.getSelectedTile() < p2StonePane.getHotBar().getChildren().size() && p2StonePane.getSelectedTile() >= 0){
                        if (p2StonePane.getSelectedTile() != liveIndex) {
                            p2StonePane.getHotBar().getChildren().get(p2StonePane.getSelectedTile()).setTranslateX(0);
                        }
                    }

                    p2StonePane.setSelectedTile(liveIndex);
                    p2StonePane.getHotBar().getChildren().get(p2StonePane.getSelectedTile()).setTranslateX(-20);
                    p2StonePane.setSelectedTile(p2StonePane.getSelectedTile());
                }
            });
        }

        primaryStage.setResizable(false);
        primaryStage.setTitle("Skystones JAVA Edition");
        primaryStage.getIcons().add(new Image("file:assets/icon.png"));
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    // launch() is a static method in the Application class
    public static void main(String[] args){launch(args);}

}
