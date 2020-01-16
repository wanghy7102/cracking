package Chapter10;

import java.util.Arrays;

/**
 * Solution_10_1
 */
public class Solution_10_1 {

    static void merge(int[] a, int[] b, int lastA, int lastB) {
        int indexA = lastA - 1;
        int indexB = lastB - 1;
        int indexMerged = lastB + lastA - 1;

        while (indexA >= 0 && indexB >= 0) {
            if (a[indexA] > b[indexB]) {
                a[indexMerged] = a[indexA];
                indexA--;
            } else {
                a[indexMerged] = b[indexB];
                indexB--;
            }
            indexMerged--;
        }

        while (indexB >= 0) {
            a[indexMerged] = b[indexB];
            indexB--;
            indexMerged--;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,4,6,0,0,0,0};
        int[] b = new int[]{1,3,5,7};
        merge(a, b, 3, 4);
        System.out.println(Arrays.toString(a));
    }
}