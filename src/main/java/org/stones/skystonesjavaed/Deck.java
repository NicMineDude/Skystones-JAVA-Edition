package org.stones.skystonesjavaed;
import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {

    private ArrayList<Stone> stones;
    private int deckType;
    private String name;
    private int requiredDeckSize;

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

    public Deck(String name, int deckType){
        this.stones = new ArrayList<>();
        this.deckType = deckType;
        int boardSize = deckType * deckType;
        int boardOddOrEven = boardSize % 2 == 0 ? 2 : 1;
        this.requiredDeckSize = (boardSize + boardOddOrEven)/2;
    }

    //For copying decks, used in gameplay. Mainly in the player class
    public Deck(Deck deck){
        this.stones = new ArrayList<>();
        for (Stone s : deck.getStones()){
            stones.add(new Stone(s.getName(), s.getNorth(), s.getSouth(), s.getEast(), s.getWest(), s.getStoneColor()));
        }
    }

    public void addStoneToDeck(Stone stone){
        this.stones.add(stone);
    }

    public void removeStoneFromDeck(Stone stone){
        this.stones.remove(stone);
    }

    public boolean validateDeck(){
        int boardSize = deckType * deckType;
        int boardOddOrEven = boardSize % 2 == 0 ? 2 : 1;
        return (boardOddOrEven + boardSize)/2 == this.stones.size();
    }

    public ArrayList<Stone> getStones() {return stones;}
    public int getDeckType() {return deckType;}
    public void setName(String name){this.name = name;}
    public int getRequiredDeckSize(){return requiredDeckSize;}

}
