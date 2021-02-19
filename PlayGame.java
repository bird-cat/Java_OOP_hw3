import java.util.*;
import ntu.r09922114.util.*;
import ntu.r09922114.binaryTree.*;
import ntu.r09922114.container.*;
import ntu.r09922114.gambling.Strict_pair_old_maid;
import ntu.r09922114.gambling.NoJoker_old_maid;
import ntu.r09922114.gambling.Old_maid;

public class PlayGame {
    public static void main(String [] argv) {
        Old_maid game;
        Scanner sc = new Scanner(System.in);
        System.out.print("Choose the type of old maid((a) Traditional old maid, (b) No Joker, (c) Strict pair definition): ");
        char choice = sc.nextLine().charAt(0);
        System.out.print("Determine the number of player(from 2 to 52): ");
        int playerCount = sc.nextInt();
        switch (choice) {
            case 'a':
                game = new Old_maid(playerCount);
                break;
            case 'b':
                game = new NoJoker_old_maid(playerCount);
                break;
            case 'c':
                game = new Strict_pair_old_maid(playerCount);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        game.startGame();
    }
}