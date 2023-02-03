package org.example.hw18.FruitBoxes;

import java.util.ArrayList;
import java.util.List;

class Box <T extends Fruit> {
    private final ArrayList<T> fruits;
    private float weight;

    public Box() {
        fruits = new ArrayList<T>();
        weight = 0.0F;
    }

    public float getWeight() {
        return weight;
    }

    public List<T> getFruits() {
        return fruits;
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
        weight += fruit.getWeight();
    }

    public void addFruits(ArrayList<T> fruits) {
        this.fruits.addAll(fruits);
        for (T fruit : fruits) {
            weight += fruit.getWeight();
        }
    }

    public boolean compare(Box<?> box) {
        return this.getWeight() == box.getWeight();
    }

    public void merge(Box<T> box) {
        this.addFruits(box.fruits);
        box.fruits.clear();
        box.weight = 0.0F;
    }
}
