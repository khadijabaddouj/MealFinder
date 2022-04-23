package com.example.mealfinder1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mealfinder1.Model.RecipeSmall;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SearchRecipeActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;

    ArrayList<RecipeSmall> recipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        searchView=findViewById(R.id.edt_search);
        recyclerView=findViewById(R.id.RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchRecipeActivity.this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    public void search(View view) {
        if (searchView.getQuery().toString().isEmpty())
        {
            Toast.makeText(this, "Type Something..", Toast.LENGTH_SHORT).show();
        }else {
            getRecipes(searchView.getQuery().toString());
        }
    }
    private void getRecipes(String query) {


        String url = "https://api.spoonacular.com/recipes/complexSearch?query="+query+"&apiKey=08785e70b5d440daa17baee00ef4515f&includeNutrition=true";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            recipes=new ArrayList<>();
                            Gson gson = new Gson();

                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("results");
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject obj=jsonArray.getJSONObject(i);
                                RecipeSmall recipe = gson.fromJson(obj.toString(), RecipeSmall.class);
                                recipes.add(recipe);
                            }

                            RecipeAdapter adapter=new RecipeAdapter(SearchRecipeActivity.this,recipes);
                            recyclerView.setAdapter(adapter);

                        } catch (Exception e) {

                            //  e.printStackTrace();
                            Toast.makeText(SearchRecipeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchRecipeActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
        );

        Volley.newRequestQueue(SearchRecipeActivity.this).add(postRequest);

    }
}