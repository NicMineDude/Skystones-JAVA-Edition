package org.stones.skystonesjavaed;

import javafx.scene.shape.Rectangle;

public class BoardTile {

    private Stone occupation;

    public BoardTile(Stone occupation){
        this.occupation = occupation;
    }

    public String toString() {
        return occupation == null ? "O" : occupation.toString();
    }


    public Stone getOccupation(){return occupation;}
    public void setOccupation(Stone stone){this.occupation = stone;}

}
