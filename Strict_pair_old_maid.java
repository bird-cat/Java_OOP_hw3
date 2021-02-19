package ntu.r09922114.gambling;

import ntu.r09922114.gambling.Card;
import ntu.r09922114.gambling.Old_maid;

public class Strict_pair_old_maid extends Old_maid {

    public Strict_pair_old_maid(int playerCount) {
        super(playerCount);
    }

    public boolean isPair(Card a, Card b) {
        return !a.getRank().equals("0") && a.getRank().equals(b.getRank()) && a.isSameColor(b);
    }
}
