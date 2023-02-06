package org.example.hw16;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
        quickSort(array, 0, array.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(array, left, right);
        quickSort(array, left, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, right);
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (array[i] <= pivot) {
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(array, partitionIndex, right);
        return partitionIndex;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
