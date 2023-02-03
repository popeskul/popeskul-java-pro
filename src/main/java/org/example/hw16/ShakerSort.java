package org.example.hw16;

import java.util.Arrays;

public class ShakerSort {
    public static void main(String[] args) {
        int[] array = new int[] {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
            right--;

            for (int i = right; i > left; i--) {
                if (array[i - 1] > array[i]) {
                    swap(array, i - 1, i);
                }
            }
            left++;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
