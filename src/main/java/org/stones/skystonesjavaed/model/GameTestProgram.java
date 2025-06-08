package org.stones.skystonesjavaed.model;

import java.util.Scanner;

public class GameTestProgram {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        GameEngine engine = new GameEngine();
        engine.printGameBoard();

        Player[] players = new Player[]{engine.getPlayer1(), engine.getPlayer2()};

        int turnSwitch = 0;

        boolean winCondition;

        do {
            engine.setTurnHolder(players[turnSwitch]);
            System.out.println(engine.getTurnHolder().getActiveDeck().getStones());

            while (true) {
                System.out.println("Add a Stone " + engine.getTurnHolder() + ", board index then stone index: ");
                int boardIndex = in.nextInt();
                int stoneIndex = in.nextInt();

                if (engine.addStoneToBoard(boardIndex, stoneIndex)){
                    engine.removeStoneFromDeck(stoneIndex);
                    engine.battleChecks(engine.getBoardDimension(), boardIndex);
                    winCondition = engine.updateBoard();
                    break;
                }
            }
            if (winCondition){break;}

            engine.printGameBoard();

            turnSwitch = turnSwitch == 0 ? 1 : 0;

        } while (true);
    }
}
