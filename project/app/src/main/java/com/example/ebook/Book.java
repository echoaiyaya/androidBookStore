package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "imageId")
    private int imageId;
    @ColumnInfo(name = "author")
    private String author;
    @ColumnInfo(name = "Description")
    private String Description;
    @ColumnInfo(name = "webApi")
    private String webApi;

    public String getWebApi() {
        return webApi;
    }

    public void setWebApi(String webApi) {
        this.webApi = webApi;
    }

    private Double Price;
    private String type;
    @NonNull
    @PrimaryKey
    private String bookId;

    public Book() {
    }

    public Book(String name, int imageId, String author, String description,String bookId, String webApi) {
        this.name = name;
        this.imageId = imageId;
        this.author = author;
        Description = description;
        this.bookId = bookId;
        this.webApi = webApi;
    }

    public Book(String name, int imageId, String author, String description, Double price, String type, String bookId, String webApi) {
        this.name = name;
        this.imageId = imageId;
        this.author = author;
        Description = description;
        Price = price;
        this.type = type;
        this.bookId = bookId;
        this.webApi = webApi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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
