package com.example.recipe_capstone.dataFromApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Services {

    @GET("api/")
    Call<RecipeResponse> search(@Query("q") String search);
}
