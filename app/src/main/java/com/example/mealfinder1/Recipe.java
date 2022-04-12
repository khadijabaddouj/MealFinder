package com.example.mealfinder1;

public class Recipe {
    private  int id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Recipe() {   }
    public Recipe(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
