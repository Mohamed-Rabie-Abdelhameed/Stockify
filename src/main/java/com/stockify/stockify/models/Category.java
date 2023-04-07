package com.stockify.stockify.models;

public class Category {
    private int Id;
    private String name;

    public Category(int categoryId, String name) {
        this.Id = categoryId;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
