package com.example.mealfinder1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mealfinder1.Model.RandomRecipes;

public class RandomRecipeActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private RequestManager manager;
    private RandomRecipeAdapter randomRecipeAdapter;
    private RecyclerView recyclerView;
    private final RandomRecipeListener randomRecipeListener = new RandomRecipeListener() {
        @Override
        public void didFetch(RandomRecipes response, String message) {
            recyclerView = findViewById(R.id.recycler_recipe);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(RandomRecipeActivity.this,1));
            randomRecipeAdapter = new RandomRecipeAdapter(RandomRecipeActivity.this, response.recipes);
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

        /*dialog = new ProgressDialog(this);
        dialog.setTitle("Loadin...");*/

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeListener);
        //dialog.show();
    }


}