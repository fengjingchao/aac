package com.example.listview;

public class Book {
    private int imageId;
    private String name;

    public int getImageId() {
        return imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Book (String name, int imageId) {
        this.imageId = imageId;
        this.name = name;
    }
}
