package ntu.r09922114.gambling;

import java.util.*;
import ntu.r09922114.gambling.Card;
import ntu.r09922114.gambling.Old_maid;

public class NoJoker_old_maid extends Old_maid {
    public NoJoker_old_maid(int playerCount) {
        super(playerCount);
    }

    private Card randomChooseOneCard() {
        Random rnd = new Random();
        int rank_index = rnd.nextInt(13);
        int suit_index = rnd.nextInt(4);
        return new Card(suitList[suit_index], rankList[rank_index]);
    }

    protected void buildDack() {
        allCards = new Card[51];
        Card discardedCard = randomChooseOneCard();
        int k = 0;
        for (char suit : suitList)
            for (String rank : rankList) {
                if (!(discardedCard.getRank().equals(rank) && discardedCard.getSuit() == suit))
                    allCards[k++] = new Card(suit, rank);
            }
    }
}
