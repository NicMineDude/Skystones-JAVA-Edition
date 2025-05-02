package org.stones.skystonesjavaed;
import java.util.ArrayList;

public class Deck {

    private ArrayList<Stone> stones;

    public Deck(Stone stone1, Stone stone2, Stone stone3, Stone stone4, Stone stone5){
        this.stones = new ArrayList<>();
        try{
            stones.add(stone1);
            stones.add(stone2);
            stones.add(stone3);
            stones.add(stone4);
            stones.add(stone5);
        } catch (Exception error){
            error.printStackTrace();
        }
    }

    public Deck(Deck deck){
        this.stones = new ArrayList<>();
        for (Stone s : deck.getStones()){
            stones.add(new Stone(s.getName(), s.getNorth(), s.getSouth(), s.getEast(), s.getWest(), s.getStoneColor()));
        }
    }

    public ArrayList<Stone> getStones() {return stones;}

}
