package com.example.recipe_capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipe_capstone.dataFromApi.Adapters.RecyclerAdapterOfResults;
import com.example.recipe_capstone.dataFromApi.RecipeResponse;
import com.squareup.picasso.Picasso;

public class RecipeDetailActivity extends AppCompatActivity {

    RecipeResponse.Result result;
    TextView barText,detail;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.barBackground));

        result = (RecipeResponse.Result) getIntent().getExtras().getSerializable(RecyclerAdapterOfResults.INTENT_OF_MOVE_RESULT_TO_DETAIL_ACTIVITY);
        barText = findViewById(R.id.recipe_name_bar);
        detail = findViewById(R.id.ingredients);
        imageView = findViewById(R.id.recipe_image);

        barText.setText(result.getTitle());
        detail.setText(result.getIngredients());

        if (!getIntent().getStringExtra(RecyclerAdapterOfResults.INTENT_OF_MOVE_PATH_TO_DETAIL_ACTIVITY).isEmpty())
            Picasso.get().load(getIntent().getStringExtra(RecyclerAdapterOfResults.INTENT_OF_MOVE_PATH_TO_DETAIL_ACTIVITY)).into(imageView);
    }
}
