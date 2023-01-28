package org.example.hw15.coffee.order;

import java.util.LinkedList;
import java.util.Queue;

public class CoffeeOrderBoard {
    private Queue<Order> orders = new LinkedList<>();
    private int orderCounter = 0;

    public void add(Order order) {
        order.setOrderNumber(++orderCounter);
        orders.add(order);
    }

    public Order deliverNext() {
        return orders.poll();
    }

    public Order deliver(int number) {
        for (Order order : orders) {
            if (order.getOrderNumber() == number) {
                orders.remove(order);
                return order;
            }
        }

        return null;
    }

    public void draw() {
        System.out.println("=============");
        System.out.println("Num | Name");
        for (Order order : orders) {
            System.out.println(order.getOrderNumber() + " | " + order.getName());
        }
    }

    public static void main(String[] args) {
        CoffeeOrderBoard board = new CoffeeOrderBoard();

        board.add(new Order("John"));
        board.add(new Order("Mike"));
        board.add(new Order("Jane"));
        board.add(new Order("Jane"));

        board.draw();

        //Delivering the next order in the queue
        Order nextOrder = board.deliverNext();
        System.out.println("Delivered order: " + nextOrder.getOrderNumber() + ", ordered by: " + nextOrder.getName());

        board.draw();

        //Delivering an order with a certain number
        int orderNumber = 3;
        Order specificOrder = board.deliver(orderNumber);
        if (specificOrder != null) {
            System.out.println("Delivered order: " + specificOrder.getOrderNumber() + ", ordered by: " + specificOrder.getName());
        } else {
            System.out.println("Order with number " + orderNumber + " is not found.");
        }

        board.draw();
    }
}