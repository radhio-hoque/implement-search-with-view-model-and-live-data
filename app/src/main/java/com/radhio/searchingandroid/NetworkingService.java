package com.radhio.searchingandroid;

import android.os.Build;

import androidx.viewbinding.BuildConfig;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Azmia Hoque Radhio on 5/27/2021.
 */
public class NetworkingService {
    private static NetworkingService instance = null;
    public static String API_BASE_URL = "https://api.themoviedb.org/3/";

    public static NetworkingService getServiceInstance() {
        if (instance == null) {
            instance = new NetworkingService();
        }
        return instance;
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS);

    private static OkHttpClient callOkHttpClint() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (BuildConfig.DEBUG) {
                httpClient.addInterceptor(getLoggingInterceptor());
            }
        }
        return httpClient.build();
    }


    protected static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(callOkHttpClint())
                    .addConverterFactory(GsonConverterFactory.create());

    protected static Retrofit retrofit = builder.build();

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
