package com.example.recipe_capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_capstone.dataFromApi.Adapters.RecyclerAdapterOfResults;
import com.example.recipe_capstone.dataFromApi.RecipeResponse;
import com.example.recipe_capstone.dataFromApi.Result;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static com.example.recipe_capstone.MainActivity.INTENT_OF_RESULTS;
import static com.example.recipe_capstone.dataFromApi.Adapters.RecyclerAdapterOfResults.INTENT_OF_MOVE_RESULT_TO_DETAIL_ACTIVITY;

public class ResultsActivity extends AppCompatActivity {
    public static DatabaseReference myRootRef;
    private static FirebaseRecyclerAdapter<Result, Holder> adapter;
    private static FirebaseRecyclerOptions<Result> options;
    private static FirebaseDatabase database;
    private RecipeResponse recipeResponse;
    private RecyclerView recyclerView;
    private Result selectedResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        if (database == null) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            database = FirebaseDatabase.getInstance();

            myRootRef = database.getReference();
        }
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.barBackground));
        TextView recipeNameBar = findViewById(R.id.recipe_name_bar);
        recyclerView = findViewById(R.id.recyclerResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recipeResponse = (RecipeResponse) getIntent().getSerializableExtra(INTENT_OF_RESULTS);


        if (recipeResponse != null) {
            setRecipesAdapter();
            recipeNameBar.append(getTitle());
        } else {
            setFavAdapter();
            recipeNameBar.setText(R.string.fav);
        }
    }

    private void setRecipesAdapter() {
        RecyclerAdapterOfResults recyclerAdapterOfResults = new RecyclerAdapterOfResults(recipeResponse, getApplicationContext());
        recyclerView.setAdapter(recyclerAdapterOfResults);
    }

    private void setFavAdapter() {
        setFirebaseRecycler();
        recyclerView.setAdapter(adapter);
    }


    private void setFirebaseRecycler() {
        options =
                new FirebaseRecyclerOptions.Builder<Result>()
                        .setQuery(myRootRef, Result.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Result, Holder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Holder holder, int i, @NonNull final Result result) {
                holder.name.setText(result.getTitle());
                if (!result.getThumbnail().isEmpty())
                    Picasso.get().load(result.getThumbnail()).into(holder.imageView);

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick() {
                        selectedResult = result;
                        Intent i = new Intent(getApplicationContext(), RecipeDetailActivity.class);
                        i.putExtra(INTENT_OF_MOVE_RESULT_TO_DETAIL_ACTIVITY, result);
                        startActivity(i);
                    }
                });

            }


            @NonNull
            @Override
            public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                return new Holder(view);
            }
        };

        adapter.startListening();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (adapter != null) {
            adapter.startListening();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
