package com.kashif.wallpapers;

public class searchRVModel {
     private String category, categoryImageUrl;

    public searchRVModel(String category, String categoryImageUrl) {
        this.category = category;
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }
}
