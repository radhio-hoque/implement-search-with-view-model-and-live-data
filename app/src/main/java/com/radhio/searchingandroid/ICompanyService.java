package com.radhio.searchingandroid;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Azmia Hoque Radhio on 8/21/2021.
 */
public interface ICompanyService {
    @GET("search/company")
    Call<CResponse> getMoviesWithPaging(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") long page);
}
