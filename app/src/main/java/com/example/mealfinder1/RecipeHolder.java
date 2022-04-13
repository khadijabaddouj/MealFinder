package com.example.mealfinder1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecipeHolder extends RecyclerView.ViewHolder {

    public static TextView recipeName;



    public RecipeHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        this.recipeName = itemView.findViewById(R.id.recipeTitle);



    }
}
