package com.example.mealfinder1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealfinder1.Model.RecipeSmall;

import java.util.ArrayList;

public class MainRecipeAdapter extends RecyclerView.Adapter<MainRecipeAdapter.ViewHolder> {

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    ArrayList<RecipeSmall> recipes;
    Context context;

    // Constructor of the class
    public MainRecipeAdapter(Context context, ArrayList<RecipeSmall> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return recipes.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
       try {



           holder.cardView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   RecipeDetailActivity.id=recipes.get(position).id;
                   Intent intent=new Intent(context, RecipeDetailActivity.class);
                   context.startActivity(intent);
               }
           });


           holder.name.setText(recipes.get(position).title);

           Glide
                   .with(context)
                   .load(recipes.get(position).image)
                   .centerCrop()
                   .into(holder.pic);


       }catch (Exception e)
       {

       }
    }


    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView pic;
        ConstraintLayout cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.tv_name);
            pic =itemView.findViewById(R.id.img);
            cardView=itemView.findViewById(R.id.item);

        }
    }

}
