package ntu.r09922114.util;

public class Sort {
    public static void sort(Comparable[] items) {
        int j;
        for (int i = 1; i < items.length; i++) {
            Comparable tmp = items[i];
            for (j = i - 1; j >= 0; j--) {
                if (tmp.compare(items[j]) < 0)
                    items[j + 1] = items[j];
                else
                    break;
            }
            items[j + 1] = tmp;
        }
    }
}