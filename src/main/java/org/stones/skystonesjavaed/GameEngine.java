package org.stones.skystonesjavaed;
public class GameEngine {


    BoardTile[] gameBoard;
    private Player player1;
    private Player player2;
    private Player turnHolder;

    private int p1Score = 0;
    private int p2Score = 0;

    private int n = 3;

    public GameEngine() {
        this.player1 = new Player(true, "Player 1");
        this.player2 = new Player(false, "Player 2");

        this.turnHolder = player1;

        this.gameBoard = new BoardTile[n*n];
        for (int i = 0; i < (n*n); i++){
            this.gameBoard[i] = new BoardTile(null);
        }

    }

    public Player getPlayer1(){return player1;}
    public Player getPlayer2(){return player2;}
    public Player getTurnHolder() {return turnHolder;}
    public void setTurnHolder(Player player) {this.turnHolder = player;}
    public int getP1Score() {return p1Score;}
    public int getP2Score() {return p2Score;}
    public int getBoardDimension() {return n;}

    public void printGameBoard() {
        String line = "";
        for (int i = 0; i < n*n; i++) {
            line += gameBoard[i].toString() + " ";
            if ((i + 1) % 3 == 0) {
                System.out.println(line);
                line = "";
            }
        }
    }

    public void resetGame(){
        this.player1.setActiveDeck(player1.getDecks().getFirst());
        this.player2.setActiveDeck(player2.getDecks().getFirst());

        this.p1Score = 0;
        this.p2Score = 0;

        this.turnHolder = player1;

        this.gameBoard = new BoardTile[n*n];
        for (int i = 0; i < (n*n); i++){
            this.gameBoard[i] = new BoardTile(null);
        }
    }

    public void removeStoneFromDeck(int index){
        if (index >= 0 && index < turnHolder.getActiveDeck().getStones().size()){
            turnHolder.getActiveDeck().getStones().remove(index);
            turnHolder.getActiveDeck().getStones().add(index, null);
        }
    }

    public boolean addStoneToBoard(int indexBoard, int indexStone){
        if (indexBoard < gameBoard.length && indexBoard >= 0 && indexStone >= 0 && indexStone < turnHolder.getActiveDeck().getStones().size()) {
            if (gameBoard[indexBoard].toString().equals("O")) {
                gameBoard[indexBoard].setOccupation(turnHolder.getActiveDeck().getStones().get(indexStone));
                return true;
            } else {
                System.out.println("\nAlready a stone there!");
                return false;
            }
        } else {
            System.out.println("Board index or Stone index out of bounds!");
            return false;
        }
    }


    public void battleChecks(int n, int index){
        //int col = index % n;
        //int row = (index - col)/n;
        if (index < n*n && index >= 0) {
            Stone mainStone = gameBoard[index].getOccupation();

            Stone eastStone = (index + 1) < (n*n) && ((index + 1) % n != 0) ? gameBoard[index + 1].getOccupation() : null;
            Stone westStone = (index - 1) >= 0 && ((index - 1) % n != (n - 1)) ? gameBoard[index - 1].getOccupation() : null;
            Stone northStone = (index - n) >= 0 ? gameBoard[index - n].getOccupation() : null;
            Stone southStone = (index + n) < (n*n) ? gameBoard[index + n].getOccupation() : null;

            Stone[] oppStones = {eastStone, westStone, southStone, northStone};
            int[] mainStoneDirection = {mainStone.getEast(), mainStone.getWest(), mainStone.getSouth(), mainStone.getNorth()};
            int[] oppStonesDirection = {eastStone != null ? eastStone.getWest() : -1,
                    westStone != null ? westStone.getEast() : -1,
                    southStone != null ? southStone.getNorth() : -1,
                    northStone != null ? northStone.getSouth() : -1};

            for (int i = 0; i < 4; i++) {
                if (oppStonesDirection[i] != -1) {
                    if (turnHolder.getPlayerColor() != oppStones[i].getStoneColor()) {
                        if (mainStoneDirection[i] > oppStonesDirection[i]) {
                            oppStones[i].setStoneColor(turnHolder.getPlayerColor());
                        } else {
                            System.out.println("Lost the Arrow Battle!");
                        }
                    }
                }
            }
        }
    }

    public boolean updateBoard(){
        int p1 = 0, p2 = 0;
        for (int i = 0; i < gameBoard.length; i++){
            if (gameBoard[i].getOccupation() != null){
                if (gameBoard[i].getOccupation().getStoneColor()){
                    p1++;
                } else {
                    p2++;
                }
            }
        }
        this.p1Score = p1;
        this.p2Score = p2;

        if (p1 + p2 == 9){
            System.out.println("Game Over! Player 1: " + p1 +", Player 2: " + p2 );
            return true;
        }
        return false;
    }

}
