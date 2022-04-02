package com.example.mealfinder1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealfinder1.Model.RecipeInformation;
import com.squareup.picasso.Picasso;

public class RecipeInfoActivity extends AppCompatActivity {
    int id;
    TextView textView_recipe_name, textView_recipe_source, textView_summary;
    ImageView imageView_recipe;
    RecyclerView recycler_ingredients;
    RequestManager manager;
    IngredientsAdapter ingredientsAdapter;
    ProgressDialog progressDialog;

    private final RecipeInfoListener recipeInfoListener = new RecipeInfoListener() {
        @Override
        public void didFetch(RecipeInformation response, String message) {
            progressDialog.dismiss();
            textView_recipe_name.setText(response.title);
            //textView_recipe_source.setText(response.sourceName);
            String linkedText =
                    String.format("<a href=\"%s\">"+response.sourceName+"</a> ", response.sourceUrl);
            textView_recipe_source.setText(Html.fromHtml(linkedText));
            textView_recipe_source.setMovementMethod(LinkMovementMethod.getInstance());
            textView_summary.setText(Html.fromHtml(response.summary));
            textView_summary.setMovementMethod(LinkMovementMethod.getInstance());
            Picasso.get().load(response.image).into(imageView_recipe);
            recycler_ingredients.setHasFixedSize(true);
            recycler_ingredients.setLayoutManager(new LinearLayoutManager(RecipeInfoActivity.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(RecipeInfoActivity.this, response.extendedIngredients);
            recycler_ingredients.setAdapter(ingredientsAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeInfoActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);

        textView_recipe_name = findViewById(R.id.textView_recipe_name);
        textView_recipe_source = findViewById(R.id.textView_recipe_source);
        textView_summary = findViewById(R.id.textView_summary);
        imageView_recipe = findViewById(R.id.imageView_recipe);
        recycler_ingredients = findViewById(R.id.recycler_ingredients);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeInformation(recipeInfoListener, id);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

    }
}