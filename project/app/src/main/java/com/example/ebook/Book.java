package com.example.ebook;

public class Book {
    private String name;
    private int imageId;
    private String author;
    private String Description;
    private Double Price;


    public Book(String name, int imageId, String author, String description, Double price) {
        this.name = name;
        this.imageId = imageId;
        this.author = author;
        Description = description;
        Price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return Description;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
