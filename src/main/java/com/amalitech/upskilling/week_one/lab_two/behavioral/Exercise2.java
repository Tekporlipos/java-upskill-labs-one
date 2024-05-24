package com.amalitech.upskilling.week_one.lab_two.behavioral;

import java.util.ArrayList;
import java.util.List;

// Concrete subject
class StockPrice implements Subject {
    private List<Observer> observers;
    private String stockName;
    private double price;

    public StockPrice(String stockName) {
        this.stockName = stockName;
        this.observers = new ArrayList<>();
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }
}

// Concrete observer
class StockPriceMonitor implements Observer {
    private final String stockName;
    private double price;

    public StockPriceMonitor(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public void update(String stockName, double price) {
        if (this.stockName.equals(stockName)) {
            this.price = price;
            display();
        }
    }

    public void display() {
        System.out.println("Stock: " + stockName + ", Price: " + price);
    }
}

class ObserverPatternStockPriceExample {
    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice("ABC");
        StockPriceMonitor monitor1 = new StockPriceMonitor("ABC");
        StockPriceMonitor monitor2 = new StockPriceMonitor("XYZ");

        stockPrice.registerObserver(monitor1);
        stockPrice.registerObserver(monitor2);

        stockPrice.setPrice(100.0);
    }
}
