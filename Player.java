package ntu.r09922114.gambling;

import java.util.*;
import ntu.r09922114.binaryTree.*;

class Player {
    private int id;
    private int cardCount;
    private Player nextPlayer, prevPlayer;

    // Use a BST to handle cards
    private BST cards;

    public Player(int id, Card[] cards) {
        this.id = id;
        this.cardCount = cards.length;
        this.cards = new BST(cards);
        nextPlayer = prevPlayer = null;
    }

    public int getID() {
        return id;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public Player getPrevPlayer() {
        return prevPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void setPrevPlayer(Player prevPlayer) {
        this.prevPlayer = prevPlayer;
    }

    // Return all remaining cards in increasing order.
    public void showCards() {
        cards.printInorder();
        System.out.println();
    }

    // If the new card forms a pair with some old card, drop the pair.
    // Otherwise, insert the new card.
    public void insertOrDropPair(Card card) {
        BTNode pairedNode = null;
        if (!card.getRank().equals("0"))
            pairedNode = cards.searchPair(card);
        if (pairedNode == null) {
            cards.insert(card);
            cardCount++;
        } else {
            cards.remove(pairedNode);
            cardCount--;
        }
    }

    // Randomly draw a card in hand.
    public Card DrawCard() {
        Random rnd = new Random();
        int i = rnd.nextInt(cardCount);
        BTNode drawnNode = cards.getCertainNode(i);
        cards.remove(drawnNode);
        cardCount--;
        return (Card) (drawnNode.getData());
    }

    // Return the number of the cards in hand.
    public int getCardCount() {
        return cardCount;
    }
}