package ntu.r09922114.gambling;

import java.util.*;
import ntu.r09922114.util.*;

public class Old_maid {
    private Player[] players;
    private int playerCount;

    // Build a card stack
    protected Card[] allCards;
    protected static String[] rankList = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    protected static char[] suitList = { 'C', 'D', 'H', 'S' };

    protected void buildDack() {
        allCards = new Card[53];
        int k = 0;
        for (char suit : suitList)
            for (String rank : rankList)
                allCards[k++] = new Card(suit, rank);
        allCards[52] = new Card('B', "0");
    }

    public boolean isPair(Card a, Card b) {
        return !a.getRank().equals("0") && a.getRank().equals(b.getRank());
    }

    public Old_maid(int playerCount) {
        // assign playerCount
        this.playerCount = playerCount;

        // Build the card dack
        buildDack();

        // Shuffle cards
        Shuffle.shuffleArray(allCards);

        // Split cards to every player
        Card[][] cardsForEveryone = distributeCards();

        // Sort
        for (int i = 0; i < cardsForEveryone.length; i++)
            Sort.sort(cardsForEveryone[i]);

        // Show cards before removing pairs
        System.out.println("Deal cards");
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Player" + i + ": ");
            showCardList(cardsForEveryone[i]);
        }

        // Remove pairs and distribute to players
        players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++)
            players[i] = new Player(i, removePairs(cardsForEveryone[i]));

        // Set playing order
        for (int i = 0; i < playerCount; i++) {
            players[i].setNextPlayer(players[(i + 1) % playerCount]);
            players[i].setPrevPlayer(players[(i - 1 + playerCount) % playerCount]);
        }

        // Show cards after dropping pairs
        System.out.println("Drop cards");
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Player" + i + ": ");
            players[i].showCards();
        }
    }

    // distribute cards evenly to every player
    private Card[][] distributeCards() {
        Card[][] cardsForEveryone = new Card[playerCount][];
        int quotient = allCards.length / playerCount;
        int remainder = allCards.length % playerCount;
        int front = 0;
        for (int i = 0; i < playerCount; i++) {
            if (i < remainder) {
                cardsForEveryone[i] = Arrays.copyOfRange(allCards, front, front + quotient + 1);
                front += quotient + 1;
            } else {
                cardsForEveryone[i] = Arrays.copyOfRange(allCards, front, front + quotient);
                front += quotient;
            }
        }
        return cardsForEveryone;
    }

    // Remove cards in pair in a sorted array
    private Card[] removePairs(Card[] cardList) {
        int i = 0, j = 0;
        while (j < cardList.length) {
            if (j < cardList.length - 1 && isPair(cardList[j], cardList[j + 1]))
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

        int finishPlayerCount = 0;
        while (playerCount - finishPlayerCount > 1) {
            next = current.getNextPlayer();

            // Draw a card from the next player
            Card c = next.DrawCard();
            current.insertOrDropPair(this, c);
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
                finishPlayerCount += 2;
                current.getPrevPlayer().setNextPlayer(next.getNextPlayer());
                next.getNextPlayer().setPrevPlayer(current.getPrevPlayer());
                current = next.getNextPlayer();
            } else if (current.getCardCount() == 0) {
                System.out.println("Player" + current.getID() + " wins");
                finishPlayerCount++;
                current.getPrevPlayer().setNextPlayer(next);
                current.getNextPlayer().setPrevPlayer(current.getPrevPlayer());
                current = next;
            } else if (next.getCardCount() == 0) {
                System.out.println("Player" + next.getID() + " wins");
                finishPlayerCount++;
                next.getNextPlayer().setPrevPlayer(current);
                next.getPrevPlayer().setNextPlayer(next.getNextPlayer());
                current = next.getNextPlayer();
            } else {
                current = next;
            }

            if (!isBasicOver && finishPlayerCount > 0) {
                isBasicOver = true;
                System.out.println("Basic game over");
                System.out.println("Bonus game start");
            }
        }

        System.out.println("Bonus game over");
    }
}