package com.stockify.stockify.models;

public class ChartArrays {
    private String[] categories;
    private int[] count;

    public ChartArrays(String[] categories, int[] count) {
        this.categories = categories;
        this.count = count;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public int[] getCount() {
        return count;
    }

    public void setCount(int[] count) {
        this.count = count;
    }


}
