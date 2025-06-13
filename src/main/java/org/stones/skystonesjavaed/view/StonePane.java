package org.stones.skystonesjavaed.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import org.stones.skystonesjavaed.model.Player;
import org.stones.skystonesjavaed.model.Stone;

public class StonePane extends Pane {

    private TilePane hotBar;
    private int selectedTile = -1;
    private Player player;

    public StonePane(Player player){

        this.player = player;

        hotBar = new TilePane();
        hotBar.setPrefColumns(1);
        hotBar.setVgap(5);

        for (int i = 0; i < player.getActiveDeck().getStones().size(); i++){
            Image stone = new Image("file:assets/baseStones/" + player.getActiveDeck().getStones().get(i).getName() + ".png");
            ImageView stoneView = new ImageView(stone);
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
