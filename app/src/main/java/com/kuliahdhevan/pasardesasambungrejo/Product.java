package com.kuliahdhevan.pasardesasambungrejo;

public class Product {
    private String name;
    private int price;
    private final int imageResource;

    Product(String name, int price, int imageResource){
        this.name =name;
        this.price = price;
        this.imageResource = imageResource;
    }

    String getName() { return name; }
    int getPrice() { return price; }
    int getImageResource() { return imageResource; }
}
