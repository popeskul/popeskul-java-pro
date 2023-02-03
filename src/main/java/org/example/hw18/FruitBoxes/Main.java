package org.example.hw18.FruitBoxes;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        appleBox.addFruit(new Apple());
        appleBox.addFruits(new ArrayList<>(List.of(new Apple(), new Apple())));
        System.out.println("Apple box weight: " + appleBox.getWeight());

        orangeBox.addFruit(new Orange());
        orangeBox.addFruits(new ArrayList<>(List.of(new Orange(), new Orange())));

        System.out.println("Orange box weight: " + orangeBox.getWeight());
        System.out.println("Are boxes equal? " + appleBox.compare(orangeBox));

        Box<Apple> anotherAppleBox = new Box<>();
        anotherAppleBox.addFruits(new ArrayList<>(List.of(new Apple(), new Apple(), new Apple())));
        System.out.println("Are boxes equal? appleBox and anotherAppleBox " + appleBox.compare(anotherAppleBox));

        System.out.println("Total size before merging " + appleBox.getFruits().size());
        appleBox.merge(anotherAppleBox);
        System.out.println("Total size after merging " + appleBox.getFruits().size());
    }
}
