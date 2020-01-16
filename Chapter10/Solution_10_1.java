package Chapter10;

import java.util.Arrays;

/**
 * Solution_10_1
 */
public class Solution_10_1 {

    static void sortedMerge(int[] a, int[] b, int sizeA, int sizeB) {
        int[] h = new int[a.length];
        for (int i = 0; i < sizeA; i++) {
            h[i] = a[i];
        }
        for (int i = 0; i < sizeB; i++) {
            h[i+sizeA] = b[i];
        }

        int helperA = 0;
        int helperB = sizeA;
        int current = 0;
        while (helperA < sizeA && helperB < sizeA+sizeB) {
            if (h[helperA] <= h[helperB]) {
                a[current] = h[helperA];
                helperA++;
            } else {
                a[current] = h[helperB];
                helperB++;
            }
            current++;
        }

        while (helperA < sizeA) {
            a[current] = h[helperA];
            helperA++;
            current++;
        }

        while (helperB < sizeA+sizeB) {
            a[current] = h[helperB];
            helperB++;
            current++;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,4,6,0,0,0,0};
        int[] b = new int[]{1,3,5,7};
        sortedMerge(a, b, 3, 4);
        System.out.println(Arrays.toString(a));
    }
}