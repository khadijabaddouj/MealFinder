package com.example.mealfinder1;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder> {

    Context c ;
    ArrayList<Recipe> recipesList ;

    public RecipeAdapter(Context c,ArrayList<Recipe> recipesListList) {
        this.c = c;
        recipesList = recipesListList;
    }


    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, null);

        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        RecipeHolder.recipeName.setText(recipesList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }
}

