package org.example.hw15.coffee.order;

public class Order {
    private int orderNumber;
    private String name;

    public Order(String name) {
        this.name = name;
    }

    public Order(int orderNumber, String name) {
        this.orderNumber = orderNumber;
        this.name = name;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", name='" + name + '\'' +
                '}';
    }
}
