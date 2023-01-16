package org.example.hw13;

import java.util.*;

// Originally the task was to create simple methods, but I decided to create generic methods for fun.
public class HW13 {
    // task 1
    private static <T> int countOccurrence(List<T> list, T element) {
        int count = 0;
        for (T e : list) {
            if (e.equals(element)) {
                count++;
            }
        }
        return count;
    }

    // task 2
    private static <T> List<T> toList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    // task 3
    private static <T> List<T> findUnique(List<T> list) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (countOccurrence(list, e) == 1) {
                result.add(e);
            }
        }
        return result;
    }

    // task 4 **
    // task 4 ***
    private static <T> Map<T, Integer> calcOccurrence(List<T> list) {
        Map<T, Integer> result = new HashMap<>(Map.of());
        for (T e : list) {
            if (result.containsKey(e)) {
                result.put(e, result.get(e) + 1);
            } else {
                result.put(e, 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int countOccurrence1 = countOccurrence(List.of("a", "b", "c", "a", "b", "a"), "a");
        int countOccurrence2 = countOccurrence(List.of(1, 2, 3, 1, 2, 1), 1);
        System.out.println("countOccurrence " + countOccurrence1 + " " + countOccurrence2);

        String[] arrayToList1 = {"a", "b", "c", "a", "b", "a"};
        Integer[] arrayToList2 = {1, 2, 3, 1, 2, 1};
        System.out.println("toList " + toList(arrayToList1) + " " + toList(arrayToList2));

        List<Integer> listFindUnique1 = List.of(5, 1, 2, 3, 3, 4, 5);
        List<String> listFindUnique2 = List.of("a", "b", "c", "a", "b", "a", "d");
        System.out.println("findUnique " + findUnique(listFindUnique1) + " " + findUnique(listFindUnique2));

        List<Integer> listCalcOccurrence1 = List.of(1, 1, 2, 3, 4, 5, 0, 0, 5);
        List<String> listCalcOccurrence2 = List.of("a", "b", "c", "d", "e", "a", "b", "a", "d");
        System.out.println("calcOccurrence " + calcOccurrence(listCalcOccurrence1) + " " + calcOccurrence(listCalcOccurrence2));
    }
}
