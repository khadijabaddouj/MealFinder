package com.example.mealfinder1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealfinder1.Model.ExtendedIngredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder>{

    Context context;
    List<ExtendedIngredient> ingredientList;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.card_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.textView_amount.setText(ingredientList.get(position).original);
        holder.textView_amount.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+ingredientList.get(position).image).into(holder.imageView_ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}
class IngredientsViewHolder extends RecyclerView.ViewHolder{
    TextView textView_amount;
    ImageView imageView_ingredient;
    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_amount = itemView.findViewById(R.id.textView_amount);
        imageView_ingredient = itemView.findViewById(R.id.imageView_ingredient);

    }
}