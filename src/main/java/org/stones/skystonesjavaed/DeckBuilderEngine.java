package org.stones.skystonesjavaed;

import java.io.*;
import java.util.ArrayList;


public class DeckBuilderEngine implements Serializable{

    private final int MAX_DECK = 20;

    private ArrayList<Stone> allStones;

    private ArrayList<Deck> decks;
    private int selectedDeckIndex = -1;

    public DeckBuilderEngine(){

        String filePath = "src/main/resources/cardBase.csv";
        String line;
        allStones = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("decks.txt"))) {
            decks = (ArrayList<Deck>) in.readObject();
        } catch (IOException | ClassNotFoundException e1) {
            System.out.println("Error loading decks:");
            e1.printStackTrace();
            decks = new ArrayList<Deck>();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //Skip headers
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // naive split, won't handle quoted commas
                String name = values[0];
                //String element = values[1]; not needed now, but it exists.
                int north = Integer.parseInt(values[2]);
                int south = Integer.parseInt(values[3]);
                int east = Integer.parseInt(values[4]);
                int west = Integer.parseInt(values[5]);
                allStones.add(new Stone(name, north, south, east, west, true));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Stone> getAllStones() {return allStones;}
    public ArrayList<Deck> getDecks() {return decks;}
    public int getSelectedDeckIndex() {return selectedDeckIndex;}
    public void setSelectedDeckIndex(int selectedDeckIndex) {this.selectedDeckIndex = selectedDeckIndex;}
    public void changeName(String name){decks.get(selectedDeckIndex).setName(name);}


    public void printAllStones() {
        for (int i = 0; i < allStones.size(); i++) {
            System.out.println(allStones.get(i));
        }
    }

    public void printAllDeckValues(){
        for (Deck deck : decks){
            System.out.println(deck.getStones());
        }
    }

    public void createNewDeck(int deckType){
        if (decks.size() < MAX_DECK && deckType >= 3 && deckType <= 5){
            decks.add(new Deck("Deck " + (decks.size() + 1), deckType));
            selectedDeckIndex = decks.indexOf(decks.getLast());
        }
    }

    public void deleteDeck(){
        if (selectedDeckIndex > -1 && selectedDeckIndex < MAX_DECK) {
            decks.remove(selectedDeckIndex);
            selectedDeckIndex = -1;
        }
    }

    public void addToDeck(int selectedStoneIndex){
        if (selectedDeckIndex > -1 && selectedDeckIndex < MAX_DECK && selectedDeckIndex < decks.size() && selectedStoneIndex >= 0){
            Deck workingDeck = decks.get(selectedDeckIndex);
            if (selectedStoneIndex < allStones.size() && workingDeck.getStones().size() < workingDeck.getRequiredDeckSize()) {
                Stone selectedStone = allStones.get(selectedStoneIndex);
                decks.get(selectedDeckIndex).addStoneToDeck(selectedStone);
            }
        }
    }

    public void removeFromDeck(int selectedStoneIndex){
        if (selectedDeckIndex > -1 && selectedDeckIndex < MAX_DECK && selectedDeckIndex < decks.size() && selectedStoneIndex >= 0){
            if (selectedStoneIndex < decks.get(selectedDeckIndex).getStones().size()) {
                Stone selectedStone = decks.get(selectedDeckIndex).getStones().get(selectedStoneIndex);
                decks.get(selectedDeckIndex).removeStoneFromDeck(selectedStone);
            }
        }
    }

    public boolean saveDecks() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("decks.txt", false));
            out.writeObject(decks);
            return true;
        } catch (IOException e1) {
            return false;
        }
    }

}
