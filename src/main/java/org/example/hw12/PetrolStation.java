package org.example.hw12;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class PetrolStation {
    private final String errorMessage = "Not enough petrol in the station";
    private int maxDoRefuelAmount = 10;
    private int amount = 0;
    private Semaphore semaphore = new Semaphore(maxDoRefuelAmount);

    public PetrolStation(int amount) {
        this.amount = amount;
    }

    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }

    public synchronized int getAmount() {
        return amount;
    }

    public void doRefuel(int value) {
        try {
            semaphore.acquire();

            waitRandomSecond();

            if (amount >= value) {
                amount -= value;
            } else {
                System.out.println(errorMessage);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public void waitRandomSecond() {
        try {
            Thread.sleep((long) (Math.random() * 2000 + 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int countThreads = 10;
        PetrolStation petrolStation = new PetrolStation(100);
        CountDownLatch countDownLatch = new CountDownLatch(countThreads);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("Thread " + finalI + " is running " + petrolStation.getAmount());
                petrolStation.doRefuel(10);
                System.out.println("Thread " + finalI + " is finished " + petrolStation.getAmount());
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("getAmount: " + petrolStation.getAmount());
    }
}
