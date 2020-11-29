package com.example.internlogin.ui.market;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.internlogin.Adapter.MarketAdapter;
import com.example.internlogin.MainActivity;
import com.example.internlogin.Model.Market;
import com.example.internlogin.R;
import com.example.internlogin.api.ApiUtils;
import com.example.internlogin.interfaced.APIService;
import com.example.internlogin.modelOfRequest.StockRequest;
import com.example.internlogin.modelOfResponse.GetMarket.GetMarket;
import com.example.internlogin.modelOfResponse.GetStockForOrder.GetStockForOrder;
import com.example.internlogin.ui.order.BuyOrder;
import com.example.internlogin.ui.order.OrderFragment;
import com.example.internlogin.ui.order.SellOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketFragment extends Fragment {

    List<Market> marketList = new ArrayList<>();
    ListView lv_market;
    MarketAdapter marketAdapter;
    APIService apiService;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        System.out.println("market fragment");

        root = inflater.inflate(R.layout.fragment_market, container, false);

        lv_market = root.findViewById(R.id.lv_market);
        apiService = ApiUtils.getAPIService();


        StockRequest stockRequest = new StockRequest(30);
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        header.put("Ocp-Apim-Subscription-Key","584075b0c41c4d15abd24e89df477d33");

        //TODO PİYASA -> Çağır
        getStockMarket(header,stockRequest); //Inter
    //    getStockMarket(); //Hurriyet iptal




        lv_market.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("---" + marketList.get(i).getSymbol());
                Intent intent = new Intent(root.getContext(), BuyOrder.class); // ?????????
                intent.putExtra("symbol",marketList.get(i).getSymbol());
                intent.putExtra("buying",marketList.get(i).getBuying());
                intent.putExtra("selling",marketList.get(i).getSelling());

                //startActivity(intent);
                alertDialogOpen(i);
            }
        });

        return root;
    }

    //TODO PİYASA -> Hürriyet
    //Hürriyet'ten çeken
    public void getStockMarket() {
        System.out.println("get stock market ..");
        apiService.stockMarketGet().enqueue(new Callback<List<GetMarket>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<GetMarket>> call, Response<List<GetMarket>> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        System.out.println("--- ğğğğ " + response.body().get(0).getData().getHisseYuzeysel().getSembol());
                        response.body().stream().forEach(o -> marketList.add(new Market(o.getData().getHisseYuzeysel().getSembol(),
                                o.getData().getHisseYuzeysel().getAlis(),
                                o.getData().getHisseYuzeysel().getSatis(),
                                o.getData().getHisseYuzeysel().getKapanis(),o.getData().getHisseYuzeysel().getYuzdedegisim())));

                        marketAdapter = new MarketAdapter(marketList,root.getContext());
                        lv_market.setAdapter(marketAdapter);
                    } else
                        System.out.println("Get Stock Market : Response Body Null" );
                }
            }
            @Override
            public void onFailure(Call<List<GetMarket>> call, Throwable t) {
                System.out.println("Get Stock Market Fail : " + t.getMessage());
            }
        });
    }

    //TODO PİYASA -> Inter-api için bu..
    //Inter-api'den çeken
    public void getStockMarket(Map<String,String> header, StockRequest stockRequest) {
        System.out.println("get stock market in buy fragment..");
        APIService interApiService = ApiUtils.getStockOrderService();

        interApiService.marketOnInterGet(header, stockRequest).enqueue(new Callback<GetStockForOrder>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<GetStockForOrder> call, Response<GetStockForOrder> response) {
                System.out.println(response + " ???");
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        response.body().getData().getIndexList().stream()
                                .forEach(o -> marketList.add(new Market(o.getSymbol(),
                                        Double.valueOf(o.getBuyPrice()),
                                        Double.valueOf(o.getSellPrice()),
                                        Double.valueOf(o.getPrice()),
                                        Double.valueOf(o.getDifference()))));

                        marketAdapter = new MarketAdapter(marketList,root.getContext());
                        lv_market.setAdapter(marketAdapter);


                    } else
                        System.out.println("Stocks For Order : Response body null");
                }
            }

            @Override
            public void onFailure(Call<GetStockForOrder> call, Throwable t) {
                System.out.println("Stocks For Order : " + t.getMessage());
            }
        });
    }


    private void alertDialogOpen(int i) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_buy_shape, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        Button btn_buy = view.findViewById(R.id.btn_buy);
        Button btn_sell = view.findViewById(R.id.btn_sell);
        alert.setView(view);
        alert.setCancelable(true);

        Bundle bundle = new Bundle();
        bundle.putString("amount","1");
        bundle.putString("id" , "-1");
        bundle.putString("symbol",marketList.get(i).getSymbol());
        bundle.putString("buying",String.valueOf(marketList.get(i).getBuying()));
        bundle.putString("selling",String.valueOf(marketList.get(i).getSelling()));
        bundle.putString("last",String.valueOf(marketList.get(i).getLast()));

        final AlertDialog dialog = alert.create();
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BuyOrder();
                fragment.setArguments(bundle);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,fragment)
                        .commit();
                dialog.cancel();
            }
        });
        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SellOrder();
                fragment.setArguments(bundle);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,fragment)
                        .commit();
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
