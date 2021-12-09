package com.kuliahdhevan.pasardesasambungrejo;

public class Product {
    public String code;
    public String name;
    public String description;
    public int price;
    public final int imageResource;

    Product(String name, String description, int price, int imageResource, String code){
        this.name =name;
        this.price = price;
        this.description = description;
        this.imageResource = imageResource;
        this.code = code;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getImageResource() { return imageResource; }
    public String getDescription() { return description; }
}
