package Chapter10;

import java.util.Arrays;

/**
 * QuickSort
 */
public class QuickSort {

    static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    static void quickSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < index-1) {
            quickSort(arr, left, index-1);
        }
        if (index+1 < right) {
            quickSort(arr, index+1, right);
        }
    }

    static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left+right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;
            if (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 5, 2, 8, 9};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}