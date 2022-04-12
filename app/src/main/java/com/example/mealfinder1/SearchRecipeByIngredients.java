package com.example.mealfinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRecipeByIngredients extends AppCompatActivity {
    ArrayList<Recipe> recipeList= new ArrayList<>();
    RecipeAdapter myAdapter;
    RecyclerView  mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe_by_ingredients);

        EditText ingre=findViewById(R.id.myIngredients);
        Button searchingre=findViewById(R.id.searchByIngredients);

        mRecyclerView =  findViewById(R.id.recycleRecipes);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this ));

        searchingre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredients=ingre.getText().toString();
                Retrofit retrofit= new Retrofit.Builder()
                        .baseUrl("https://api.spoonacular.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JsonRecipesHolder jsonPlacesHolder= retrofit.create(JsonRecipesHolder.class);

                retrofit2.Call<ArrayList<Recipe>> call = jsonPlacesHolder.getrecipes(ingredients);
                call.enqueue(new Callback<ArrayList<Recipe>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                        if(response.isSuccessful()){
                            recipeList= response.body();
                            myAdapter = new RecipeAdapter(SearchRecipeByIngredients.this ,  recipeList);
                            mRecyclerView.setAdapter(myAdapter);
                            for (Recipe recipe:recipeList){
                                Log.d("title",recipe.getTitle());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

                    }
                });

            }
        });
    }
}