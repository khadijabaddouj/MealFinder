package com.example.mealfinder1;

import com.example.mealfinder1.Model.RecipeInformation;

public interface RecipeInfoListener {
    void didFetch(RecipeInformation response, String message);
    void didError(String message);
}
