package com.amalitech.upskilling.week_one.lab_two.behavioral;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

interface Observer {
    void update(String stockName, double price);
}

class WeatherData implements Subject {
    private final List<Observer> observers;
    private String stockName;
    private double price;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }

    public void setMeasurements(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
        notifyObservers();
    }
}

class CurrentConditionsDisplay implements Observer {
    private String stockName;
    private double price;

    public CurrentConditionsDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }

    public void display() {
        System.out.println("Current conditions: " + stockName + "F degrees and " + price + "% humidity");
    }

    @Override
    public void update(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
        display();
    }
}

class ObserverPatternWeatherStationExample {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements("Tesla", 65.5);

        currentDisplay.display();
    }
}
