package com.example.mealfinder1;

import android.content.Context;

import com.example.mealfinder1.Model.RandomRecipes;
import com.example.mealfinder1.Model.RecipeInformation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeListener listener){
        SpoonacularApi spoonacularApi = retrofit.create(SpoonacularApi.class);
        Call<RandomRecipes> call = spoonacularApi.callRandomRecipes(context.getString(R.string.api_key), "10");
        call.enqueue(new Callback<RandomRecipes>() {
            @Override
            public void onResponse(Call<RandomRecipes> call, Response<RandomRecipes> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipes> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeInformation(RecipeInfoListener listener, int id){
        SpoonacularApi spoonacularApi = retrofit.create(SpoonacularApi.class);
        Call<RecipeInformation> call = spoonacularApi.callRecipeInformation(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeInformation>() {
            @Override
            public void onResponse(Call<RecipeInformation> call, Response<RecipeInformation> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeInformation> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface SpoonacularApi{

        @GET("recipes/random")
        Call<RandomRecipes> callRandomRecipes(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );
        @GET("recipes/{id}/information")
        Call<RecipeInformation> callRecipeInformation(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
