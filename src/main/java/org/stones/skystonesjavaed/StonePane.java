package org.stones.skystonesjavaed;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.image.Image;

public class StonePane extends TilePane {

    private TilePane hotBar;
    private int selectedTile = -1;
    private Player player;

    public StonePane(Player player){

        this.player = player;

        hotBar = new TilePane();
        hotBar.setPrefColumns(1);
        hotBar.setVgap(5);



        for (int i = 0; i < player.getActiveDeck().getStones().size(); i++){
            Image stone0 = new Image("file:assets/baseStones/"+ player.getActiveDeck().getStones().get(i).getName() + ".png");
            ImageView stoneView = new ImageView(stone0);
            stoneView.setFitWidth(Stone.WIDTH);
            stoneView.setFitHeight(Stone.HEIGHT);
            hotBar.getChildren().addAll(stoneView);
        }

        this.getChildren().addAll(hotBar);
    }

    public TilePane getHotBar() {return hotBar;}
    public int getSelectedTile() {return selectedTile;}
    public void setSelectedTile(int i) {this.selectedTile = i;}
}
