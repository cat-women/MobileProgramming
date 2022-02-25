package com.mobileprogramming.mobileprogamming3.poducts;

public class ProductModel {
    private int id;
    private String name;
    private String category;
    private String description;
    private float price;
    private byte[] image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
