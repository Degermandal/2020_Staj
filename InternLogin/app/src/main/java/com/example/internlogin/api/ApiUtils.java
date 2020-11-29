package com.example.internlogin.api;


import com.example.internlogin.interfaced.APIService;

import retrofit2.Retrofit;

public class ApiUtils {

    public ApiUtils() {
    }
    public static final String API_URL = "http://10.0.2.2:8080/BankingApiV01/";
    public static final String INTER_URL = "https://api-gateway.intertech.com.tr/BankingApiV01/";
    public static final String NEWS_URL = "http://newsapi.org/v2/";


    public static APIService getAPIService() {
        return RetrofitClient.getClient(API_URL).create(APIService.class);
    }

    public static APIService getStockOrderService() {
        return RetrofitClient.getInterClient(INTER_URL).create(APIService.class);
    }

    public static APIService getNewsService() {
        return RetrofitClient.getNewsClient(NEWS_URL).create(APIService.class);
    }
}
