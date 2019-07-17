package com.example.recipe_capstone;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_capstone.dataFromApi.Adapters.RecyclerAdapterOfResults;
import com.example.recipe_capstone.dataFromApi.RecipeResponse;

import static com.example.recipe_capstone.MainActivity.INTENT_OF_SEARCH;

public class ResultsActivity extends AppCompatActivity {
    RecipeResponse recipeResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.barBackground));
        recipeResponse = (RecipeResponse) getIntent().getSerializableExtra(INTENT_OF_SEARCH);
        getRecipes();
        TextView recipeNameBar = findViewById(R.id.recipe_name_bar);
        recipeNameBar.append(recipeResponse.getTitle());
    }

    private void getRecipes() {
        RecyclerView recyclerView = findViewById(R.id.recyclerResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        RecyclerAdapterOfResults recyclerAdapterOfResults = new RecyclerAdapterOfResults(recipeResponse, getApplicationContext());
        recyclerView.setAdapter(recyclerAdapterOfResults);
    }
}
