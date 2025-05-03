package org.stones.skystonesjavaed;

import java.util.Scanner;

public class DeckBuilderTestProgram {
    public static void main(String[] args){
        DeckBuilderEngine deckModel = new DeckBuilderEngine();
//        deckModel.printAllStones();

        Scanner in = new Scanner(System.in);

        while(true){

            System.out.println("What kind of deck? 3x3, 4x4, 5x5? (i.e, 3, 4 or 5)");
            int type = in.nextInt();
            deckModel.createNewDeck(type);

            System.out.println("Select Deck:");
            System.out.println(deckModel.getDecks());

            int selDeck = in.nextInt();
            deckModel.setSelectedDeckIndex(selDeck);

            deckModel.printAllStones();
            System.out.println("");
            System.out.println("Give the index of the stone you want to add!");
            int index = in.nextInt();
            deckModel.addToDeck(index);

            System.out.println("\nItems in deck:");
            System.out.println(deckModel.getDecks().get(deckModel.getSelectedDeckIndex()).getStones());

            System.out.println("Give the index of the stone you want to remove!");
            int indexR = in.nextInt();
            deckModel.removeFromDeck(indexR);

            System.out.println("\nItems in deck:");
            System.out.println(deckModel.getDecks().get(deckModel.getSelectedDeckIndex()).getStones());

//            System.out.println("Deleting Deck");
//            System.out.println(deckModel.getDecks());
//            deckModel.deleteDeck();
//            System.out.println(deckModel.getDecks());

            deckModel.saveDecks();
            break;
        }
    }


}
