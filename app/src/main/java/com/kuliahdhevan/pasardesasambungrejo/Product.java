package com.kuliahdhevan.pasardesasambungrejo;

public class Product {
    private String name;
    private String description;
    private int price;
    private final int imageResource;

    Product(String name, String description, int price, int imageResource){
        this.name =name;
        this.price = price;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getImageResource() { return imageResource; }
    public String getDescription() { return description; }
}
