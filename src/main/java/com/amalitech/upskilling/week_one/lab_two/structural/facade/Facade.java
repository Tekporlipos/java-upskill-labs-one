package com.amalitech.upskilling.week_one.lab_two.structural.facade;

import java.util.ArrayList;
import java.util.List;

record Product(String name, double price) {
}

class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotal() {
        return products.stream().mapToDouble(Product::price).sum();
    }

    public void checkout() {
        System.out.println("Checking out with total: " + calculateTotal());
        products.clear();
    }
}

class ShoppingCartFacade {
    private final ShoppingCart cart;

    public ShoppingCartFacade() {
        this.cart = new ShoppingCart();
    }

    public void addProductToCart(Product product) {
        cart.addProduct(product);
    }

    public double getTotalPrice() {
        return cart.calculateTotal();
    }

    public void checkout() {
        cart.checkout();
    }
}

class FacadePatternShoppingCartExample {
    public static void main(String[] args) {
        ShoppingCartFacade cartFacade = new ShoppingCartFacade();
        cartFacade.addProductToCart(new Product("Laptop", 999.99));
        cartFacade.addProductToCart(new Product("Mouse", 19.99));

        System.out.println("Total Price: " + cartFacade.getTotalPrice());
        cartFacade.checkout();
    }
}

