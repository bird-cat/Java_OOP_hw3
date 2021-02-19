package ntu.r09922114.gambling;

import java.util.*;
import ntu.r09922114.util.*;

public class Old_maid {
    private Player[] players = new Player[4];
    private int playerCount = 4;

    // Build a card stack
    private static Card[] allCards = new Card[54];
    private static String[] rankList = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    private static char[] suitList = { 'C', 'D', 'H', 'S' };
    static {
        int k = 0;
        for (char suit : suitList)
            for (String rank : rankList)
                allCards[k++] = new Card(suit, rank);
        allCards[52] = new Card('R', "0");
        allCards[53] = new Card('B', "0");
    }

    public Old_maid() {
        // Shuffle cards
        Shuffle.shuffleArray(allCards);

        // Split cards into 4 parts
        Card[] cards0 = Arrays.copyOfRange(allCards, 0, 14);
        Card[] cards1 = Arrays.copyOfRange(allCards, 14, 28);
        Card[] cards2 = Arrays.copyOfRange(allCards, 28, 41);
        Card[] cards3 = Arrays.copyOfRange(allCards, 41, 54);

        // Sort
        Sort.sort(cards0);
        Sort.sort(cards1);
        Sort.sort(cards2);
        Sort.sort(cards3);

        // Show cards before removing pairs
        System.out.println("Deal cards");
        System.out.print("Player0: ");
        showCardList(cards0);
        System.out.print("Player1: ");
        showCardList(cards1);
        System.out.print("Player2: ");
        showCardList(cards2);
        System.out.print("Player3: ");
        showCardList(cards3);

        // Remove pairs and distribute to players
        players[0] = new Player(0, removePairs(cards0));
        players[1] = new Player(1, removePairs(cards1));
        players[2] = new Player(2, removePairs(cards2));
        players[3] = new Player(3, removePairs(cards3));

        // Set playing order
        for (int i = 0; i < 4; i++) {
            players[i].setNextPlayer(players[(i + 1) % 4]);
            players[i].setPrevPlayer(players[(i - 1 + 4) % 4]);
        }

        // Show cards after dropping pairs
        System.out.println("Drop cards");
        for (int i = 0; i < 4; i++) {
            System.out.print("Player" + i + ": ");
            players[i].showCards();
        }
    }

    // Remove cards in pair in a sorted array
    private Card[] removePairs(Card[] cardList) {
        int i = 0, j = 0;
        while (j < cardList.length) {
            if (j < cardList.length - 1 &&
                !cardList[j].getRank().equals("0") &&
                cardList[j].getRank().equals(cardList[j + 1].getRank()))
                j += 2;
            else {
                cardList[i++] = cardList[j];
                j++;
            }
        }
        return Arrays.copyOfRange(cardList, 0, i);
    }

    // Show all the cards in a list
    private void showCardList(Card[] cardList) {
        for (Card card : cardList) {
            System.out.print(card.toString() + " ");
        }
        System.out.println();
    }

    // Play game
    public void startGame() {
        System.out.println("Game start");
        Player current = players[0], next;
        boolean isBasicOver = false;

        while (playerCount > 1) {
            next = current.getNextPlayer();

            // Draw a card from the next player
            Card c = next.DrawCard();
            current.insertOrDropPair(c);
            System.out.println(
                    "Player" + current.getID() + " draws a card from Player" + next.getID() + " " + c.toString());

            // Show the rest cards of the current player
            System.out.print("Player" + current.getID() + ": ");
            current.showCards();

            // Show the rest cards of the next player
            System.out.print("Player" + next.getID() + ": ");
            next.showCards();

            // Check has someone won and update the next player
            if (current.getCardCount() == 0 && next.getCardCount() == 0) {
                System.out.println("Player" + current.getID() + " and" + " Player" + next.getID() + " win");
                playerCount -= 2;
                current.getPrevPlayer().setNextPlayer(next.getNextPlayer());
                next.getNextPlayer().setPrevPlayer(current.getPrevPlayer());
                current = next.getNextPlayer();
            } else if (current.getCardCount() == 0) {
                System.out.println("Player" + current.getID() + " wins");
                playerCount--;
                current.getPrevPlayer().setNextPlayer(next);
                current.getNextPlayer().setPrevPlayer(current.getPrevPlayer());
                current = next;
            } else if (next.getCardCount() == 0) {
                System.out.println("Player" + next.getID() + " wins");
                playerCount--;
                next.getNextPlayer().setPrevPlayer(current);
                next.getPrevPlayer().setNextPlayer(next.getNextPlayer());
                current = next.getNextPlayer();
            } else {
                current = next;
            }

            if (!isBasicOver && playerCount < 4) {
                isBasicOver = true;
                System.out.println("Basic game over");
                System.out.println("Bonus game start");
            }
        }

        System.out.println("Bonus game over");
    }
}