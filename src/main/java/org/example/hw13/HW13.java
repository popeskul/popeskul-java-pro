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
}
