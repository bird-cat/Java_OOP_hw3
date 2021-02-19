package ntu.r09922114.gambling;

import java.util.*;
import ntu.r09922114.util.Comparable;

public class Card implements Comparable {
    private char suit;
    private String rank;

    static HashMap<String, Integer> rankOrder;
    static HashMap<Character, Integer> suitOrder;
    static {
        rankOrder = new HashMap<String, Integer>();
        rankOrder.put("0", 0);
        rankOrder.put("2", 1);
        rankOrder.put("3", 2);
        rankOrder.put("4", 3);
        rankOrder.put("5", 4);
        rankOrder.put("6", 5);
        rankOrder.put("7", 6);
        rankOrder.put("8", 7);
        rankOrder.put("9", 8);
        rankOrder.put("10", 9);
        rankOrder.put("J", 10);
        rankOrder.put("Q", 11);
        rankOrder.put("K", 12);
        rankOrder.put("A", 13);

        suitOrder = new HashMap<Character, Integer>();
        suitOrder.put('C', 0);
        suitOrder.put('D', 1);
        suitOrder.put('H', 2);
        suitOrder.put('S', 3);
        suitOrder.put('R', 4);
        suitOrder.put('B', 5);
    }

    public Card(char suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String toString() {
        return suit + rank;
    }

    public char getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int compare(Comparable item) {
        Card card = (Card) item;
        int d1 = rankOrder.get(this.rank) - rankOrder.get(card.rank);
        int d2 = suitOrder.get(this.suit) - suitOrder.get(card.suit);
        return (d1 != 0) ? d1 : d2;
    }
}