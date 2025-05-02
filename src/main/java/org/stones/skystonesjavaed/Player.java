package org.stones.skystonesjavaed;
import java.util.ArrayList;

public class Player {

    private String name;

    private boolean playerColor;
    private ArrayList<Deck> decks;
    private Deck activeDeck;


    public Player(boolean playerColor, String name){
        //Testing purposes
         Deck p1Deck = new Deck(new Stone("CONQUERTRON",4,4,4,4, playerColor),
                                        new Stone("Arkeyan Jouster 3",3,0,2,2, playerColor),
                                        new Stone("Chompy Bot 9000 4",2,2,2,2, playerColor),
                                        new Stone("Drow Lance Master 2",2,0,0,2,playerColor),
                                        new Stone("Mace Major 4",1,3,1,3, playerColor));

         Deck p2Deck = new Deck(new Stone("CONQUERTRON",4,4,4,4, playerColor),
                                        new Stone("Enfuego Chompy",1,0,0,0, playerColor),
                                        new Stone("Arkeyan Crackler",2,2,3,1, playerColor),
                                        new Stone("Goliath Drow",4,0,1,1	,playerColor),
                                        new Stone("Mohawk Cyclops 4",3,1,3,1, playerColor));

        this.name = name;
        this.decks = new ArrayList<Deck>();
        this.playerColor = playerColor;
        decks.add(playerColor ? new Deck(p1Deck) : new Deck(p2Deck));
        this.activeDeck = new Deck(decks.getFirst());
    }

    public String toString(){
     return name;
    }

    public void setName(String name) {this.name = name;}
    public ArrayList<Deck> getDecks() {return decks;}
    public void setActiveDeck(Deck deck) {this.activeDeck = new Deck(deck);}
    public Deck getActiveDeck() {return activeDeck;}
    public boolean getPlayerColor(){return playerColor;}

}
