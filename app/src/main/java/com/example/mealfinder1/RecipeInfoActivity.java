package com.example.mealfinder1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecipeInfoActivity extends AppCompatActivity {
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);
        id = Integer.parseInt(getIntent().getStringExtra("id"));
    }
}