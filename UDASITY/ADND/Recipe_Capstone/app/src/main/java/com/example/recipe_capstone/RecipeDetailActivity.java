package com.example.recipe_capstone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.recipe_capstone.dataFromApi.Adapters.RecyclerAdapterOfResults;
import com.example.recipe_capstone.dataFromApi.Result;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.recipe_capstone.ResultsActivity.myRootRef;

public class RecipeDetailActivity extends AppCompatActivity {

    public static String ingredients;
    private Result result;
    private TextView barText;
    private TextView detail;
    private ImageView imageView;
    private ImageButton imageButton;
    private ValueEventListener value;
    private ArrayList<String> stringArrayList;
    private Button addWidgetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.barBackground));


        result = (Result) Objects.requireNonNull(getIntent().getExtras()).getSerializable(RecyclerAdapterOfResults.INTENT_OF_MOVE_RESULT_TO_DETAIL_ACTIVITY);
        barText = findViewById(R.id.recipe_name_bar);
        detail = findViewById(R.id.ingredients);
        imageView = findViewById(R.id.recipe_image);
        imageButton = findViewById(R.id.fav_button);
        addWidgetButton = findViewById(R.id.add_widget);

        addWidgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWidget();
            }
        });

        barText.setText(result.getTitle());
        detail.setText(result.getIngredients());


        String path = result.getThumbnail().replace("\\", "");
        if (!path.isEmpty())
            Picasso.get().load(path).into(imageView);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFavorite();
            }
        });

        checkIfFav();
        Result rrr = new Result("", "", "", "");
        myRootRef.push().setValue(rrr);
    }


    private void setFavorite() {
        for (int i = 0; i < stringArrayList.size(); i++) {
            if (result.getTitle().equals(stringArrayList.get(i))) {
                myRootRef.child(result.getTitle()).removeValue();
                imageButton.setImageResource(R.drawable.ic_favorite_border_black_48dp);
                stringArrayList.clear();
                return;
            }
        }
        myRootRef.child(result.getTitle()).setValue(result);

    }


    private void checkIfFav() {
        stringArrayList = new ArrayList<>();
        value = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Result r = postSnapshot.getValue(Result.class);
                    assert r != null;
                    if (Objects.requireNonNull(r).getTitle().equals(""))
                        myRootRef.child(Objects.requireNonNull(Objects.requireNonNull(postSnapshot.getKey()))).removeValue();
                    else
                        stringArrayList.add(r.getTitle());
                    if (r.getTitle().equals(result.getTitle()))
                        imageButton.setImageResource(R.drawable.ic_favorite_black_48dp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        myRootRef.addValueEventListener(value);

    }


    private void addWidget() {
        ingredients = result.getIngredients();
        WidgetService.updateWidget(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }


}
