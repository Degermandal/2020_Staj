package com.example.internlogin.interfaced;

import com.example.internlogin.modelOfRequest.GiveNewOrder;
import com.example.internlogin.modelOfRequest.Header;
import com.example.internlogin.modelOfRequest.StockRequest;
import com.example.internlogin.modelOfResponse.GetAccountInformation.GetAccountInformation;
import com.example.internlogin.modelOfResponse.GetCustomerPortfolio.GetCustomerPortfolio;
import com.example.internlogin.modelOfResponse.GetLogin.GetUser;
import com.example.internlogin.modelOfResponse.GetMarket.GetMarket;
import com.example.internlogin.modelOfResponse.GetNews.GetNews;
import com.example.internlogin.modelOfResponse.GetOrder.CancelOrderRequest;
import com.example.internlogin.modelOfResponse.GetOrder.GetOrder;
import com.example.internlogin.modelOfResponse.GetStockForOrder.GetStockForOrder;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface APIService {

    @POST("Portfolio/GetCustomerPortfolio")
    Call<GetCustomerPortfolio> customerPortfolioPost(@Body Map<String,String> header);

    @POST("login")
    Call<GetUser> userPost(@Body Map<String, String> header);

    //piyasa için
    @GET("GetStockMarket")
    Call<List<GetMarket>> stockMarketGet();

    //emir giriş için, tüm hisse isimleri - interapi
    @POST("stocks/GetMarketShare")
    Call<GetStockForOrder> marketOnInterGet(@HeaderMap Map<String,String> header, @Body StockRequest stockRequest);

    //emir takip için tüm emirleri çek
    @POST("GetOrder")
    Call<List<GetOrder>> orderGet(@Body Map<String,String> header);

    //emir iptali için
    @POST("CancelOrder")
    Call<List<GetOrder>> cancelOrderGet(@Body CancelOrderRequest header);

    //emir giriş için
    @POST("InsertOrder")
    Call<Boolean> giveNewOrder(@Body GiveNewOrder param);

    //Haberler için
    @GET("top-headlines?country=tr&category=business&apiKey=99426b62ced34765ac8c0c2f401f8975")
    Call<GetNews> getFinanceNews();

    //Hesap detayları için
    @POST("GetUserInformation")
    Call<GetAccountInformation> accountInformationGet(@Body Map<String,String> param);
}
