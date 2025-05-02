package org.stones.skystonesjavaed;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class GridPane extends TilePane{

    public static final int TILE_WIDTH = 150;
    public static final int TILE_HEIGHT = 150;

    private TilePane gridPane;

    private ImageView gridTile ;

    public GridPane(GameEngine model){
        gridPane = new TilePane();
        gridPane.setPrefColumns(3);
        gridPane.setVgap(8);
        gridPane.setHgap(8);

        for (int i = 0; i < 9; i++){
            gridTile = new ImageView(new Image("file:assets/gridtile.png"));
            gridTile.setFitWidth(TILE_WIDTH);
            gridTile.setFitHeight(TILE_HEIGHT);
            gridPane.getChildren().addAll(gridTile);
        }

        this.getChildren().add(gridPane);
    }

    public TilePane getGridPane(){return gridPane;}
    public ImageView getGridTile(){return gridTile;}
}
