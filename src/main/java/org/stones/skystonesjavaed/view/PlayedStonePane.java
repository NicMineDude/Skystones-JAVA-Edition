package org.stones.skystonesjavaed.view;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import org.stones.skystonesjavaed.model.GameEngine;

public class PlayedStonePane extends TilePane {

    public static final int TILE_WIDTH = 115;
    public static final int TILE_HEIGHT = 115;

    private TilePane psTilePane;

    private Image playedTile = new Image("file:assets/clear.png");

    public PlayedStonePane(GameEngine model){
        psTilePane = new TilePane();
        psTilePane.setPrefColumns(3);
        psTilePane.setVgap(43);
        psTilePane.setHgap(43);

        for (int i = 0; i < 9; i++){
            GridCellPane tile = new GridCellPane(playedTile, model.gameBoard[i]);
            tile.setFitWidth(TILE_WIDTH);
            tile.setFitHeight(TILE_HEIGHT);
            tile.setPickOnBounds(true);
            psTilePane.getChildren().add(tile);
        }

        this.getChildren().add(psTilePane);
    }

    public void setPlayedTile(String filepath) {this.playedTile = new Image(filepath);}
    public TilePane getPsTilePane(){return psTilePane;}

}
