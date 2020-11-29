package com.example.internlogin.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static Gson gson;
    public static Retrofit getClient(String baseUrl) {
        Retrofit retrofit = null;
        gson = new GsonBuilder().setLenient().create();
        if(retrofit == null) {
  /*          OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();
*/
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    //.client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getInterClient(String baseUrl) {
        Retrofit retrofit = null;
        gson = new GsonBuilder().setLenient().create();
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getNewsClient(String baseUrl) {
        Retrofit retrofit = null;
        gson = new GsonBuilder().setLenient().create();
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
