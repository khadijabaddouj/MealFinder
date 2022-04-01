package com.example.mealfinder1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealfinder1.Model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{

    Context context;
    List<Recipe> recipes;
    RecipeOnClickListener clickListener;

    public RandomRecipeAdapter(Context context, List<Recipe> recipes, RecipeOnClickListener clickListener) {
        this.context = context;
        this.recipes = recipes;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.card_random_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textView_title.setText(recipes.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_likes.setText(recipes.get(position).aggregateLikes+" Likes");
        holder.textView_servings.setText(recipes.get(position).servings+" Servings");
        holder.textView_duration.setText(recipes.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(recipes.get(position).image).into(holder.imageView_meal);
        holder.recipe_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onRecipeClick(String.valueOf(recipes.get(holder.getAdapterPosition()).id));
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView recipe_card;
    TextView textView_title, textView_servings, textView_likes, textView_duration;
    ImageView imageView_meal;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        recipe_card = itemView.findViewById(R.id.recipe_card);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_servings = itemView.findViewById(R.id.textView_servings);
        textView_likes = itemView.findViewById(R.id.textView_likes);
        textView_duration = itemView.findViewById(R.id.textView_duration);
        imageView_meal = itemView.findViewById(R.id.imageView_meal);
    }
}

