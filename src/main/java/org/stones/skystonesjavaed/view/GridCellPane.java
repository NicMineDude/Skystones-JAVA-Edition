package org.stones.skystonesjavaed.view;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GridCellPane extends ImageView {

    private BoardTile boardTile;
    private Image stoneOcc;


    public GridCellPane(Image image, BoardTile boardTile){
        super(image);
        this.boardTile = boardTile;
        this.stoneOcc = image;
    }

    public void setStoneOcc(String stone){
        this.stoneOcc = new Image("file:assets/baseStones/" + stone + ".png");
        setImage(this.stoneOcc);
    }

    public String toString(){
        return "" + boardTile;
    }
}
