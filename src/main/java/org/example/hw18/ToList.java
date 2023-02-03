package org.example.hw18;

import java.util.ArrayList;
import java.util.List;

public class ToList {
    public static <T> List<T> toList(T[] array) {
        List<T> list = new ArrayList<>();
        for (T element : array) {
            list.add(element);
        }
        return list;
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        List<Integer> intList = toList(intArray);
        System.out.println("Int List: " + intList);

        String[] stringArray = {"a", "b", "c", "d", "e"};
        List<String> stringList = toList(stringArray);
        System.out.println("String List: " + stringList);
    }
}
