package org.stones.skystonesjavaed;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameView extends Pane {
    private GameEngine model;

    private MenuPane menuPane;
    private GamePane gamePane;
    private OptionPane optionPane;
    private DeckBuilderPane deckBuilderPane;
    private InGameOptionPane inGameOptionPane;

    public static final int MENUPANE_INDEX = 0;
    public static final int GAMEPANE_INDEX = 1;
    public static final int OPTIONPANE_INDEX = 2;
    public static final int DECKBUILDERPANE_INDEX = 3;
    public static final int INGAMEOPTIONPANE_INDEX = 4;

    private int currentMenu = 0;
    private Pane[] menuArray;


    private TranslateTransition slide;

    private MusicPlayer music;
    private MusicPlayer clickSFX;


    public GameView(GameEngine initModel){
        model = initModel;

        menuPane = new MenuPane(model);
        menuPane.setPrefSize(GameApp.windowWidth, GameApp.windowHeight);

        gamePane = new GamePane(model);
        gamePane.setPrefSize(GameApp.windowWidth, GameApp.windowHeight);
        gamePane.setVisible(false);
        gamePane.setDisable(true);


        optionPane = new OptionPane(model);
        optionPane.setPrefSize(GameApp.windowWidth,GameApp.windowHeight);
        optionPane.setVisible(false);
        optionPane.setDisable(true);

        deckBuilderPane = new DeckBuilderPane(model);
        deckBuilderPane.setPrefSize(GameApp.windowWidth, GameApp.windowHeight);
        deckBuilderPane.setVisible(false);
        deckBuilderPane.setDisable(true);

        inGameOptionPane = new InGameOptionPane(model);
        inGameOptionPane.setPrefSize(GameApp.windowWidth,GameApp.windowHeight);
        inGameOptionPane.setVisible(false);
        inGameOptionPane.setDisable(true);

        //To be made into a transition Player!!!
        Rectangle rect = new Rectangle(GameApp.windowWidth*2, 769, Color.BLACK);
        rect.setLayoutX(-GameApp.windowWidth*2);
        rect.setLayoutY(0);
        rect.toFront();

        slide = new TranslateTransition(Duration.seconds(2.5), rect);
        slide.setFromX(-GameApp.windowWidth*2);  // Starting X offset
        slide.setToX(GameApp.windowWidth*4);       // End at center
        slide.setCycleCount(1);
        slide.setAutoReverse(false);

        music = new MusicPlayer("music/skystonesThemeLooped.mp3", MediaPlayer.INDEFINITE, MusicPlayer.MUSIC_VOL);
        music.getMusicMediaPlayer().play();
        music.getMusicMediaPlayer().volumeProperty().bind(optionPane.getMusicSlider().valueProperty());

        clickSFX = new MusicPlayer("music/stonesfxclick.mp3" , 1, MusicPlayer.SFX_VOL);
        clickSFX.getMusicMediaPlayer().volumeProperty().bind(optionPane.getSfxSlider().valueProperty());

        menuArray = new Pane[]{menuPane, gamePane, optionPane, deckBuilderPane};

        getChildren().addAll(menuPane, gamePane, optionPane, deckBuilderPane, inGameOptionPane, rect);
    }

    public void resetGameView() {
        //Reset the gridPane back to normal neutral tiles and reset the Played stone plan to clear png's
        for (int i = 0; i < model.gameBoard.length; i++){
                ImageView childGrid = (ImageView) gamePane.getGameBoardPane().getGridPane().getChildren().get(i);
                childGrid.setImage(new Image("file:assets/gridtile.png"));

                ImageView childStoneGrid = (ImageView) gamePane.getPlayedStonePane().getPsTilePane().getChildren().get(i);
                childStoneGrid.setImage(new Image("file:assets/clear.png"));
        }
        //Reset the stones position and ability and visility inside the their STONEPANES!
        for (int i = 0; i < model.getPlayer1().getActiveDeck().getStones().size(); i++){
            ImageView p1Stones =  (ImageView) gamePane.getStonePaneP1().getHotBar().getChildren().get(i);
            ImageView p2Stones = (ImageView) gamePane.getStonePaneP2().getHotBar().getChildren().get(i);

            p1Stones.setVisible(true);
            p1Stones.setDisable(false);

            p2Stones.setVisible(true);
            p2Stones.setDisable(false);

            p1Stones.setTranslateX(0);
            p2Stones.setTranslateX(0);
        }

        //Makes backToMenu and Reset and win prompt Nodes invisible and disable
        gamePane.getReset().setDisable(true);
        gamePane.getReset().setVisible(false);
        gamePane.getBackToMenu().setDisable(true);
        gamePane.getBackToMenu().setVisible(false);
        gamePane.getOptionsButton().setDisable(false);
        gamePane.getOptionsButton().setVisible(true);
        gamePane.getWinMessage().setVisible(false);

        //Resets selected tile back to unslected (-1).
        gamePane.getStonePaneP1().setSelectedTile(-1);
        gamePane.getStonePaneP2().setSelectedTile(-1);
    }

    public void update(){

        //Updates music volume label
        optionPane.getMusicVolLabel().setText("Music Volume: "+ String.format("%.2f", MusicPlayer.MUSIC_VOL*100));
        optionPane.getSfxVolLabel().setText("SFX Volume: "+ String.format("%.2f", MusicPlayer.SFX_VOL*100));

        inGameOptionPane.getMusicVolLabel().setText("Music Volume: "+ String.format("%.2f", MusicPlayer.MUSIC_VOL*100));
        inGameOptionPane.getSfxVolLabel().setText("SFX Volume: "+ String.format("%.2f", MusicPlayer.SFX_VOL*100));


        //
        if (menuPane.getPlayButton().isDisabled() || gamePane.getBackToMenu().isDisabled()){
            menuPane.getPlayButton().setDisable(false);
            gamePane.getBackToMenu().setDisable(false);
        }

        //Menu switch logic
        for (int i = 0; i < menuArray.length; i++){
            if (i == currentMenu){
                menuArray[i].setVisible(true);
                menuArray[i].setDisable(false);
            } else {
                menuArray[i].setVisible(false);
                menuArray[i].setDisable(true);
            }
        }

        //Tile updating
        for (int i = 0; i < model.gameBoard.length; i++){
            if (model.gameBoard[i].getOccupation() != null) {
                if (model.gameBoard[i].getOccupation().getStoneColor()) {
                    ImageView child = (ImageView) gamePane.getGameBoardPane().getGridPane().getChildren().get(i);
                    child.setImage(new Image("file:assets/redgridtile.png"));
                } else {
                    ImageView child = (ImageView) gamePane.getGameBoardPane().getGridPane().getChildren().get(i);
                    child.setImage(new Image("file:assets/bluegridtile.png"));
                }
            }
        }
        if (model.updateBoard()){
            for (int i = 0; i < gamePane.getStonePaneP1().getHotBar().getChildren().size(); i++){
                ImageView p1Stones =  (ImageView) gamePane.getStonePaneP1().getHotBar().getChildren().get(i);
                p1Stones.setDisable(true);
                p1Stones.setVisible(false);
            }
            for (int i = 0; i < gamePane.getStonePaneP2().getHotBar().getChildren().size(); i++){
                ImageView p2Stones = (ImageView) gamePane.getStonePaneP2().getHotBar().getChildren().get(i);
                p2Stones.setDisable(true);
                p2Stones.setVisible(false);
            }
            gamePane.getWinMessage().setVisible(true);
            gamePane.getWinMessage().setText(model.getP1Score() > model.getP2Score() ? "PLAYER 1 WINS!"
                    : model.getP1Score() == model.getP2Score() ? "TIE! Score "
                    : "PLAYER 2 WINS!" );
            gamePane.getBackToMenu().setDisable(false);
            gamePane.getBackToMenu().setVisible(true);
            gamePane.getReset().setDisable(false);
            gamePane.getReset().setVisible(true);
            gamePane.getOptionsButton().setDisable(true);
            gamePane.getOptionsButton().setVisible(false);
        }

        gamePane.getP1Label().setText("Player 1   " + model.getP1Score());
        gamePane.getP2Label().setText("Player 2   " + model.getP2Score());
    }



    public MenuPane getMenuPane() {return menuPane;}
    public GamePane getGamePane() {return gamePane;}
    public OptionPane getOptionPane() {return optionPane;}
    public DeckBuilderPane getDeckBuilderPane() {return deckBuilderPane;}
    public InGameOptionPane getInGameOptionPane() {return inGameOptionPane;}
    public TranslateTransition getSlide(){return slide;}
    public MusicPlayer getClickSFX(){return clickSFX;}
    public MusicPlayer getMusic(){return music;}
    public void setCurrentMenu(int index) {this.currentMenu = index;}
    public int getCurrentMenu() {return currentMenu;}

}
