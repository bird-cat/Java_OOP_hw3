package ntu.r09922114.util;

import java.util.*;

public class Shuffle {
    public static void shuffleArray(Object[] arr) {
        Random rnd = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = rnd.nextInt(i + 1);
            Object tmp = arr[i];
            arr[i] = arr[index];
            arr[index] = tmp;
        }
    }
}
