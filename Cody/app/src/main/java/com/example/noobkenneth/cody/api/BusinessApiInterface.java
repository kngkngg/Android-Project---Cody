package com.example.noobkenneth.cody.api;

import com.example.noobkenneth.cody.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BusinessApiInterface {

    @GET("top-headlines")
    Call<News> getNews(

            //@Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("country") String country ,
            @Query("apiKey") String apiKey
     );
}
