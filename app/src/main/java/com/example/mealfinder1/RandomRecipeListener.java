package com.example.mealfinder1;

import com.example.mealfinder1.Model.RandomRecipes;

public interface RandomRecipeListener {
    void didFetch(RandomRecipes response, String message);
    void didError(String message);
}
