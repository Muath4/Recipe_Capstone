package com.example.recipe_capstone.dataFromApi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_capstone.R;
import com.example.recipe_capstone.RecipeDetailActivity;
import com.example.recipe_capstone.dataFromApi.RecipeResponse;
import com.squareup.picasso.Picasso;

public class RecyclerAdapterOfResults extends RecyclerView.Adapter<RecyclerAdapterOfResults.ViewHolder> {
    public static final String INTENT_OF_MOVE_RESULT_TO_DETAIL_ACTIVITY = "moveResultToDetailActivity";
    private final RecipeResponse recipeResponse;
    private final Context context;

    public RecyclerAdapterOfResults(RecipeResponse recipeResponse, Context context) {
        this.recipeResponse = recipeResponse;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapterOfResults.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(recipeResponse.getResults().get(position).getTitle());
        final String path = recipeResponse.getResults().get(position).getThumbnail().replace("\\", "");
        if (!path.isEmpty())
            Picasso.get().load(path).into(holder.imageView);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeDetailActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(INTENT_OF_MOVE_RESULT_TO_DETAIL_ACTIVITY, recipeResponse.getResults().get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeResponse.getResults().size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView name;
        private final LinearLayout item;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recipe_image);
            name = itemView.findViewById(R.id.recipe_name);
            item = itemView.findViewById(R.id.one_item_from_search);
        }
    }
}
