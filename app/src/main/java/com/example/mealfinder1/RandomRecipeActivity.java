package com.example.mealfinder1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mealfinder1.Model.RandomRecipes;

import java.util.ArrayList;
import java.util.List;

public class RandomRecipeActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private RequestManager manager;
    private RandomRecipeAdapter randomRecipeAdapter;
    private RecyclerView recyclerView;
    private List<String> tags  = new ArrayList<>();
    SearchView searchView;

    private final RandomRecipeListener randomRecipeListener = new RandomRecipeListener() {
        @Override
        public void didFetch(RandomRecipes response, String message) {
            recyclerView = findViewById(R.id.recycler_recipe);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(RandomRecipeActivity.this,1));
            randomRecipeAdapter = new RandomRecipeAdapter(RandomRecipeActivity.this, response.recipes, recipeOnClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RandomRecipeActivity.this, message, Toast.LENGTH_SHORT);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_recipe);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        searchView = findViewById(R.id.RecipesSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeListener, tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeListener, tags);
        //dialog.show();
    }

    private final RecipeOnClickListener recipeOnClickListener = new RecipeOnClickListener() {
        @Override
        public void onRecipeClick(String id) {
            startActivity(new Intent(RandomRecipeActivity.this, RecipeInfoActivity.class).putExtra("id", id));
        }
    };


}