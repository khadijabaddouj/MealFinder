package com.example.mealfinder1;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonRecipesHolder {
    @GET("recipes/findByIngredients?number=2&apiKey=991a84a91a03474398d5ef21671dc97a")
    Call<ArrayList<Recipe>> getrecipes(@Query("ingredients") String ingredients);
}
