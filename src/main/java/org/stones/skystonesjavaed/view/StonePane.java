package org.stones.skystonesjavaed.view;

import javafx.animation.Animation;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import org.stones.skystonesjavaed.model.Player;
import org.stones.skystonesjavaed.model.Stone;

public class StonePane extends Pane {

    private TilePane hotBar;
    //Index of the players selectedTile in game.
    private int selectedTile = -1;
    private Player player;

    public StonePane(Player player){
        //The defines the player
        this.player = player;
        //This initial angle of the stones in the players decks (Different angles for each player)
        double imageRotationDegree = player.getPlayerColor() ? 4 : -4;
        //This is the fromAngle of the selected stone rotation animation. the toAngle is the negation of this. see below.
        double angleNegation = player.getPlayerColor() ? 2.5 : -2.5;
        //instatiates the TilePane with a column of 1 and gaps of 5 pixels(Double check this) between tiles.
        hotBar = new TilePane();
        hotBar.setPrefColumns(1);
        hotBar.setVgap(5);

        //For each stones in the model players deck we get the images data and put it into a wrapper class for an ImageView (AnimatedStones).
        for (int i = 0; i < player.getActiveDeck().getStones().size(); i++){
            Image stone = new Image("file:assets/baseStones/" + player.getActiveDeck().getStones().get(i).getName() + ".png");
            //This class is to set the nice angle and rotation animations for the players stones in their deck.
            AnimatedStones stoneView = new AnimatedStones(stone, imageRotationDegree, angleNegation, -angleNegation, 1500, Animation.INDEFINITE);
            stoneView.setFitWidth(Stone.WIDTH);
            stoneView.setFitHeight(Stone.HEIGHT);
            hotBar.getChildren().add(stoneView);
        }
        this.getChildren().addAll(hotBar);
    }

    public TilePane getHotBar() {return hotBar;}
    public int getSelectedTile() {return selectedTile;}
    public void setSelectedTile(int i) {this.selectedTile = i;}
}
