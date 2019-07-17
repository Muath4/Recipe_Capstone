package com.example.recipe_capstone.dataFromApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Services {

    @GET("api/")
    Call<RecipeResponse> search(@Query("q")String search);
//
//    @GET("movie/top_rated")
//    Call<Response> getTopRatedMovies(@Query("api_key") String apiKey);
//
//    @GET("movie/{id}/videos")
//    Call<ResponseTrailer> getTrailer(@Path("id") int id, @Query("api_key") String apiKey);
//
//    @GET("movie/{movie_id}/reviews")
//    Call<ResponseReview> getReview(@Path("movie_id") int id, @Query("api_key") String apiKey);

}
