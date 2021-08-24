package com.radhio.searchingandroid;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Azmia Hoque Radhio on 8/10/2021.
 */
public class CompanyRepository extends PageKeyedDataSource<Integer, Company> {
    ICompanyService iCompanyService = NetworkingService.getServiceInstance().getRetrofit().create(ICompanyService.class);
    private Call<CResponse> clientPaymentCall;

    private final String query;
    public CompanyRepository(String query) {
        this.query = query;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Company> callback) {

        clientPaymentCall = iCompanyService.getMoviesWithPaging("5116f51e0a2992d5d2a37ea4fdd87b8c",query,1);
        clientPaymentCall.enqueue(new Callback<CResponse>() {
            @Override
            public void onResponse(@NonNull Call<CResponse> call, @NonNull Response<CResponse> response) {
                if(response.body() != null && response.isSuccessful()) {
                    callback.onResult(response.body().getMovies(), null, 1+1);
                }
            }
            @Override
            public void onFailure(@NonNull Call<CResponse> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Company> callback) {
        clientPaymentCall = iCompanyService.getMoviesWithPaging("5116f51e0a2992d5d2a37ea4fdd87b8c",query,params.key);
        clientPaymentCall.enqueue(new Callback<CResponse>() {
            @Override
            public void onResponse(@NonNull Call<CResponse> call, @NonNull Response<CResponse> response) {
                if(response.body() != null && response.isSuccessful()) {
                    Integer key = (params.key > 1) ? params.key - 1 : null;
                    callback.onResult(response.body().getMovies(), key);
                }
            }
            @Override
            public void onFailure(@NonNull Call<CResponse> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Company> callback) {

        clientPaymentCall = iCompanyService.getMoviesWithPaging("5116f51e0a2992d5d2a37ea4fdd87b8c",query,params.key);
        clientPaymentCall.enqueue(new Callback<CResponse>() {
            @Override
            public void onResponse(@NonNull Call<CResponse> call, @NonNull Response<CResponse> response) {
                if(response.body() != null && response.isSuccessful()) {
                    callback.onResult(response.body().getMovies(), params.key + 1);
                }
            }
            @Override
            public void onFailure(@NonNull Call<CResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
