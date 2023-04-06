package com.stockify.stockify.models;

public class Product {
    private int id;
    private String name;
    private int categoryID;
    private int supplierID;
    private double price;
    private int quantity;

    public Product( int id,String name,  int categoryID, int supplierID, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.categoryID = categoryID;
        this.supplierID = supplierID;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

}
