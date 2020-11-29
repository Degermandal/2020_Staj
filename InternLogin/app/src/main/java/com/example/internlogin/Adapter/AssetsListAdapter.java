package com.example.internlogin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.internlogin.Model.Asset;
import com.example.internlogin.Model.Market;
import com.example.internlogin.R;
import com.example.internlogin.api.ApiUtils;
import com.example.internlogin.interfaced.APIService;
import com.example.internlogin.modelOfRequest.StockRequest;
import com.example.internlogin.modelOfResponse.GetStockForOrder.GetStockForOrder;
import com.example.internlogin.ui.order.SellOrder;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetsListAdapter extends BaseAdapter {

    List<Asset> assetList;
    FragmentManager fragment;
    Context context;
    public int balanceType;
    StockRequest stockRequest;
    Map<String,String> header;


    public AssetsListAdapter(Context context, List<Asset> assetList, int balanceType,FragmentManager fragment) {
        this.assetList = assetList;
        this.context = context;
        this.balanceType = balanceType;
        this.fragment = fragment;
        stockRequest = new StockRequest(30);
        header = new HashMap<>();
        header.put("Content-Type","application/json");
        header.put("Ocp-Apim-Subscription-Key","584075b0c41c4d15abd24e89df477d33");
    }

    @Override
    public int getCount() {
        return assetList.size();
    }

    @Override
    public Object getItem(int position) {
        return assetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.layout_assets_row, parent, false);
        TextView tv_assetName = convertView.findViewById(R.id.tv_assetName);
        TextView tv_assetValue = convertView.findViewById(R.id.tv_assetValue);
        TextView tv_assetProfit = convertView.findViewById(R.id.tv_assetProfit);
        TextView tv_assetAmount = convertView.findViewById(R.id.tv_assetAmount);
        Button btn_sell = convertView.findViewById(R.id.btn_sell);

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        if(balanceType == 0){
            symbols.setCurrencySymbol("₺");
        }
        else if(balanceType == 1){
            symbols.setCurrencySymbol("$");
        }
        else {
            symbols.setCurrencySymbol("€");
        }
        formatter.setDecimalFormatSymbols(symbols);

        tv_assetName.setText(assetList.get(position).getName());
        tv_assetAmount.setText(String.valueOf(assetList.get(position).getAmount().intValue()));
        tv_assetValue.setText(formatter.format(Double.valueOf(assetList.get(position).getValue())));
        tv_assetProfit.setText(""+String.format("%.2f", assetList.get(position).getProfit()));
        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Market[] market = new Market[1];
                List<Market> marketList = new ArrayList<>();
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

                            } else
                                System.out.println("Stocks For Order : Response body null");
                        }
                        for(Market m: marketList){
                            if(m.getSymbol().equals(assetList.get(position).getName())){
                                market[0] = new Market(m.getSymbol(),m.getBuying(),m.getSelling(),m.getLast(),m.getRate());
                            }
                        }
                        Bundle bundle = new Bundle();
                        int dotIndex = String.valueOf(assetList.get(position).getAmount()).indexOf(".");
                        bundle.putString("id","-1");
                        bundle.putString("amount", String.valueOf(assetList.get(position).getAmount()).substring(0,dotIndex));
                        bundle.putString("symbol",market[0].getSymbol());
                        bundle.putString("buying",String.valueOf(market[0].getBuying()));
                        bundle.putString("selling",String.valueOf(market[0].getSelling()));
                        bundle.putString("last",String.valueOf(market[0].getLast()));

                        Fragment sellOrder = new SellOrder();
                        sellOrder.setArguments(bundle);
                        fragment.beginTransaction()
                                .replace(R.id.nav_host_fragment,sellOrder)
                                .commit();

                        System.out.println("++++++++++++++++"+market[0].getSymbol());
                    }

                    @Override
                    public void onFailure(Call<GetStockForOrder> call, Throwable t) {
                        System.out.println("Stocks For Order : " + t.getMessage());
                    }
                });

            }
        });

        return convertView;
    }
    private Market getStockMarket(Map<String,String> header, StockRequest stockRequest, String assetName) {
        System.out.println("get stock market in buy fragment..");
        final Market[] market = new Market[1];
        List<Market> marketList = new ArrayList<>();
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

                    } else
                        System.out.println("Stocks For Order : Response body null");
                }
                for(Market m: marketList){
                    if(m.getSymbol().equals(assetName)){
                        market[0] = new Market(m.getSymbol(),m.getBuying(),m.getSelling(),m.getLast(),m.getRate());
                    }
                }
                System.out.println("++++++++++++++++"+market[0].getSymbol());
            }

            @Override
            public void onFailure(Call<GetStockForOrder> call, Throwable t) {
                System.out.println("Stocks For Order : " + t.getMessage());
            }
        });

        return market[0];
    }

}
